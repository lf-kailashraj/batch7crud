<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/students/" />
<title>Hello</title>
</head>
<body>

<div>Welcome.</div>
<div><a href="${pageContext.request.contextPath}/students/create">New Student</a></div>

<a href="${pageContext.request.contextPath}/students">See all Students</a>
</body>
</html>
