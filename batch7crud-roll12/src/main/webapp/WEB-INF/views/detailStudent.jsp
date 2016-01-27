<%--
  Created by IntelliJ IDEA.
  User: sagarmatha
  Date: 1/28/16
  Time: 12:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>SMS | ${student.getFirstName()} | Details</title>
  <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
  <h2>Details</h2>

  <div>
    <label>Name: </label>
    <span>${student.getFirstName()} ${student.getLastName()}</span>
  </div>
  <div>
    <label>Age: </label>
    <span>${student.getAge()}</span>
  </div>
  <div>
    <label>Address: </label>
    <span>${student.getAddress()}</span>
  </div>
  <a href="students/${student.getId()}/edit" class="edit">Edit info</a>
  <a href="students/${student.getId()}/delete" class="delete">Delete record</a>

</body>
</html>
