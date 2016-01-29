<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/18/16
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Users</title>
</head>
<body>
  <%--<c:choose>--%>
  <%--<c:when test="${success}">--%>
  <%--Welcome user!--%>
  <%--</c:when>--%>
  <%--</c:choose>--%>
  <div>
    <p><a href="/employees">Manage Employees</a></p>
    <p><a href="#">Manage your Deets</a></p>
    <p><form action="/users/logout" method="post">
    <input type="submit" value="Logout" >
  </form></p>
  </div>
</body>
</html>
