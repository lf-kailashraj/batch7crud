<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/18/16
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Sign Up</title>
</head>
<body>
Sign Up Form
<form action="users/create" method="POST">
  <div>
    <label>Name:</label>
    <input name="name" type="text"/>
  </div>
  <div>
    <label>Email:</label>
    <input name="email" type="text"/>
  </div>
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
