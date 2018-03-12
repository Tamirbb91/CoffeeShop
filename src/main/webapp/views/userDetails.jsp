<%--
  Created by IntelliJ IDEA.
  User: Tamir
  Date: 3/11/2018
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User details</title>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <h4>User details</h4>
    <form:form modelAttribute="person" action="userDetails" method="POST">
        <div class="form-group">
            <label>First name</label>
            <form:input path="firstName" cssClass="form-control"/>
            <form:errors path="firstName" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Last name</label>
            <form:input path="lastName" cssClass="form-control"/>
            <form:errors path="lastName" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Email</label>
            <form:input path="email" readonly="true" cssClass="form-control"/>
            <form:errors path="email" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Phone</label>
            <form:input path="phone" cssClass="form-control"/>
            <form:errors path="phone" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>City</label>
            <form:input path="address.city" cssClass="form-control"/>
            <form:errors path="address.city" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Country</label>
            <form:input path="address.country" cssClass="form-control"/>
            <form:errors path="address.country" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>State</label>
            <form:input path="address.state" cssClass="form-control"/>
            <form:errors path="address.state" cssClass="from-text text-danger" element="small"/>
        </div>
        <div class="form-group">
            <label>Zipcode</label>
            <form:input path="address.zipcode" cssClass="form-control"/>
            <form:errors path="address.zipcode" cssClass="from-text text-danger" element="small"/>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
