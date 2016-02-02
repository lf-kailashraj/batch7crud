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
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<div class="form-wrp">
		<form action="students/${student.getId()}/edit" method="post">
			<h1>Edit Student</h1>

			<div class="form">
				<div class="roll">
					<label>Roll</label> <input type="number" name="roll"
						value="${student.getRoll()}"><span class="message">${message.roll}</span>
				</div>
				<div class="name">
					<label>Name</label> <input type="text" name="name"
						value="${student.getName()}"><span class="message">${message.name}</span>
				</div>
				<div class="create-btn">
					<input type="submit" value="Submit" />
				</div>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>