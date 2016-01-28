<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binod <binodshrestha@lftechnology.com>
  Date: 1/14/16
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Students</title>
</head>
<body>
<div><a href="/auth/logout">logout</a></div>
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
      <td><a class="delete" href="students/${student.id}/delete">delete</a></td>
    </tr>
  </c:forEach>
</table>

<div>
  <c:if test="${currentPage != 1}">
    <a href="students?page=${currentPage-1}">prev</a>
  </c:if>
  <c:forEach begin="1" end="${totalPages}" var="i">
    <a href="students?page=${i}">${i}</a>
  </c:forEach>
  <c:if test="${currentPage != totalPages}">
    <a href="students?page=${currentPage + 1}">next</a>
  </c:if>
</div>


<a href="students/create">add student</a>

<script src="js/index.js"></script>
<script type="text/javascript">
  var deleteBtn = document.querySelectorAll("a.delete");

  for (var i = 0; i < deleteBtn.length; i++) {
    deleteBtn[i].onclick = function (e) {
      e.preventDefault();
      var href = this.getAttribute("href");
      var confirmation = confirm("Do you want to delete?");

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
