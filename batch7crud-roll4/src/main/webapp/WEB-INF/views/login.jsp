<%--
  Created by IntelliJ IDEA.
  User: pratishshr
  Date: 1/26/16
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>LOG IN</title>
</head>
<body>
    <form action="auth/login" method="post">
        username: <input type="text" name="username">
        <br/>
        password: <input type="password" name="password">
        <br/>
        <input type="submit" name="submit" value="submit">
    </form>
</body>
</html>
