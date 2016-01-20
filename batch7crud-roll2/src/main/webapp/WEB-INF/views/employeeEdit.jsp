<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/18/16
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Edit Page</title>
</head>
<body>
<h1>Edit Employee information</h1>

<form name="jspForm" method="POST" action="employees/${employee.getId()}/edit">
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="${employee.getName()}"></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="${employee.getAddress()}"></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="${employee.getEmail()}"></td>
        </tr>
        <tr>
            <td>Contact Number:</td>
            <td><input type="number" name="contact" value="${employee.getContact()}"></td>
        </tr>
        </tbody>
    </table>
    <input type="reset" value="Reset">
    <input type="submit" value="Edit">
</form>
</body>
</html>
