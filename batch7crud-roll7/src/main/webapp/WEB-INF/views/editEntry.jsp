<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com>
  Date: 1/19/16
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title>Edit</title>
</head>
<body>
<form action="students/${student.id}/edit" method="post">
  Name: <input type="text" name="name" value="${student.name}"/>
  Address: <input type="text" name="address" value="${student.address}"/>
  Roll: <input type="text" name="roll" value="${student.roll}"/>
  <input type="submit"/>
</form>
${error}
<c:if test="${error != null}">
  <p>Error in entered roll</p>
</c:if>

<div><a href="students">View All</a></div>

</body>
</html>
