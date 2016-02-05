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
  <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
  <link rel="stylesheet" type="text/css" href="static/css/layout.css" />

  <title>Students</title>
</head>
<body>
<%--<div><a href="students/newEntry">New Entry</a></div>--%>


<%--<table border="1">--%>
  <%--<tr>--%>
    <%--<td>Id</td>--%>
    <%--<td>Name</td>--%>
    <%--<td>Address</td>--%>
    <%--<td>Roll</td>--%>
    <%--<td></td>--%>
    <%--<td></td>--%>
    <%--<td></td>--%>
  <%--</tr>--%>
  <%--<c:forEach items="${students}" var="student">--%>
    <%--<tr>--%>
      <%--<td><c:out value="${student.id}"/></td>--%>
      <%--<td><c:out value="${student.name}"/></td>--%>
      <%--<td><c:out value="${student.address}"/></td>--%>
      <%--<td><c:out value="${student.roll}"/></td>--%>
      <%--<td><a href="students/${student.id}">View</a></td>--%>
      <%--<td><a href="students/${student.id}/edit">Edit</a></td>--%>
      <%--<td><a href="students/${student.id}/delete" class="delete">Delete</a></td>--%>
    <%--</tr>--%>
  <%--</c:forEach>--%>
<%--</table>--%>

<div class="header">
  <div class="header-text">
    SHREE KAMANA BOARDING HIGH SCHOOL
  </div>

</div>

<div class="mainbody clr">
  <div class="navigation">
    <ul>
      <li><a href="students/create">New Entry</a></li>
      <li><a href="logOut">Log Out</a></li>
    </ul>
  </div>
  <div class = "content">
    LIST OF STUDENTS
    <table class="list">
      <col width="50">
      <col width="300">
      <col width="200">
      <col width="80">
      <col width="130">
      <col width="80">
      <col width="80">
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Address</th>
        <th>Roll</th>
        <th colspan="3">Operations</th>
      </tr>
      <c:forEach items="${students}" var="student">
        <tr>
          <td height="40"><c:out value="${student.id}"/></td>
          <td height="40"><c:out value="${student.name}"/></td>
          <td height="40"><c:out value="${student.address}"/></td>
          <td height="40"><c:out value="${student.roll}"/></td>
          <td height="40"><a href="students/${student.id}">View</a></td>
          <td height="40"><a href="students/${student.id}/edit">Edit</a></td>
          <td height="40"><a href="students/${student.id}/delete" class="delete">Delete</a></td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${pageNum > 1}">
      <a class="change-page" href="students?page=${pageNum - 1}">Previous</a>
    </c:if>

    <c:forEach var="i" begin="1" end="${numberOfPages}">
      <a class="change-page" href="students?page=${i}">${i}</a>
    </c:forEach>

    <c:if test="${(pageNum * pageSize) < totalStudents}">
      <a class="change-page" href="students?page=${pageNum + 1}">Next</a>
    </c:if>

  </div>

</div>

<div class="footer">
  <div class="copyright">Copyright</div>
  <div class="contact">Contact</div>
</div>


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
