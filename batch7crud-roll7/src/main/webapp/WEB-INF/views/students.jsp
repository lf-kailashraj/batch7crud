<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com>
  Date: 1/18/16
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title>Students</title>
</head>
<body>
<div><a href="students/newEntry">New Entry</a></div>


<table border="1">
  <tr>
    <td>Id</td>
    <td>Name</td>
    <td>Address</td>
    <td>Roll</td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <c:forEach items="${students}" var="book">
    <tr>
      <td><c:out value="${book.id}"/></td>
      <td><c:out value="${book.name}"/></td>
      <td><c:out value="${book.address}"/></td>
      <td><c:out value="${book.roll}"/></td>
      <td><a href="students/${book.id}">View</a></td>
      <td><a href="students/${book.id}/edit">Edit</a></td>
      <td><a href="students/${book.id}/delete" class="delete">Delete</a></td>
    </tr>
  </c:forEach>
</table>

<c:if test="${pageNum > 1}">
  <a href="students?page=${pageNum - 1}">Previous</a>
</c:if>

<c:forEach var="i" begin="1" end="${numberOfPages}">
  <a href="students?page=${i}">${i}</a>
</c:forEach>

<c:if test="${(pageNum * pageSize) < totalStudents}">
  <a href="students?page=${pageNum + 1}">Next</a>
</c:if>

<script>

  var deleteBtn = document.getElementsByClassName("delete");

  for (var i = 0; i < deleteBtn.length; i++) {
    deleteBtn[i].onclick = function (e) {
      e.preventDefault();
      var href = this.getAttribute("href");
      var confirmation = confirm("Are you sure?");

      if (confirmation == true) {
        var form = document.createElement("form");
        form.action = href;
        form.method = "post";
        form.submit();
      }
    }
  }

</script>
</body>
</html>
