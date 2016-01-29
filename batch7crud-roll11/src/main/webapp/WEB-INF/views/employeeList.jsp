<%--
  Created by IntelliJ IDEA.
  User: achyut
  Date: 1/27/16
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>List  of Employees</title>
    <base href="${pageContext.request.contextPath}/">
</head>
<body>
<a href="/employee/add">Add Employee</a>
<h1>Employee List</h1>
<table>
    <thead>
    <tr>
        <th>Emp.No</th>
        <th>Name</th>
        <th>Address</th>
        <th>Department</th>
        <th>Position</th>
        <th>Salary</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="emp">
        <tr>
            <td><c:out value="${emp.empId}"></c:out></td>
            <td><c:out value="${emp.name}"></c:out></td>
            <td><c:out value="${emp.address}"></c:out></td>
            <td><c:out value="${emp.department}"></c:out></td>
            <td><c:out value="${emp.position}"></c:out></td>
            <td><c:out value="${emp.salary}"></c:out></td>
            <td><a href="employee/${emp.empId}/edit">Edit</a>
                <a href="employee/${emp.empId}/delete" class="deleteEmployee">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
    <script>
    var deleteElement = document.querySelectorAll('a.deleteEmployee');
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
</html>
