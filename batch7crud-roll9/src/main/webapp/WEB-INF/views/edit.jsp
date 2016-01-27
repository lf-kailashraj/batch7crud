<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/18/16
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Details</title>
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<form method="POST" action="students/${student.getId()}/edit">
    <label>First Name</label>
    <input type="text" value="${student.getFirstName()}" name="fname"/>
    <label>Middle Name</label>
    <input type="text" value="${student.getMiddleName()}" name="mname"/>
    <label>Last Name</label>
    <input type="text" value="${student.getLastName()}" name="lname"/>
    <label>Address</label>
    <input type="text" value="${student.getAddress()}" name="address"/>
    <label>Grade</label>
    <input type="text" value="${student.getGrade()}" name="grade"/>
    <input type="submit"/>
</form>
</body>
</html>
