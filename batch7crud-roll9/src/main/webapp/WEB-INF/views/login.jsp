<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/29/16
  Time: 9:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link href="static/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="static/css/login.css" rel="stylesheet" type="text/css" />
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>

</head>
<body>
<div class="login-wrapper">
    <div class="login-box">
        <div class="title">Classroom</div>
        <div class="login-container">
            <p class="page-info">Please Login</p>
            <form action="login" method="POST">
                <div><input type="text" id="username" name="username" value="${userObject.getUsername()}" placeholder="Username"/><span class="error">${error_login_data.errorUname}</span></div>
                <div><input type="password" id="password" name="password" placeholder="Password"/><span class="error">${error_login_data.errorPassword}</span></div>
                <div><span class="error">${error_login_data.data_mismatch}</span></div>
                <div class="clearfix"><div class="remember"><input type="checkbox" id="remember" name="remember"/><label for="remember">Remember Me</label></div><input type="submit" class="sm" value="Login"/></div>
            </form>
            <div><a href="#">Forgot Password?</a></div>
        </div>
        <div class="register-container">
            <a href="#" class="sm" >Register</a>
        </div>
    </div>
</div>
</div>
</body>
</html>





