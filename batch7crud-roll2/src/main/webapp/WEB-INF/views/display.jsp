<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: romit
  Date: 1/14/16
  Time: 2:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Display Page</title>
</head>
<body>

<h1>Form Data</h1>
<table border="1px solid black">
  <tbody>
  <tr>
    <td>Id</td>
    <td>Name</td>
    <td>Address</td>
    <td>Email</td>
    <td>Contact</td>
  </tr>
  <c:forEach items="${employeeList}" var="employee" varStatus="counter">
  <tr>
    <td>${counter.count}</td>
    <td>${employee.getName()}</td>
    <td>${employee.getAddress()}</td>
    <td>${employee.getEmail()}</td>
    <td>${employee.getContact()}</td>
    <td><a href="/">Edit</a></td>
    <td><a href="/">Delete</a></td>
  </tr>
  </c:forEach>
  </tbody>
</table>
<a href="Create">Add Employee</a>
</body>
</html>
