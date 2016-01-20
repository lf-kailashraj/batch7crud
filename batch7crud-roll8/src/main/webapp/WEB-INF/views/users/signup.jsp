<%--
  Created by IntelliJ IDEA.
  User: grishma
  Date: 1/18/16
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sign Up</title>
</head>
<body>
<form action="create" method="POST">
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
