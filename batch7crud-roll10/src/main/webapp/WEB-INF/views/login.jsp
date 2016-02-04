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
    <link type="text/css" rel="stylesheet" href="static/css/reset.css">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
</head>
<body>
<div class="wrapper">
    <div class="login-content">
        <div class="login-title">
            <h2>abcd</h2>
        </div>

        <div class="login-form">
            <form action="/auth/login" method="post">
                <div class="form-group">
                    <label>username</label> <input type="text" name="username" class="form-control"><br>
                </div>
                <div class="form-group">
                    <label>password</label> <input type="password" name="password" class="form-control"><br>
                </div>

                <input type="submit" class="button button-primary" value="Login">
            </form>
        </div>
    </div>
</div>

    <script src="static/js/index.js"></script>
</body>
</html>
