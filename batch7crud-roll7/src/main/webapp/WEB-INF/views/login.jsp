<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel
  Date: 1/27/16
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
  <link rel="stylesheet" type="text/css" href="static/css/layout.css" />
  <title>Log In</title>
</head>
<body>

<div class="header">
  <div class="header-text">
    SHREE KAMANA BOARDING HIGH SCHOOL
  </div>
</div>

<div class="mainbody">
  <div class="login-container">
    <h2>SIGN IN</h2>
    <form action="authenticate" method="post">
      <div>
        <input type="text" name="name" placeholder="Username">
      </div>

      <div>
        <input type="password" name="password" placeholder="password">
      </div>
      <span class="error">${error}</span>
      <div>
        <input type="submit" value = "Log In">
      </div>

    </form>
  </div>

</div>

<div class="footer">
  <div class="copyright">Copyright</div>
  <div class="contact">Contact</div>
</div>

</body>
</html>
