<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: leapfrog
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
<form action="Students/NewEntry" method="post">
  <input type="text" name="name" placeholder="Name" value="${param.name}"/>
  <input type="text" name="address" placeholder="Address"/>
  <input type="text" name="roll" placeholder="Roll"/>
  <input type="submit"/>
</form>
${error}
<c:if test="${error != null}">
  <p>you have error</p>
</c:if>
<div><a href="Students">View All</a></div>
</body>
</html>
