<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="pageTitle" fragment="true" %>
<%@attribute name="userName" fragment="true" %>
<%@attribute name="dataContainer" fragment="true" %>
<%@attribute name="footer" fragment="true" %>


<html>
<head>
  <base href="/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>
    <jsp:invoke fragment="pageTitle"/>
  </title>
  <link rel="stylesheet" type="text/css" href="css/reset.css">
  <link rel="stylesheet" type="text/css" href="css/layout.css">
  <link rel="stylesheet" type="text/css" href="css/style.css">
  <script src="js/jquery-1.12.0.min.js"></script>
</head>
<body>
<div class="header-container clrfix">
  <div class="logo-container">
    eMS
  </div>
  <div class="user-info-container">
    <ul>
      <li><img src="images/user-white.png"></li>
      <li>
        <div class="username-container" >
          <jsp:invoke fragment="userName"/>
        </div>
      </li>
      <li>
        <a class="logout" href="logout"><img src="images/logout.png"></a>
      </li>
    </ul>
  </div>
</div>
<div class="main-container">
  <div class="left-col">
    <ul>
      <li>
        <a href="index">Dashboard</a>
      </li>
      <li>
        <a href="employee">Employee List</a>
      </li>
      <li>
        <a href="employee/add">Add Employee</a>
      </li>
      <li>
        <a href="user/add">Add User</a>
      </li>

    </ul>
  </div>
  <div class="right-col">
    <jsp:invoke fragment="dataContainer"/>
  </div>
</div>
</body>
</html>