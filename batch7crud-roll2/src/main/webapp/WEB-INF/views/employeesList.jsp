<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: romit
  Date: 1/14/16
  Time: 2:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Display Page</title>
</head>
<body>

<h1>Form Data</h1>
<table border="1px solid black">
    <tbody>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Address</td>
        <td>Email</td>
        <td>Contact</td>
        <td colspan="2">Operations</td>
    </tr>
    <c:forEach items="${employeeList}" var="employee" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${employee.getName()}</td>
            <td>${employee.getAddress()}</td>
            <td>${employee.getEmail()}</td>
            <td>${employee.getContact()}</td>
            <td><a href="employees/${employee.getId()}/edit" class="edit">Edit</a></td>
            <td><a href="employees/${employee.getId()}/delete" class="delete">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="employees/create">Add Employee</a>
<script>
    var deleteElement = document.querySelector('a.delete');
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
