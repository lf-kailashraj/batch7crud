<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 2/2/16
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>
        <%= request.getParameter("title") %>
    </title>
    <link type="text/css" rel="stylesheet" href="css/reset.css"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div class="main-wrapper clearfix">
    <div class="header-wrapper clearfix">
        <div class="logo"> Employee Management System</div>
        <div class="loggedin-user">${user}</div>
    </div>