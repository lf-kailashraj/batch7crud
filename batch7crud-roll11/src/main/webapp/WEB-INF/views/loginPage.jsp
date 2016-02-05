<%--
  Created by IntelliJ IDEA.
  User: achyut
  Date: 2/2/16
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Login Page</title>
  <link rel="stylesheet" type="text/css" href="css/reset.css"/>
  <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div class="login-body-container">
  <div class="panel-container">
    <div class="panel-header">Login</div>
    <div class="panel-body">
      <form action="/login" method="POST">
        <div class="error-msg">
          <c:out value="${errors}"></c:out>
        </div>
        <input type="text" name="userName" placeholder="User Name">
        <input type="password" name="password" placeholder="Password">
        <div class="login-button">
          <button type="submit">Login</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>

