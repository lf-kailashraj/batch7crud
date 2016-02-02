<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: achyut
  Date: 1/27/16
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
  <title>Add New Employee</title>
  <base href="${pageContext.request.contextPath}/">
</head>
<body>
  <h1>Hello fill up this form</h1>
  <form action="/employee/add" method="post">
    <label>Name:</label> <input name="name" type="text" value="${param.name}"> <c:out value="${errors['name']}"></c:out> <br/>
    <label>Address :</label> <input name="address" type="text" value="${param.address}"> <c:out value="${errors['address']}"></c:out> <br/>
    <label>Department:</label> <input name="department" type="text" value="${param.department}"> <c:out value="${errors['department']}"></c:out> <br/>
    <label>Position:</label> <input name="position" type="text" value="${param.position}"> <c:out value="${errors['position']}"></c:out> <br/>
    <label>Salary:</label> <input name="salary" type="text" value="${param.salary}"> <c:out value="${errors['salary']}"></c:out> <br/>
    <input type="submit" value="submit">
  </form>
</body>
</html>
