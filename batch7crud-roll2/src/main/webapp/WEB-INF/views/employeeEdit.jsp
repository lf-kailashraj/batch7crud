<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/18/16
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Edit Page</title>
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
                <div class="body-title">Edit Details of the Employee</div>
                <div class="employee-form clearfix">
                    <form name="jspForm" method="POST" action="employees/${employee.id}/edit">
                        <ul>
                            <li><span class="name-field">Name:</span>
                                <span class="input-field"><input type="text" name="name" value="${employee.name}"></span> <span
                                        class="error-message">${validationError.name}</span>
                            </li>
                            <li><span class="name-field">Address:</span>
                                <span class="input-field"><input type="text" name="address" value="${employee.address}"></span><span
                                        class="error-message">${validationError.address}
                                </span></li>
                            <li><span class="name-field">Email:</span>
                                <span class="input-field"><input type="text" name="email" value="${employee.email}"></span><span
                                        class="error-message">${validationError.email}</span>
                            </li>
                            <li><span class="name-field"> Contact Number:</span>
                                <span class="input-field"><input type="text" name="contact" value="${employee.contact}"></span><span
                                        class="error-message">${validationError.contact}</span>
                            </li>
                        </ul>
                        <input type="submit" value="Edit">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-wrapper clearfix">Copyright 2016, Leapfrog Technology, Inc</div>
</div>
</body>
</html>
