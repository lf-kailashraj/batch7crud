<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/14/16
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Create Page</title>
    <link type="text/css" rel="stylesheet" href="css/reset.css"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div class="mainWrapper clearfix">
    <div class="headerWrapper clearfix">
        <div class="logo">Employee Management System</div>
        <div class="loggedinUser">Welcome,Romit Amgai</div>
    </div>
    <div class="bodyWrapper clearfix">
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
                <div class="bodyTitle">Add Details of New Employee</div>
                <div class="employeeForm clearfix">
                    <form name="jspForm" method="POST" action="employees/create">
                        <ul>
                            <li><span class="nameFields">Name:</span>
                                <span class="inputFields"><input type="text" name="name" value="${employee.name}"></span> <span
                                        class="errorMessage">${validationError.name}</span>
                            </li>
                            <li><span class="nameFields">Address:</span>
                                <span class="inputFields"><input type="text" name="address" value="${employee.address}"></span><span
                                        class="errorMessage">${validationError.address}
                                </span></li>
                            <li><span class="nameFields">Email:</span>
                                <span class="inputFields"><input type="text" name="email" value="${employee.email}"></span><span
                                        class="errorMessage">${validationError.email}</span>
                            </li>
                            <li><span class="nameFields"> Contact Number:</span>
                                <span class="inputFields"><input type="text" name="contact" value="${employee.contact}"></span><span
                                        class="errorMessage">${validationError.contact}</span>
                            </li>
                        </ul>
                        <input type="submit" value="Submit">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="footerWrapper clearfix">Copyright 2016, Leapfrog Technology, Inc</div>
</div>
</body>
</html>
