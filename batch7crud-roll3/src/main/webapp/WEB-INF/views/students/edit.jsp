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
	${message}
	<form action="students/${student.getId()}/edit" method="post">
		<c:if test="${student.getRoll() != 0}">
			<c:set var="param.roll" value="${student.getRoll()}" />
		</c:if>
		<c:if test="${student.getName() != null}">
			<c:set var="param.name" value="${student.getName()}" />
		</c:if>
		Roll:<input type="number" name="roll" value="${param.roll}"><br />
		Name:<input type="text" name="name" value="${param.name}"><br /> <input
			type="submit" value="submit" />
	</form>
</body>
</html>