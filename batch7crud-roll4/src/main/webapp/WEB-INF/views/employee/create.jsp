<%--
  Created by IntelliJ IDEA.
  User: Pratish Shrestha <pratishshrestha@lftechnology.com>
  Date: 1/14/16
  Time: 10:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Add User</title>
</head>
<body>
<h1>Add User</h1>

    <form action="employees/create" method="post">
        First Name: <input type="text" name="firstName" value="${employee.firstName}"> ${errors.firstName}
        <br/>
        Last Name: <input type="text" name="lastName" value="${employee.lastName}"}> ${errors.lastName}
        <br/>
        Station: <input type="text" name="station" value="${employee.station}"> ${errors.station}
        <br/>
        <input type="submit" name="submit" value="submit">
    </form>


</body>
</html>
