<%--
  Created by IntelliJ IDEA.
  User: leapfrog
  Date: 1/19/16
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href = "${pageContext.request.contextPath}/">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title></title>
</head>
<body>
<form action="/Students/${student.id}/edit" method="post">
    Name: <input type="text" name="name" value="${student.name}"/>
    Address: <input type="text" name="address" value="${student.address}"/>
    Roll: <input type="text" name="roll" value="${student.roll}"/>
    <input type="submit"/>
</form>
<div><a href="/Students">View All</a></div>

</body>
</html>
