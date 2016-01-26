<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Insert title here</title>
<style>
	input{
		display:block;
	}
</style>
</head>
<body>
	<h1>Hello fill up this form</h1>
	<form action="add" method="post">
		
			<span>First Name:</span> <input name="firstname" type="text">
			<span>Surname :</span> <input name="surname" type="text">
			<span>Username:</span> <input name="username" type="text">
			<span>Password:</span> <input name="password" type="password">
			<input type="submit" value="submit">
	
	</form>
</body>
</html>