<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/19/16
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SMIS | ${student.getFirstName()} | Details</title>
    <base href="${pageContext.request.contextPath}/"/>

</head>
<body>
<h2>Detail View</h2>
<label>Name: </label><span>${student.getFirstName()} ${student.getMiddleName()} ${student.getLastName()}</span>
<label>Address: </label> <span>${student.getAddress()}</span>
<label>Grade: </label><span>${student.getGrade()}</span>
<%--<span>--%>
<%--<c:forEach begin="1" end="${total}" var="counter">--%>
<%--<span><a href = "students?page=${counter}"> ${counter} </a></span>--%>
<%--</c:forEach>--%>
<%--</span>--%>
<td><a href="students/${student.getId()}/edit" class="edit">Edit This</a></td>

<td><a href="students/${student.getId()}/delete" class="delete">Delete This</a></td>

</body>
</html>
