<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com>
  Date: 1/18/16
  Time: 4:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
  <link rel="stylesheet" type="text/css" href="static/css/layout.css" />

  <title>New Entry</title>
</head>

<body>
  <%--<form action="students/newEntry" method="post">--%>
    <%--Name: <input type="text" name="name" placeholder="Name" value="${param.name}"/><span>${error.name}</span><br>--%>
    <%--Address: <input type="text" name="address" placeholder="Address" value="${param.address}"/><span>${error.address}</span><br>--%>
    <%--Roll: <input type="number" name="roll" placeholder="Roll" value = "${param.roll}"/><span>${error.roll}</span><br>--%>
    <%--<input type="submit"/>--%>
  <%--</form>--%>
  <%--&lt;%&ndash;<c:if test="${error != null}">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<p>Error in entered roll</p>&ndash;%&gt;--%>
  <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
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
        <li><a href="students">View All</a></li>
        <li><a href="logOut">Log Out</a></li>
      </ul>
    </div>
    <div class = "content">
      <div class="login-container">
        NEW ENTRY FORM
        <form action="students/newEntry" method="post">
          <input type="text" name="name" placeholder="Name" value="${param.name}"/><span class="error">${error.name}</span>
          <input type="text" name="address" placeholder="Address" value="${param.address}"/><span class="error">${error.address}</span>
          <input type="number" name="roll" placeholder="Roll" value = "${param.roll}"/><span class="error">${error.roll}</span>
          <input type="submit"/>
        </form>
      </div>
    </div>

  </div>

  <div class="footer">
    <div class="copyright">Copyright</div>
    <div class="contact">Contact</div>
  </div>


</body>
</html>
