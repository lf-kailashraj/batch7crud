<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee Management</title>
<base href="${pageContext.request.contextPath}/">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container clearfix">
		<jsp:include page="sideMenu.jsp"></jsp:include>
		<div class="content">this is a dashboard for Admin</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
