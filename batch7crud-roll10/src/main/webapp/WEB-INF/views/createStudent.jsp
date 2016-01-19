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
    <title>create student</title>
</head>
<body>
  <div>
    <form action="create" method="post">
        <label>name</label><input type="text" name="name" value="${param.name}"><br>
        <label>address</label><input type="text" name="address" value="${param.address}"><br>
        <label>dob</label><input type="date" name="dob" value="${param.dob}"><br>

        <label>department</label><input type="text" name="department" value="${param.department}"><br>
        <label>batch</label><input type="text" name="batch" value="${param.batch}"><br>
        <label>roll</label><input type="number" name="roll" value="${param.roll}"><br>

        <input type="submit">
    </form>

      <p>${errorMessage}</p>

  </div>
</body>
</html>
