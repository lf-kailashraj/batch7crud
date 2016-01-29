<%--
  Created by IntelliJ IDEA.
  User: kiran
  Date: 1/27/16
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>login page</title>
</head>
<body>

<h2> Login </h2>

<div>
    <span>${errors}</span>
    <form action="authentication/login" method="post">
        <div>
            <label>User Name :</label>
            <input type="text" name="userName">
        </div>
        <div>
            <label>Password :</label>
            <input type="password" name="password">
        </div>

        <input type="submit" value="login">
    </form>
</div>

</body>
</html>
