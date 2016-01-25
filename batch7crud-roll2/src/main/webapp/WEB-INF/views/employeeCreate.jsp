<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/14/16
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Create Page</title>
</head>
<body>
<h1>Employee information form!</h1>

<p>Enter employee information</p>

<form name="jspForm" method="POST" action="employees/create">
    <table>
        <tbody>
        <td>Name:</td>
        <td><input type="text" name="name" value="${param.name}">${validationError.name}</td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><input type="text" name="address" value="${param.address}">${validationError.address}</td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" ${param.email}>${validationError.email}</td>
        </tr>
        <tr>
            <td>Contact Number:</td>
            <td><input type="number" name="contact" ${param.contact}>${validationError.contact}</td>
        </tr>
        </tbody>
    </table>
    <input type="reset" value="Reset">
    <input type="submit" value="Submit">
</form>
</body>
</html>
