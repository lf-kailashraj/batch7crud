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
  <base href="/students" />

</head>
<body>
  <form action="${pageContext.request.contextPath}/students/create" method="POST">
    <input type="text" required="required" placeholder="First Name" name="fname" />
    <input type="text" required="required" placeholder="Middle Name" name="mname" />
    <input type="text" required="required" placeholder="Last Name" name="lname" />
    <input type="text" required="required" placeholder="Address" name="address" />
    <select name="grade">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
    </select>
   <input type="submit"/>
  </form>
</body>
</html>
