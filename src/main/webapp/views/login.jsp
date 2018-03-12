<%--
  Created by IntelliJ IDEA.
  User: Tamir
  Date: 3/9/2018
  Time: 11:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<div class="container">
    <jsp:include page="header.jsp"/>
    <form action="/login" method="POST">
        <div class="form-group">
            <label for="username">Username </label><input class="form-control" type="text" name="username"
                                                           id="username"/>
        </div>
        <div class="form-group">
            <label for="password">Password </label><input class="form-control" type="password" name="password"
                                                           id="password"/>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
