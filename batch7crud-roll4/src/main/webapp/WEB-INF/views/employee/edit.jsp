<%--
  Created by IntelliJ IDEA.
  User: Pratish Shrestha <pratishshrestha@lftechnology.com>
  Date: 1/18/16
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Add User</title>
</head>
<body>
<h1>Edit User</h1>
<form action="employees/${employee.getId()}/edit" method="post">
  First Name: <input type="text" name="firstName" value="${employee.firstName}">  ${errors.firstName}
  <br/>
  Last Name: <input type="text" name="lastName" value="${employee.lastName}">  ${errors.lastName}
  <br/>
  Station: <input type="text" name="station" value="${employee.station}">  ${errors.station}
  <br/>
  <input type="submit" name="submit" value="Save">
</form>

</body>
</html>
