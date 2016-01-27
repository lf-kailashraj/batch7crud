<%--
  Created by IntelliJ IDEA.
  User: sagarmatha
  Date: 1/28/16
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Details</title>
  <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<form method="POST" action="students/${student.getStudentId()}/edit">
  First Name :
  <input type="text" value="${student.getFirstName()}" name="fname"/>
  Last Name :
  <input type="text" value="${student.getLastName()}" name="lname"/>
  Age :
  <input type="text" value="${student.getAge()}" name="age"/>
  Address :
  <input type="text" value="${student.getAddress()}" name="address"/>
  <input type="submit"/>
</form>
</body>
</html>
