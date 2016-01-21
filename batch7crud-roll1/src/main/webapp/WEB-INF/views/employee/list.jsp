<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>list</title>
</head>
<body>

    <a href="employees/create">Add Employee</a>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>user name</th>
				<th>full name</th>
				<th>department</th>
				<th>address</th>
			</tr>
		</thead>
		<tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td><c:out value="${employee.id}" /></td>
                    <td><c:out value="${employee.userName}" /></td>
                    <td><c:out value="${employee.fullName}" /></td>
                    <td><c:out value="${employee.department}" /></td>
                    <td><c:out value="${employee.address}" /></td>
                    <td><a href="employees/${employee.id}/delete" class="delete"> Delete</a></td>
                    <td><a href="employees/${employee.id}/edit" class="edit"> Edit</a></td>
                </tr>
            </c:forEach>
		</tbody>
	</table>

    <div>
        <c:if test="${currentPage > 1}">
            <a href="employees?page=${currentPage-1}">prev</a>
        </c:if>
        <c:forEach begin="1" end="${totalPage}" var="page">
            <a href="employees?page=${page}">${page}</a>
        </c:forEach>
        <c:if test="${currentPage < totalPage}">
            <a href="employees?page=${currentPage + 1}">next</a>
        </c:if>
    </div>


    <script>

        var deleteButton = document.getElementsByClassName("delete");
        for (var i = 0; i < deleteButton.length; i++) {
            deleteButton[i].onclick = function (e) {
                e.preventDefault();
                var href = this.getAttribute("href");
                var confirmation = confirm("do you want to delete?");

                if(confirmation == true){
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
