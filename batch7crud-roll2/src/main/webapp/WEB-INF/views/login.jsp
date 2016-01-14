<%--
  Created by IntelliJ IDEA.
  User: romit
  Date: 1/14/16
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Login</h1>
<form name="jspForm" method="POST" action="Login" style="margin: 0 auto">
  <table>
    <tbody>
    <tr>
      <td>Username</td>
      <td><input type="text" name="username"></td>
    </tr>
    <tr>
      <td>password</td>
      <td><input type="password" name="password"></td>
    </tr>
    </tbody>
  </table>
  <br/>
  <input type="submit" value="Submit">
</form>
</body>
</html>
