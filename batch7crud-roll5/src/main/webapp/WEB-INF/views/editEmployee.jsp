<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="/batch7crud-roll5/" target="_self">
<title>Edit Employee</title>
</head>
<body>
	<h1>Edit Employee Form</h1>
	<form action="employees/${employee.getId()}/editProcess" method="post">
		First Name:<input type="text" name="firstName" value = ${employee.getFirstName()} /><br>
		Last Name:<input type="text" name="lastName" value = ${employee.getLastName()} /><br> 
		Department:<input type="text" name="department" value = ${employee.getDepartment()} /><br> 
		Address:<input type="text" name="address" value = ${employee.getAddress()} /><br> 
			    <input type="submit" name="create" value="Submit">
	</form>



</body>
</html>