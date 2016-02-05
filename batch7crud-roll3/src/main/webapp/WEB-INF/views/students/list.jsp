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
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Roll</td>
			<td>Name</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="${studentList}" var="book">
			<tr>
				<td>${book.getId()}</td>
				<td>${book.getRoll()}</td>
				<td>${book.getName()}</td>
				<td><a href="students/${book.getId()}">show</a></td>
				<td><a href="students/${book.getId()}/edit">edit</a></td>
				<td><a href="students/${book.getId()}/delete"
					class="deleteItem">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="students/create">Create</a>

	<c:if test="${page > 1}">
		<a href="students?page=${page - 1}">Previous</a>
	</c:if>

	<c:if test="${numberOfPages <= 6}">
		<c:forEach var="i" begin="1" end="${numberOfPages}">
			<c:if test="${page != i}">
				<a href="students?page=${i}">${i}</a>
			</c:if>
			<c:if test="${page == i}">
				${i}
			</c:if>
		</c:forEach>
	</c:if>
	<c:if
		test="${numberOfPages > 6 && (page == 1 || page == 2 || page == 3)}">
		<c:forEach var="i" begin="1" end="3">
			<c:if test="${page != i}">
				<a href="students?page=${i}">${i}</a>
			</c:if>
			<c:if test="${page == i}">
				${i}
			</c:if>
		</c:forEach>
		..
		<a href="students?page=${numberOfPages-1}">${numberOfPages-1}</a>
		<a href="students?page=${numberOfPages}">${numberOfPages}</a>
	</c:if>
	<c:if
		test="${numberOfPages > 6 && (page == numberOfPages-2 || page == numberOfPages-1 || page == numberOfPages)}">
		<a href="students?page=1">1</a>
		<a href="students?page=2">2</a>
		..
		<c:forEach var="i" begin="${numberOfPages-2}" end="${numberOfPages}">
			<c:if test="${page != i}">
				<a href="students?page=${i}">${i}</a>
			</c:if>
			<c:if test="${page == i}">
				${i}
			</c:if>
		</c:forEach>

	</c:if>
	<c:if
		test="${numberOfPages > 6 && page != numberOfPages-2 && page != numberOfPages-1 && page != numberOfPages && page != 3 && page != 2 && page != 1}">
		<a href="students?page=1">1</a>
		<a href="students?page=2">2</a>
		..
		${page}
		<a href="students?page=${page + 1}">${page + 1}</a>
		<c:if test="${page != (numberOfPages - 3)}">
		..
		</c:if>
		<a href="students?page=${numberOfPages-1}">${numberOfPages-1}</a>
		<a href="students?page=${numberOfPages}">${numberOfPages}</a>
	</c:if>

	<c:if test="${numberOfPages > page}">
		<a href="students?page=${page + 1}">Next</a>
	</c:if>
</body>
<script>
	var deleteBtn = document.getElementsByClassName('deleteItem');
	for (var i = 0; i < deleteBtn.length; i++) {
		deleteBtn[i].onclick = function(e) {
			e.preventDefault();
			var href = this.getAttribute("href");
			var confirmation = confirm("Do you want to delete?");

			if (confirmation == true) {
				var form = document.createElement("form");
				form.action = href;
				form.method = "post";
				document.body.appendChild(form);
				form.submit();
			}

		}

	}
</script>
</html>