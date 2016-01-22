<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table, th, td {
	border: 1px solid black;
}
</style>
</head>
<body>
	<a href="/batch7crud-roll6/users/add"> Add User</a>
	<h1>User List</h1>
	<table>
		<thead>
			<tr>
				<th>S.No</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>User Name</th>
				<th>Operations</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>
					<td><c:out value="${user.firstName}"></c:out></td>
					<td><c:out value="${user.surName}"></c:out></td>
					<td><c:out value="${user.userName}"></c:out></td>
					<td><a href="users/${user.id}/edit">edit</a> <a
						href="users/${user.id}/delete" class="deleteUser">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:if test="${currentPage >1}">
		<a href="?page=${currentPage-1}">Previous</a>
	</c:if>
	<c:forEach begin="1" end="${noOfPages}" var="i">
		<a href="?page=${i}">${i}</a>
	</c:forEach>
	<c:if test="${currentPage < noOfPages }">
		<a href="?page=${currentPage+1}">Next</a>
	</c:if>
	<script type="text/javascript">
		var deleteUser = document.getElementsByClassName("deleteUser");

		for (var i = 0; i < deleteUser.length; i++) {

			deleteUser[i].onclick = function(event) {
				event.preventDefault();
				var href = this.getAttribute("href");
				var confirmation = confirm("Do you want to delete?");

				if (confirmation == true) {
					var form = document.createElement("form");
					form.action = href;
					form.method = "post";
					form.submit();
				}
			}
		}
	</script>
</body>
</html>