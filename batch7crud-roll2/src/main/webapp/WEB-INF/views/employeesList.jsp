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
    <link type="text/css" rel="stylesheet" href="css/reset.css"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div class="main-wrapper clearfix">
    <div class="header-wrapper clearfix">
        <div class="logo">Employee Management System</div>
        <div class="loggedin-user">Welcome,Romit Amgai</div>
    </div>
    <div class="body-wrapper clearfix">
        <div class="col2-left clearfix">
            <div class="left">
                <ul>
                    <li><a href="employees">Employee List</a></li>
                    <li>
                        <form id="logout" action="authenticate/logout" method="post">
                            <input type="hidden" name="name" value="value"/>
                            <a onclick="document.getElementById('logout').submit();">Logout</a>
                        </form>
                    </li>
                </ul>
            </div>
            <div class="right">
                <div class="body-title">Employee List</div>
                <div class="addnew-employee"><a href="employees/create">Add New Employee</a></div>
                <div class="employee-list">
                    <table>
                        <tbody>
                        <tr>
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
                </div>
                <div class="pagination">
                    <ul>
                        <c:if test="${currentPage != 1}">
                            <li><a href="employees?page=${currentPage - 1}">Previous</a></li>
                        </c:if>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li>${i}</li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="employees?page=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${currentPage lt noOfPages}">
                            <li><a href="employees?page=${currentPage + 1}">Next</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-wrapper clearfix">Copyright 2016, Leapfrog Technology, Inc</div>
</div>
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
