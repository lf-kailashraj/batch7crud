<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee Management System</title>
<base href="${pageContext.request.contextPath}/">
</head>
<body>
	<div>
		<h1>Employee Management System</h1>

		<a href="employee">View All Employees</a> <br/>
		<a href="employee/add">Add New Employee</a>
	</div>
</body>
</html>