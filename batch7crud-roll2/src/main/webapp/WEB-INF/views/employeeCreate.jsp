<%--
  Created by IntelliJ IDEA.
  User: romit
  Date: 1/14/16
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Form Page</title>
</head>
<body>
<h1>User information form!</h1>

<p>Enter your information</p>

<form name="jspForm" method="POST" action="employees/create">
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>Contact Number:</td>
            <td><input type="number" name="contact"></td>
        </tr>
        </tbody>
    </table>
    <br/>
    <input type="reset" value="Reset">
    <input type="submit" value="Submit">
</form>
</body>
</html>
