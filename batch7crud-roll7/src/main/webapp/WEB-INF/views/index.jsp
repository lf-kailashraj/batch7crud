<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
  <link rel="stylesheet" type="text/css" href="static/css/layout.css" />

  <title>SMS</title>
  <%
    response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibilit--%>
</head>
<body>
<%--<div>SMS</div>--%>
<%--<div><a href="students/newEntry">New Entry</a></div>--%>
<%--<div><a href="students">View All</a></div>--%>
<%--<div><a href="logOut">Log Out</a></div>--%>

<div class="header">
  <div class="header-text">
    SHREE KAMANA BOARDING HIGH SCHOOL
  </div>

</div>

<div class="mainbody clr">
  <div class="navigation">
    <ul>
      <li><a href="students/create">New Entry</a></li>
      <li><a href="students">View All</a></li>
      <li><a href="logOut">Log Out</a></li>
    </ul>
  </div>
  <div class = "content">
    Welcome to Student Management System
  </div>

</div>

<div class="footer">
  <div class="copyright ">Copyright</div>
  <div class="contact">Contact</div>
</div>

</body>
</html>
