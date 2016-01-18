<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binodnme
  Date: 1/14/16
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>students</title>
</head>
<body>
  <p>here are students</p>
  <table>
      <tr>
          <th>NAME</th>
          <th>ROLL</th>
          <th>ADDRESS</th>
          <th>DEPARTMENT</th>
          <th>BATCH</th>
      </tr>
      <c:forEach items="${studentList}" var="student">
          <tr>
              <td>${student.name}</td>
              <td>${student.roll}</td>
              <td>${student.address}</td>
              <td>${student.department}</td>
              <td>${student.batch}</td>

              <td><a href="students/${student.id}/edit">edit</a></td>
              <%--<td><a href="students/delete">delete</a></td>--%>
          </tr>
      </c:forEach>
  </table>

    <a href="students/add">add student</a>
</body>
</html>
