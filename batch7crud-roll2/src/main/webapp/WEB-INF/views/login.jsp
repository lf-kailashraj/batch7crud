<%--
  Created by IntelliJ IDEA.
  User: romit
  Date: 1/26/16
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="css/reset.css"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>

</head>
<body>
<div class="mainWrapper clearfix">
    <div class="headerWrapper clearfix">
        <div class="logo"> Employee Management System</div>
    </div>
    <div class="bodyWrapper clearfix">
        <div class="col2-left clearfix">
            <div class="loginBox">
                <div class="loginTitle">Login</div>
                <form action="authenticate/login" method="POST">
                    <div class="formLabel">Username</div>
                    <div class="inputText"><input type="text" name="username"></div>
                    <div class="formLabel">Password</div>
                    <div class="inputText"><input type="password" name="password"></div>
                    <span class="errorMessage">${loginError}</span>

                    <input type="submit" value="Login">
                </form>
            </div>
        </div>
    </div>
    <div class="footerWrapper clearfix">Copyright 2016, Leapfrog Technology, Inc</div>
</div>
</body>
</html>
