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
    <h4>All orders</h4>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">First name</th>
            <th scope="col">Email</th>
            <th scope="col">City</th>
            <th scope="col">Country</th>
            <th scope="col">Total products</th>
        </tr>
        </thead>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.person.firstName}</td>
                <td>${order.person.email}</td>
                <td>${order.person.address.city}</td>
                <td>${order.person.address.country}</td>
                <td>${order.orderLines.size()}</td>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
