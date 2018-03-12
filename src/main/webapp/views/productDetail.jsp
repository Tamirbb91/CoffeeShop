<%--
  Created by IntelliJ IDEA.
  User: Tamir
  Date: 3/11/2018
  Time: 7:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit product</title>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <div class="form-group"><a href="/products">Home</a></div>
    <form:form modelAttribute="product" action="${product.id}" method="POST">
        <div class="form-group">
            <label>Product name</label>
            <form:input path="productName" class="form-control"/>
            <form:errors path="productName" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Description</label>
            <form:input path="description" class="form-control"/>
            <form:errors path="description" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Price</label>
            <form:input path="price" class="form-control"/>
            <form:errors path="price" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Product type</label>
            <form:select path="productType" class="form-control">
                <form:options/>
            </form:select>
            <form:errors path="productType" cssClass="from-text text-danger" element="small"/>
        </div>
        <button type="submit" class="btn btn-success">Update</button>
    </form:form>
    <form action="delete/${product.id}" method="post">
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
