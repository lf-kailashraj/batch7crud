<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Hello</title>
</head>
<base href="${pageContext.request.contextPath}/" />
<body>

	<h1>Welcome!!!!!</h1>
	<a href="users">List User</a><br>
	<a href="users/add">Add User</a>
	<a href="login">Login</a>
</body>
</html>
