<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/18/16
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Details</title>
  <base href="${pageContext.request.contextPath}/" />
</head>
<body>
<table>
  <form method="POST" action="students/${student.getId()}/edit" >
    <tr><td><label>First Name</label></td><td><input type="text" value="${student.getFirstName()}" name="fname"/></td></tr>
    <tr><td><label>Middle Name</label></td><td><input type="text" value="${student.getMiddleName()}" name="mname"/></td></tr>
    <tr><td><label>Last Name</label></td><td><input type="text" value="${student.getLastName()}" name="lname"/></td></tr>
    <tr><td><label>Address</label></td><td><input type="text" value="${student.getAddress()}" name="address"/></td></tr>
  <tr><td><label>Grade</label></td><td><input type="text" value="${student.getGrade()}" name="grade"/></td></tr>
    <tr><td><input type="submit" /></td></tr>
  </form>
</table>

</body>
</html>
