<%--
  Created by IntelliJ IDEA.
  User: pratishshr
  Date: 1/14/16
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Employees</title>
</head>
<body>
    <h1>List of Employees</h1>
    <a href="/employees/create">Add Employee</a>

    <table border="1">
        <tr>
            <td>SN</td>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Station</td>
            <td>Action</td>
        </tr>

        <c:forEach var="employee" items="${employees}" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${employee.getFirstName()}</td>
            <td>${employee.getLastName()}</td>
            <td>${employee.getStation()}</td>
            <td>
                <a href="${employee.getId()}/edit">Edit</a>
                <a href="${employee.getId()}/delete">Delete</a>
            </td>
        </tr>
        </c:forEach>

    </table>

</body>
</html>
