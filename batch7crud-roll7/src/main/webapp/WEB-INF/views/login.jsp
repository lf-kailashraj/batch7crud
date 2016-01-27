<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel
  Date: 1/27/16
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Log In</title>
</head>
<body>

<form action="authenticate" method="post">
  username: <input type="text" name="name">
  <br/>
  password: <input type="password" name="password">
  <br/>
  <input type="submit">
</form>

</body>
</html>
