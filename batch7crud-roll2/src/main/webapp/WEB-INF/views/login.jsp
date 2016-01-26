<%--
  Created by IntelliJ IDEA.
  User: romit
  Date: 1/26/16
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Login</title>
</head>
<body>
<h1>User Login</h1>
${loginError}
<form action="login" method="POST">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
