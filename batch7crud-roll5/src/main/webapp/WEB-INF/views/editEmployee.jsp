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
	<form action="employees/${employeeId}/editProcess" method="post">
		First Name:<input type="text" name="firstName"
			value="${employee.getFirstName()}" id="fistName"/> ${message.firstName}<br>
		Last Name:<input type="text" name="lastName"
			value="${employee.getLastName()}" id="lastName"/> ${message.lastName}<br>
		Password:<input type="password" name="password"
			value="${employee.getPassword()}" id="password"/> ${message.pass}<br>
		Department:<input type="text" name="department"
			value="${employee.getDepartment()}" id="department"/> ${message.department}<br>
		Address:<input type="text" name="address"
			value="${employee.getAddress()}" id="address"/> ${message.address}<br> 
			
			<input type="submit" name="create" value="Submit">
	</form>

	<script>
		if("${message}"){
			document.getElementById("fistName").value = "${param.firstName}";
			document.getElementById("lastName").value = "${param.lastName}";
			document.getElementById("password").value = "${param.password}";
			document.getElementById("department").value = "${param.department}";
			document.getElementById("address").value = "${param.address}";
		}
			
	</script>

</body>
</html>