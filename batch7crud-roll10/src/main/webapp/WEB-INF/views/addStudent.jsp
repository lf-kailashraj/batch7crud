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
    <form action="add" method="post">
        <label>name</label><input type="text" name="name"><br>
        <label>address</label><input type="text" name="address"><br>
        <label>dob</label><input type="date" name="dob"><br>

        <label>department</label><input type="text" name="department"><br>
        <label>batch</label><input type="text" name="batch"><br>
        <label>roll</label><input type="number" name="roll"><br>

<input type="submit">
    </form>

  </div>
</body>
</html>
