<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/14/16
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Student Registration</title>
    <base href="/students"/>

</head>
<body>
<form action="${pageContext.request.contextPath}/students/create" method="POST">
    <label for="fname">First Name: </label>

    <div><input type="text" required="required" placeholder="First Name" name="fname" id="fname"/></div>
    <label for="mname">Middle Name: </label>

    <div><input type="text" required="required" placeholder="Middle Name" name="mname" id="mname"/></div>
    <label for="lname">Last Name: </label>

    <div><input type="text" required="required" placeholder="Last Name" name="lname" id="lname"/></div>
    <label for="address">Address: </label>

    <div><input type="text" required="required" placeholder="Address" name="address" id="address"/></div>
    <label for="grade">Grade: </label>

    <div><select name="grade" id="grade">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
    </select></div>
    <input type="submit"/>
</form>
</body>
</html>
