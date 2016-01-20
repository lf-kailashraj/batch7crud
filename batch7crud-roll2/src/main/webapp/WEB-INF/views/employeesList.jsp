<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/14/16
  Time: 2:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Employee List Page</title>
</head>
<body>

<h1>Employee List</h1>
<table border="1px solid black">
    <tbody>
    <a>
        <td>Id</td>
        <td>Name</td>
        <td>Address</td>
        <td>Email</td>
        <td>Contact</td>
        <td colspan="3">Operations</td>
        </tr>
        <c:if test="${currentPage == 1}">
            <c:set var="count" value="1"/>
        </c:if>
        <c:if test="${currentPage > 1}">
            <c:set var="count" value="${(currentPage -1)*10 +1}"/>
        </c:if>
        <c:forEach items="${employeeList}" var="employee">
        <tr>
            <td>${count}</td>
            <td>${employee.getName()}</td>
            <td>${employee.getAddress()}</td>
            <td>${employee.getEmail()}</td>
            <td>${employee.getContact()}</td>
            <td><a href="employees/${employee.getId()}">View</a></td>
            <td><a href="employees/${employee.getId()}/edit">Edit</a></td>
            <td><a href="employees/${employee.getId()}/delete" class="delete">Delete</a></td>
        </tr>
            <c:set var="count" value="${count+1}"/>
        </c:forEach>
    </tbody>
</table>
<table>
    <tbody>
    <tr>
        <c:if test="${currentPage != 1}">
            <td><a href="employees?page=${currentPage - 1}">Previous</a></td>
        </c:if>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="employees?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt noOfPages}">
            <td><a href="employees?page=${currentPage + 1}">Next</a></td>
        </c:if>
    </tr>
    </tbody>
</table>
<a href="employees/create">Add Employee</a>
<script>
    var deleteElement = document.querySelectorAll('a.delete');
    for (var i = 0; i < deleteElement.length; i++) {
        deleteElement[i].onclick = function (e) {
            e.preventDefault();
            var link = this.href;
            var confirmStatus = confirm('Are you sure to delete?');
            if (confirmStatus == true) {
                var formElement = document.createElement('form');
                formElement.setAttribute('action', link);
                formElement.setAttribute('method', 'POST');
                document.body.appendChild(formElement);
                formElement.submit();
            }
        }
    }
</script>
</body>
</html>
