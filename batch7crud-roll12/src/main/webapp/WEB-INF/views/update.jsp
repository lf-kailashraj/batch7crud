<%--
  Created by IntelliJ IDEA.
  User: sagarmatha
  Date: 1/28/16
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Update Details</title>
  <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
skfhnd
<form method="POST" action="students/${student.studentID}/update">
  First Name :
  <input type="text" value="${student.firstName}" name="firstName"/>
  <span class="error">${errorMessege.fname}</span><br />
  Last Name :
  <input type="text" value="${student.lastName}" name="lastName"/>
  <span class="error">${errorMessege.lname}</span><br />
  Age :
  <input type="text" value="${student.age}" name="age"/>
  <span class="error">${errorMessege.age}</span><br />
  Address :
  <input type="text" value="${student.address}" name="address"/>
  <span class="error">${errorMessege.address}</span><br />
  <input type="submit"/>
</form>
</body>
</html>
