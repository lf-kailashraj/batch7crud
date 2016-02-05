<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com>
  Date: 1/19/16
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="static/css/reset.css"/>
  <link rel="stylesheet" type="text/css" href="static/css/layout.css"/>

  <title>Edit</title>
</head>
<body>

<div class="header">
  <div class="header-text">
    SHREE KAMANA BOARDING HIGH SCHOOL
  </div>

</div>

<div class="mainbody clr">
  <div class="navigation">
    <ul>
      <li><a href="students">View All</a></li>
      <li><a href="logOut">Log Out</a></li>
    </ul>
  </div>
  <div class="content">
    <div class="login-container">
      Edit ENTRY FORM


      <form action="students/${student['id']}/edit" method="post">
        Name: <input id="name" type="text" name="name" value="${student['name']}"/><span class="error">${error.name}</span>

        <div>Address: <input id="address" type="text" name="address" value="${student['address']}"/><span
          class="error">${error.address}</span></div>
        Roll: <input id="roll" type="number" name="roll" value="${student['roll']}"/><span class="error">${error.roll}</span>
        <input type="submit"/>
      </form>
    </div>
  </div>
</div>
<%--<div><a href="students">View All</a></div>--%>
<%--<div><a href="logOut">Log Out</a></div>--%>


<div class="footer">
  <div class="copyright">Copyright</div>
  <div class="contact">Contact</div>
</div>

</body>
</html>
