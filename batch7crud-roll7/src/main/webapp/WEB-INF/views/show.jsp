<%--
  Created by IntelliJ IDEA.
  User: leapfrog
  Date: 1/21/16
  Time: 4:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title></title>
</head>
<body>
Id : ${student.getId()}<br>
Name : ${student.getName()}<br>
Address : ${student.getAddress()}<br>
Roll : ${student.getRoll()}<br>

<a href="/Students">View All</a>

</body>
</html>
