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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <h4>Add new product</h4>
    <form:form modelAttribute="product" action="addProduct" method="POST">
        <div class="form-group">
            <label>Product name</label>
            <form:input path="productName" cssClass="form-control"/>
            <form:errors path="productName" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Description</label>
            <form:input path="description" cssClass="form-control"/>
            <form:errors path="description" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Price</label>
            <form:input path="price" cssClass="form-control"/>
            <form:errors path="price" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Product type</label>
            <form:select path="productType" cssClass="form-control">
                <form:options/>
            </form:select>
            <form:errors path="productType" cssClass="from-text text-danger" element="small"/>
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
    </form:form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
