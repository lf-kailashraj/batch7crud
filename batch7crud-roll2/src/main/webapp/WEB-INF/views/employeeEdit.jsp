<%--
  Created by IntelliJ IDEA.
  User: romit
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
<h1>Edit information</h1>

<form name="jspForm" method="POST" action="employees/${employee.getId()}/edit">
    Name: <input type="text" name="name" value="${employee.getName()}">
    <br/>
    Address: <input type="text" name="address" value="${employee.getAddress()}">
    <br/>
    Email: <input type="text" name="email" value="${employee.getEmail()}">
    <br/>
    Contact Number: <input type="number" name="contact" value="${employee.getContact()}">
    <br/>
    <input type="reset" value="Reset">
    <input type="submit" value="Edit">
</form>
</body>
</html>
