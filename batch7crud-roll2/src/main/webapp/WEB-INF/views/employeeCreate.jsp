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

<form name="jspForm" method="POST" action="employees/create">
    Name: <input type="text" name="name">
    <br/>
    Address: <input type="text" name="address">
    <br/>
    Email:<input type="text" name="email">
    <br>
    Contact Number: <input type="number" name="contact">
    <br/>
    <input type="reset" value="Reset">
    <input type="submit" value="Submit">
</form>
</body>
</html>
