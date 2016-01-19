<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: leapfrog
  Date: 1/18/16
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
  <table>
    <c:forEach items="${students}" var="student">
      <tr>
        <td><c:out value="${student.roll}" /></td>
        <td><c:out value="${student.name}" /></td>
        <td><c:out value="${student.address}" /></td>
        <td><a href = "/Students/${student.roll}/edit">Edit</a></td>
      </tr>
    </c:forEach>
  </table>
  <div><a href="/Students/NewEntry">New Entry</a></div>
</body>
</html>