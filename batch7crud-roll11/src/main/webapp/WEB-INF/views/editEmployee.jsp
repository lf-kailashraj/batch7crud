<%--
  Created by IntelliJ IDEA.
  User: achyut
  Date: 1/27/16
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Edit Employee</title>
  <base href="${pageContext.request.contextPath}/">
</head>
<body>
<h1>Hello fill up this form</h1>
  <form action="/employee/${employee.empId}/edit" method="post">
    <label>Name:</label> <input name="name" type="text" required value="${employee.name}"> <br/>
    <label>Address :</label> <input name="address" type="text" required value="${employee.address}"> <br/>
    <label>Department:</label> <input name="department" type="text" required value="${employee.department}"> <br/>
    <label>Position:</label> <input name="position" type="text" required value="${employee.position}"> <br/>
    <label>Salary:</label> <input name="salary" type="text" required value="${employee.salary}"> <br/>
    <input type="submit" value="submit">
  </form>
</body>
</html>