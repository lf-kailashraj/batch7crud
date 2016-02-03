<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<div class="record-wrp">
		<div class="show-record">
			<h1>Record ${student.getId()}</h1>
			<div class="record-info">
				<table class="show-table">
					<tr>
						<td class="field-name">Name</td>
						<td class="record-field">${student.getName()}</td>
					</tr>
					<tr>
						<td class="field-name">Roll</td>
						<td class="record-field">${student.getRoll()}</td>
						</td>
				</table>
				<div class="show-action clear-fix">
					<div class="action-edit"><a href="students/${student.getId()}/edit">edit</a></div>
					<div class="action-list"><a href="students">list</a></div>
				</div>
				
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>