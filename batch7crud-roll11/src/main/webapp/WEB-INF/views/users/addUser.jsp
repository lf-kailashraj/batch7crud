<%--
  Created by IntelliJ IDEA.
  User: achyut
  Date: 2/2/16
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<div>
  <form action="/user/add" method="POST">
    <label>Username</label>
    <input type="text" name="userName" value="${param.userName}"> <c:out value="${errors.userName}"></c:out>
    <label>Password</label>
    <input type="password" name="password"> <c:out value="${errors.password}"></c:out>
    <button type="submit">Register</button>
  </form>
</div>
</body>
</html>
