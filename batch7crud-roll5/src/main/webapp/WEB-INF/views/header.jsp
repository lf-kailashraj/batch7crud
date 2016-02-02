<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/layout.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="header-wrapper">
		<div class="logo">
			<img alt="" src="images/">
			<h1>Employee Management System</h1>
		</div>
		<div class="user">
			<img alt="" src="images/">
			${name}
			<a href="employeeAuthentication/logout">Log Out</a>
		</div>
	</div>
</body>
</html>