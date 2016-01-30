<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login to IMS| Inventory Managemant System</title>
<style type="text/css">
input{
		display:block;
	}
</style>
<base href="${pageContext.request.contextPath}/" />

</head>
<body>
Inventory Managemant System Login
<form action="login" method="post">
	<label>Username</label><input type = "text" name = "username">
	<label>Password</label><input type = "password" name = "password">
	<input type = "submit" name="submit" value = "Login">

</form>
</body>
</html>