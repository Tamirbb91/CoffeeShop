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
    <title>Register new user</title>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <div class="form-group row">
        <a href="/products">Home</a></div>
    <form:form modelAttribute="person" method="POST">
        <div class="form-group row">
            <label>First name</label>
            <form:input path="firstName" cssClass="form-control"/>
            <form:errors path="firstName" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group row">
            <label>Last name</label>
            <form:input path="lastName" cssClass="form-control"/>
            <form:errors path="lastName" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group row">
            <label>Email</label>
            <form:input path="email" cssClass="form-control"/>
            <form:errors path="email" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group row">
            <label>Phone</label>
            <form:input path="phone" cssClass="form-control"/>
            <form:errors path="phone" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group row">
            <label>City</label>
            <form:input path="address.city" cssClass="form-control"/>
            <form:errors path="address.city" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group row">
            <label>Country</label>
            <form:input path="address.country" cssClass="form-control"/>
            <form:errors path="address.country" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group row">
            <label>State</label>
            <form:input path="address.state" cssClass="form-control"/>
            <form:errors path="address.state" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group row">
            <label>Zipcode</label>
            <form:input path="address.zipcode" cssClass="form-control"/>
            <form:errors path="address.zipcode" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group row">
            <label>Password</label>
            <input name="password" type="password" class="form-control" placeholder="Password">
        </div>
        <div class="form-group row">
            <label>Verify password</label>
            <input name="password2" type="password" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
    </form:form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
