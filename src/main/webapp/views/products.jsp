<%--
  Created by IntelliJ IDEA.
  User: Tamir
  Date: 3/11/2018
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Products List</title>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <h3>Coffee list</h3>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Product name</th>
            <th scope="col">Description</th>
            <th scope="col">Price</th>
            <th scope="col">Product type</th>
            <sec:authorize access="hasRole('ADMIN')">
                <th scope="col">Edit</th>
            </sec:authorize>
            <sec:authorize access="hasAuthority('USER')">
                <th scope="col">Add to Order</th>
            </sec:authorize>
        </tr>
        </thead>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.productName}</td>
                <td>${product.description}</td>
                <td>$ ${product.price}</td>
                <td>${product.productType}</td>
                <sec:authorize access="hasRole('ADMIN')">
                    <td><a class="btn btn-info" href="/admin/product/${product.id}">edit</a></td>
                </sec:authorize>
                <sec:authorize access="hasAuthority('USER')">
                    <td>
                        <form method="POST" action="/user/addOrder/${product.id}">
                            <button type="submit" value="add to Order" class="btn btn-primary"><i
                                    class="fas fa-plus-square"></i> add order
                            </button>
                        </form>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
    <sec:authorize access="hasRole('ADMIN')">
        <div>
            <a class="btn btn-primary" href="/admin/product/addProduct">Add product</a>
        </div>
    </sec:authorize>

    <c:if test="${order.orderLines.size() > 0}">
        <h3>Purchase cart</h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Product name</th>
                <th scope="col">Quantity</th>
                <th scope="col">Remove from order</th>
            </tr>
            </thead>
            <c:forEach items="${order.orderLines}" var="orderline">
                <tr>
                    <td>${orderline.product.productName}</td>
                    <td>${orderline.quantity}</td>
                    <td>
                        <form method="POST" action="/user/removeOrder/${orderline.product.id}">
                            <button type="submit" value="remove from Order" class="btn btn-danger"><i
                                    class="fas fa-trash"></i> remove
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>Total amount : ${order.totalAmount}</p>
        <form method="POST" action="/user/placeOrder">
            <button type="submit" value="place order" class="btn btn-success">Place order</button>
        </form>
    </c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
