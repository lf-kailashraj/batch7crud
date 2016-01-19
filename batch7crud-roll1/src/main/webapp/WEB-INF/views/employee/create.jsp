<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add</title>
</head>
<body>

	<form action="create" method="post">
		<label>Employee Id :</label>
		<input type="text" name="id">
		<label>Name :</label>
		<input type="text" name="userName">
		<label>Password :</label>
		<input type="password" name="password">
		<label>Full Name :</label>
		<input type="text" name="fullName">
		<label>Department :</label>
		<input type="text" name="department">
		<label>Address</label>
		<input type="text" name="address">
		<input type="submit" value="submit">  
	</form>

</body>
</html>