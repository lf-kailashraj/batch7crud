<%--
  Created by IntelliJ IDEA.
  User: Pratish Shrestha <pratishshrestha@lftechnology.com>
  Date: 1/14/16
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Employees</title>
</head>
<h1>List of Employees</h1>
<a href="employees/create">Add Employee</a>

<table border="1">
    <tr>
        <td>SN</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Station</td>
        <td>Action</td>
    </tr>


    <c:set var="count" value="${(currentPage - 1) * 20 + 1}"/>

    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${count}</td>
            <td><a href="employees/${employee.getId()}">${employee.getFirstName()}</a></td>
            <td>${employee.getLastName()}</td>
            <td>${employee.getStation()}</td>
            <td>
                <a href="employees/${employee.getId()}/edit">Edit</a>
                <a class="deleteBtn" href="employees/${employee.getId()}/delete">Delete</a>
            </td>
        </tr>
        <c:set var="count" value="${count + 1}" scope="page"/>
    </c:forEach>
</table>

<c:if test="${currentPage != 1}">
    <a href="employees?page=${currentPage - 1}">Previous</a>
</c:if>

<c:forEach begin="1" end="${noOfPages}" var="i">
    <c:if test="${i == currentPage}">
        ${i}
    </c:if>
    <c:if test="${i != currentPage}">
        <a href="employees?page=${i}">${i}</a>
    </c:if>
</c:forEach>

<c:if test="${currentPage != noOfPages}">
    <a href="employees?page=${currentPage + 1}">Next</a>
</c:if>

<script>
    var deleteBtn = document.getElementsByClassName("deleteBtn");

    for (var i = 0; i < deleteBtn.length; i++) {
        deleteBtn[i].onclick = function (e) {
            e.preventDefault();
            var href = this.href;
            var confirmation = confirm("do you want to delete?");

            if (confirmation == true) {
                var form = document.createElement("form");

                form.action = href;
                form.method = "post";
                document.body.appendChild(form);

                form.submit();
            }
        }
    }

</script>
</body>
</html>
