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
					class="show" title = "Show"></a> <%-- <td><a href="students/${student.getId()}/edit">edit</a></td> --%>
					<a href="students/${student.getId()}/delete" class="deleteItem" title = "delete"></a></td>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination-wrp"></div>

	<jsp:include page="/WEB-INF/views/footer.jsp" />
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
	var paginationWrp = document.getElementsByClassName('pagination-wrp')[0];

	var curPage = "${page}";
	console.log(curPage);
	window.onload = myFunc("${numberOfPages}", "${page}");

	function myFunc(totalPage, page) {
		if (page > 1) {
			createElement(page - 1, '<');
		}
		if (totalPage <= 6) {
			for (var i = 1; i <= 6; i++)
				createElement(i);
		} else if (page <= 3) {
			for (var i = 1; i <= 3; i++)
				createElement(i);
			createSpan();
			createElement(totalPage - 1);
			createElement(totalPage);
		} else if (page >= totalPage - 2) {
			createElement(1);
			createElement(2);
			createSpan();
			for (var i = totalPage - 2; i <= totalPage; i++)
				createElement(i);
		} else {
			createElement(1);
			createElement(2);
			createSpan();
			createElement(page);
			createElement(parseInt(page) + 1);
			if (page != totalPage - 3)
				createSpan();
			createElement(totalPage - 1);
			createElement(totalPage);
		}
		if (totalPage > page)
			createElement(parseInt(page) + 1, '>');

	}
	function createElement(page, character) {
		var newEl = document.createElement('a');
		var span = document.createElement('span');
		span.classList.add('pagination');
		newEl.classList.add('page');
		console.log(character);
		if (character)
			span.innerHTML = character;
		else
			span.innerHTML = page;
		newEl.setAttribute('href', 'students?page=' + page);
		if (curPage == page)
			span.classList.add('pagination-selected');
		newEl.appendChild(span);
		paginationWrp.appendChild(newEl);
	}
	function createSpan() {
		var newSpan = document.createElement('span');
		newSpan.classList.add('pagination-space');
		newSpan.innerHTML = "..";
		paginationWrp.appendChild(newSpan);

	}
</script>
</html>