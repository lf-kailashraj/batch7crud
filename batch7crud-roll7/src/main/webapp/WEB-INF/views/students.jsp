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
    <base href = "${pageContext.request.contextPath}/">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Students</title>
</head>
<body>
<div><a href="/Students/NewEntry">New Entry</a></div>


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
    <c:forEach items="${students}" var="student">
        <tr>
            <td><c:out value="${student.id}"/></td>
            <td><c:out value="${student.name}"/></td>
            <td><c:out value="${student.address}"/></td>
            <td><c:out value="${student.roll}"/></td>
            <td><a href="Students/${student.id}">View</a></td>
            <td><a href="/Students/${student.id}/edit">Edit</a></td>
            <td><a href="/Students/${student.roll}/delete" class="delete">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<c:if test="${pageNum > 1}">
    <a href="Students?page=${pageNum - 1}">Previous</a>
</c:if>
${pageNum}
<c:if test="${(pageNum * pageSize) < totalStudents}">
    <a href="Students?page=${pageNum + 1}">Next</a>
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
