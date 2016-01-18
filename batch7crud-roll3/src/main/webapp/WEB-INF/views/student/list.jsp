<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/batch7crud-roll3/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>
</head>
<body>
${count}
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Roll</td>
			<td>Name</td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items = "${stdList}" var="student" >
			<tr>
				<td>${student.getId()}</td>
				<td>${student.getRoll()}</td>
				<td>${student.getName()}</td>
				<td><a href="student/${stdList[i].getId()}/edit">edit</a></td>
				<td><a href="student/${stdList[i].getId()}/delete" class="deleteItem">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="student/create">Create</a>

	<a href="student/${pageNum - 1}">Previous</a>${pageNum}
	<a href="student/${pageNum + 1}">Next</a>
</body>
<script>
	var deleteBtn = document.getElementsByClassName('deleteItem');
	for (var i = 0; i < deleteBtn.length; i++) {
		deleteBtn[i].onclick = function(e) {
			e.preventDefault();
			var href = this.getAttribute("href");
			var confirmation = confirm("do you want to delete?");

			if (confirmation == true) {
				var form = document.createElement("form");
				form.action = href;
				form.method = "post";
				console.log(form);
				document.body.appendChild(form);
				form.submit();
			}

		}

	}
</script>
</html>