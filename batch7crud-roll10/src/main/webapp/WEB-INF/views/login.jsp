<%--
  Created by IntelliJ IDEA.
  User: binodnme
  Date: 1/27/16
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="static/css/login.css">
</head>
<body>
    <div class="container">
        <div class="form-wrapper">
            <form action="/auth/login" method="post">
                <label>username</label> <input type="text" name="username"><br>
                <label>password</label> <input type="password" name="password"><br>
                <input type="submit">
            </form>
        </div>
    </div>

    <script src="static/js/index.js"></script>
</body>
</html>
