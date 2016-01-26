<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com>
  Date: 1/18/16
  Time: 4:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title>New Entry</title>
</head>
<body>
<form action="students/newEntry" method="post">
  <input type="text" name="name" placeholder="Name" value="${param.name}"/>
  <input type="text" name="address" placeholder="Address" value="${param.address}"/>
  <input type="text" name="roll" placeholder="Roll"/>
  <input type="submit"/>
</form>
${error}
<c:if test="${error != null}">
  <p>Error in entered roll</p>
</c:if>
<div><a href="students">View All</a></div>
</body>
</html>
