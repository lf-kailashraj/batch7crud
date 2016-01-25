<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Insert title here</title>
<style>
	input{
		display:block;
	}
	.error{
	color:red;
	display:block;
	}
</style>
</head>
<body>
	<h1>Hello fill up this form</h1>
	<form action="add" method="post">
		
			<label>First Name:</label> <input name="firstname" value="${user.firstName}" type="text">
			<span class="error"><c:out value="${errors['firstname']}"></c:out></span>
			<label>Surname :</label> <input name="surname" value="${user.surName}" type="text">
			<span class="error"><c:out value="${errors['surname']}"></c:out></span>
			<label>Username:</label> <input name="username" value="${user.userName }" type="text">
			<span class="error"><c:out value="${errors['username']}"></c:out></span>
			<label>Password:</label> <input name="password" value="${user.password }" type="password">
			<span class="error"><c:out value="${errors['password']}"></c:out></span>
			<input type="submit" value="submit">
	
	</form>
</body>
</html>