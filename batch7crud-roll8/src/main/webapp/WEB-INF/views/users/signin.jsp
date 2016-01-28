<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/18/16
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Sign In</title>
</head>
<body>
Sign In Form
<form action="users/signin" method="POST">
  <div>
    <label>Username:</label>
    <input name="username" type="text"/>
  </div>
  <div>
    <label>Password:</label>
    <input name="password" type="password"/>
  </div>
  <div>
    <input type="submit"/>
  </div>

</form>
</body>
</html>
