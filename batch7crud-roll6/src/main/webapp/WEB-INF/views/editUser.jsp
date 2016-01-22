<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	input{
			display:block;
	}
</style>
</head>
<body>
	<form method="post" action="/batch7crud-roll6/users/${user.id}/edit">
		<span>First Name:</span> <input type = "text" name = "firstname" value ="${user.firstName}">
		<span>Surname :</span> <input type = "text" name = "surname" value = "${user.surName }">
		<span>Username:</span> <input type = "text" name="username" value = "${user.userName }">
		<span>Password:</span> <input type = "password" name = "password" value = "${user.password }">
		<input type="submit" value = "submit">
	
	</form>
</body>
</html>