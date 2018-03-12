<%--
  Created by IntelliJ IDEA.
  User: Tamir
  Date: 3/12/2018
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Header</title>
    <link href="https://use.fontawesome.com/releases/v5.0.8/css/all.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          type="text/css">
    <style>
        #header{
            width: 100%;
            height: 70px;
            top: 0;
            border-bottom: 1px solid aliceblue;
        }

        #name{
            position: absolute;
            vertical-align: center;
            text-align: center;
        }
    </style>
</head>
<body>
    <div id="header">
        <a href="/products">Home</a>
        <sec:authorize access="isAnonymous()">
            | <a href="/login">Login</a>
            | <a href="/register">Sign up</a>
        </sec:authorize>
        <sec:authorize access="hasAuthority('USER')">
            | <a href="/user/userDetails">Update user details</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            | <a href="/admin/users">Users</a>
            | <a href="/admin/orders">Orders</a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            | <a href="/logout"> logout </a>
        </sec:authorize>
        <h2 id="name">Vishwa Shanti coffee shop</h2>
    </div>
</body>
</html>
