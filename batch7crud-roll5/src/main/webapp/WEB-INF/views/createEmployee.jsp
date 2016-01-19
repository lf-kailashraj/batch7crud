<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="/batch7crud-roll5/" target="_self">
<title>Create Employee</title>
</head>
<body>
	<h1>Sign Up Form</h1>
	<form action="createProcess" method="post">
		First Name:<input type="text" name="firstName" /><br> 
		Last Name:<input  type="text" name="lastName" /><br>
		Department:<input type="text" name="department" /><br> 
		Address:<input type="text" name="address" /><br> 
		<input type="submit" name="create" value="Create"/>
	</form>

</body>
</html>