<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="dataContainer" fragment="true" %>
<%@attribute name="username" fragment="true" %>
<%@attribute name="pageTitle" fragment="true" %>


<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome to IMS | Login</title>
 	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<script src="js/jsonMapper.js"></script>
	<script src="js/jquery-2.2.0.js"></script>
</head>
  <body>
	  <div class="header-container clrfix">
	    <div class="logo-container">
	      IMS
	    </div>
	    <div class="user-info-container">
	      <ul>
	        <li><img src="images/user-white.png"></li>
	        <li>
	          <a class="username-container" href=""><jsp:invoke fragment="username"/></a>
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
	          <a href="users">List User</a>
	        </li>
	        <li>
	          <a href="users/add">Add User</a>
	        </li>
	      </ul>
	    </div>
	    <div class="right-col">
	    	<jsp:invoke fragment="pageTitle"/>
      		<jsp:invoke fragment="dataContainer"/>
    	</div>
  </div>

	<script src="js/common.js"></script>

  </body>
</html>