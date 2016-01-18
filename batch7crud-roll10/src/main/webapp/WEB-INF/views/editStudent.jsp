<%--
  Created by IntelliJ IDEA.
  User: binodnme
  Date: 1/14/16
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <div>

    <form action="/students/${student.id}/edit" method="post">
      <label>name</label><input type="text" name="name" value="${student.name}"><br>
      <label>address</label><input type="text" name="address" value="${student.address}"><br>
      <label>dob</label><input type="date" name="dob" value="${student.dob}"><br>

      <label>department</label><input type="text" name="department" value="${student.department}"><br>
      <label>batch</label><input type="text" name="batch" value="${student.batch}"><br>
      <label>roll</label><input type="number" name="roll" value="${student.roll}"><br>

      <input type="submit">
    </form>
  </div>
</body>
</html>
