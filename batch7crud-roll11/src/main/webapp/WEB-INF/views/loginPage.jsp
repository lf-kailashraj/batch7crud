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
    <title>Login Page</title>
</head>
<body>
  <div>
    <form action="/login" method="POST">
      <label>Username</label>
      <input type="text" name="userName">
      <label>Password</label>
      <input type="password" name="password">
      <button type="submit">Login</button>
    </form>
    <c:out value="${errors}"></c:out>
  </div>
</body>
</html>
