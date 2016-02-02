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
	.error{
		color:red;
		display:block;
	}
</style>
<base href="${pageContext.request.contextPath}/" />
</head>
<body>
	<form method="post" action="users/${userAttributes['id']}/edit">
		<span>First Name:</span> <input type = "text" name = "firstname" value ="${userAttributes['firstname']}">
		<span class="error"><c:out value="${errors['firstname']}"></c:out></span>
		<span>Surname :</span> <input type = "text" name = "surname" value = "${userAttributes['surname']}">
		<span class="error"><c:out value="${errors['surname']}"></c:out></span>
		<span>Username:</span> <input type = "text" name="username" value = "${userAttributes.username}">
		<span class="error"><c:out value="${errors['username']}"></c:out></span>
		<label>Age:</label> <input name="age"  type="text" value="${userAttributes.age}">
		<span class="error"><c:out value="${errors['age']}"></c:out></span>
		<input type="submit" value = "submit">
	
	</form>
</body>
</html>