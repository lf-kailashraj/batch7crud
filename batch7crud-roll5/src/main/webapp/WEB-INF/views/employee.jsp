<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath}/">
<title>Employees</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container clearfix">
		<jsp:include page="sideMenu.jsp"></jsp:include>
		<div class="content">
			<div class="table">
				<h1>List Of Employees</h1>
				<table>
					<thead>
						<tr>
							<th>Id</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Department</th>
							<th>Address</th>
							<th>Edit</th>
							<th>Delete</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach items="${employeeList}" var="employee">
							<tr>
								<td>${employee.getId()}</td>
								<td>${employee.getFirstName()}</td>
								<td>${employee.getLastName()}</td>
								<td>${employee.getDepartment()}</td>
								<td>${employee.getAddress()}</td>
								<td><a href="employees/${employee.getId()}/edit">Edit</a></td>
								<td><a href="employees/${employee.getId()}/deleteProcess"
									class="deleteBtn">Delete</a></td>
							</tr>

						</c:forEach>

					</tbody>

				</table>
			</div>
			
			<div class="page-no">
				<c:if test="${pageNo > 1}">
					<a href="employees?page=${pageNo-1}">Previous</a>
				</c:if>

				<c:set var="counter" value="0" />
				<c:forEach begin="0" end="${pageLink - 1}" step="1" varStatus="loop">
					<c:set var="counter" value="${counter + 1}" />
					<a href="employees?page=${counter}">${counter}</a>
				</c:forEach>

				<c:if test="${pageNo*noRecordsInPage < noOfEmployees}">
					<a href="employees?page=${pageNo+1}">Next</a>
				</c:if>
			</div>


		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

	<script>
		var delBtn = document.getElementsByClassName('deleteBtn');

		for (var i = 0; i < delBtn.length; i++) {
			delBtn[i].onclick = function(e) {
				e.preventDefault();

				var href = this.getAttribute('href');
				var doConfirm = confirm('Are you sure to delete??');

				if (doConfirm == true) {
					var form = document.createElement('form');

					form.action = href;
					form.method = 'post';

					document.body.appendChild(form);

					form.submit();

				}
			}

		}
	</script>


</body>
</html>