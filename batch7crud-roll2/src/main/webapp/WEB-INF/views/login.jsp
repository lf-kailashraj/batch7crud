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
<form action="authenticate/login" method="POST">
    <table>
        <tbody>
        <tr>
            <td>Username</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login"></td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
