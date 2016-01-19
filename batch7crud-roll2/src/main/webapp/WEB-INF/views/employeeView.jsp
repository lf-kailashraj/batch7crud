<%--
  Created by IntelliJ IDEA.
  User: romit
  Date: 1/20/16
  Time: 4:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>View Employee</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <td>Name:</td>
        <td>${employee.getName()}</td>
    </tr>
    <tr>
        <td>Address:</td>
        <td>${employee.getAddress()}</td>
    </tr>
    <tr>
        <td>Email:</td>
        <td>${employee.getEmail()}</td>
    </tr>
    <tr>
        <td>Contact Number:</td>
        <td>${employee.getContact()}</td>
    </tr>
    <tr>
        <td><a href="employees">Go Back</a></td>
    </tr>
    </tbody>
</table>
</body>
</html>
