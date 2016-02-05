<%--
  Created by IntelliJ IDEA.
  User: sagarmatha
  Date: 2/2/16
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/home"/>
  <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>--%>
  <link href="static/css/reset.css" rel="stylesheet">
  <link href="static/css/style.css" rel="stylesheet">
  <title>SMS</title>
</head>
<body>
  <%
  //allow access only if session exists
  String user = (String) session.getAttribute("user");
%>
<div class="header">
  <div class="nav group">
    <div class="user-field">
      <h3>Welcome <%=user %>
    </div>
    <div class="logout-field">
      <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout"/>
      </form>
    </div>
  </div>
</div>
<div class="main-body">


