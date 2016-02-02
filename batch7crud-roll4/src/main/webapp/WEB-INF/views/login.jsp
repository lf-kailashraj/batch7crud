<%--
  Created by IntelliJ IDEA.
  User: pratishshr
  Date: 1/26/16
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>LOG IN</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
</head>
<body>
<div class="wrapper">
    <div class="title">EMS</div>
    <div class="login-box clrfix">
        <div class="login-box-info">
            Sign in to start your session
        </div>
        <form action="auth/login" method="post">
            <input type="text" name="username" placeholder="Username">
            <input type="password" name="password" placeholder="Password">
            <input type="submit" name="submit" value="Log in">
        </form>
    </div>
</div>
</body>
</html>
