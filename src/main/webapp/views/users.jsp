<%--
  Created by IntelliJ IDEA.
  User: Tamir
  Date: 3/11/2018
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <h4>User's list</h4>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Email</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Phone</th>
            <th scope="col">City</th>
            <th scope="col">Country</th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="person">
            <tr>
                <td>${person.email}</td>
                <td>${person.firstName}</td>
                <td>${person.lastName}</td>
                <td>${person.phone}</td>
                <td>${person.address.city}</td>
                <td>${person.address.country}</td>
            </tr>
        </c:forEach>
    </table>
    <a class="btn btn-primary" href="user/addUser"> Add new user</a>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
