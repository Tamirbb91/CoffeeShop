<%--
  Created by IntelliJ IDEA.
  User: Tamir
  Date: 3/11/2018
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>PlaceOrder</title>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <p><a href="/products">Home</a></p>
    <h1>Place order</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Product name</th>
            <th scope="col">Description</th>
            <th scope="col">Price</th>
            <th scope="col">Product type</th>
        </tr>
        </thead>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.productName}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.productType}</td>
                    <%--<td><a href="/products/${product.id}">edit</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
