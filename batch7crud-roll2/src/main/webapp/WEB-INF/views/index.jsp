<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/18/16
  Time: 12:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Information Management</title>
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
                <h1 class="textGreen">Welcome to Employee Management System</h1>
            </div>
        </div>
    </div>
    <div class="footerWrapper clearfix">Copyright 2016, Leapfrog Technology, Inc</div>
</div>
</body>
</html>
