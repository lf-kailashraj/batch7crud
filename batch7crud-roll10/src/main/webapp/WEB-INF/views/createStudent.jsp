<%--
  Created by IntelliJ IDEA.
  User: binodnme
  Date: 1/14/16
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>create student</title>
</head>
<body>
<div>
  <form action="students/create" method="post">
    <label>name</label><input type="text" name="name" value="${param.name}"><span>${errors.name}</span><br>

    <label>address</label><input type="text" name="address" value="${param.address}"><span>${errors.address}</span><br>

    <label>dob</label><input type="date" name="dob" value="${param.dob}"><span>${errors.dob}</span><br>

    <label>department</label><input type="text" name="department" value="${param.department}"><span>${errors.department}</span><br>

    <label>batch</label><input type="text" name="batch" value="${param.batch}"><span>${errors.batch}</span><br>

    <label>roll</label><input type="number" name="roll" value="${param.roll}"><span>${errors.roll}</span><br>

    <input type="submit">
  </form>

  <p>${errorMessage}</p>

</div>
</body>
</html>
