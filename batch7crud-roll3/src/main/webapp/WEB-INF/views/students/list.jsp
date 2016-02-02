<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<div class="list-title">
		<h1>Students</h1>
		<a href="students/create">Create</a>
	</div>
	<table border="1">
		<tr>
			<td class="table-header">Id</td>
			<td class="table-header">Name</td>
			<td class="table-header">Roll</td>
			<td class="table-header">Actions</td>
		</tr>
		<c:forEach items="${studentList}" var="student">
			<tr>
				<td>${student.getId()}</td>
				<td>${student.getName()}</td>
				<td>${student.getRoll()}</td>
				<td class="link"><a href="students/${student.getId()}"
					class="show" title="Show"></a> <%-- <td><a href="students/${student.getId()}/edit">edit</a></td> --%>
					<a href="students/${student.getId()}/delete" class="deleteItem"
					title="delete"></a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination-wrp"></div>

	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
<script src="js/delete.js"></script>
<script src="js/pagination.js"></script>
<script>
	window.onload = paginate("${numberOfPages}", "${page}");
</script>
</html>