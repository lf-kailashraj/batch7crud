<%--
  Created by IntelliJ IDEA.
  User: kiran
  Date: 1/17/16
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>update</title>
</head>
<body>

<form action="employees/<c:out value="${employee.id}"/>/edit" method="post">
    <label>Name :</label>
    <input type="text" name="userName" value="<c:out value="${employee.userName}"/>">
    <label>Password :</label>
    <input type="password" name="password" value="<c:out value="${employee.password}"/>">
    <label>Full Name :</label>
    <input type="text" name="fullName" value="<c:out value="${employee.fullName}"/>">
    <label>Department :</label>
    <input type="text" name="department" value="<c:out value="${employee.department}"/>">
    <label>Address</label>
    <input type="text" name="address" value="<c:out value="${employee.address}"/>">
    <input type="submit" value="update">
</form>

</body>
</html>
