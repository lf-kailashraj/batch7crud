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
    <label>Name:</label> <input name="name" type="text" required> <br/>
    <label>Address :</label> <input name="address" type="text" required> <br/>
    <label>Department:</label> <input name="department" type="text" required> <br/>
    <label>Position:</label> <input name="position" type="text" required> <br/>
    <label>Salary:</label> <input name="salary" type="text" required> <br/>
    <input type="submit" value="submit">
  </form>
</body>
</html>
