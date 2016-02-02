<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit</title>
</head>
<body>
	<form action="students/${student.getId()}/edit" method="post">
		Roll:<input type="number" name="roll" value="${student.getRoll()}">${message.roll}<br />
		Name:<input type="text" name="name" value="${student.getName()}">${message.name}<br />
		<input type="submit" value="submit" />
	</form>
</body>
</html>