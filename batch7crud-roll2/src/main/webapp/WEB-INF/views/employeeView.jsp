<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/20/16
  Time: 4:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>View Employee</title>
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
                <div class="bodyTitle">Employee Details</div>
                <div class="viewEmployee">
                    <ul>
                        <li>Name:<span>${employee.getName()}</span></li>
                        <li>Address:<span>${employee.getAddress()}</span></li>
                        <li>Email:<span>${employee.getEmail()}</span></li>
                        <li>Contact Number:<span>${employee.getContact()}</span></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="footerWrapper clearfix">Copyright 2016, Leapfrog Technology, Inc</div>
</div>
</body>
</html>
