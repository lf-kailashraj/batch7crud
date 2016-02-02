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
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>update</title>
</head>
<body>

<h2>Edit Form</h2>

<form action="employees/${employee.id}/edit" method="post">
    <div>
        <label>User Name :</label>
        <input type="text" name="userName" value="${employee.userName}">
        <span>${errors.userName}</span>
    </div>
    <div>
        <label>Password :</label>
        <input type="password" name="password" value="${employee.password}">
        <span>${errors.password}</span>
    </div>
    <div>
        <label>Full Name :</label>
        <input type="text" name="fullName" value="${employee.fullName}">
        <span>${errors.fullName}</span>
    </div>
    <div>
        <label>Department :</label>
        <input type="text" name="department" value="${employee.department}">
        <span>${errors.department}</span>
    </div>
    <div>
        <label>Address</label>
        <input type="text" name="address" value="${employee.address}">
        <span>${errors.address}</span>
    </div>

    <div>
        <label>Age</label>
        <input type="number" name="age" value="${employee.age}">
        <span>${errors.age}</span>
    </div>
    <input type="submit" value="update">
</form>

</body>
</html>
