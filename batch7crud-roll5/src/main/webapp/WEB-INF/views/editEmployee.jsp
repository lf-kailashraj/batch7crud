<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath}/">
<title>Edit Employee</title>
</head>
<body>

	<h1>Edit Employee Form</h1>	
	<form action="employees/${employee.getId()}/edit" method="post">
		First Name:<input type="text" name="firstName"
			value="${employee.getFirstName()}"/> ${message.firstName}<br>
		Last Name:<input type="text" name="lastName"
			value="${employee.getLastName()}"/> ${message.lastName}<br>
		Password:<input type="password" name="password"
			value="${employee.getPassword()}"/> ${message.pass}<br>
		Department:<input type="text" name="department"
			value="${employee.getDepartment()}"/> ${message.department}<br>
		Address:<input type="text" name="address"
			value="${employee.getAddress()}"/> ${message.address}<br> 
			<input type="submit" name="create" value="Submit">
	</form>
</body>
</html>