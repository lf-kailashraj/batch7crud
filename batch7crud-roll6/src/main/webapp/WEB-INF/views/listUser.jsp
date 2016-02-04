<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericPage>

    <jsp:attribute name="dataContainer">
	  <div class="table-wrp"> 
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
			<c:if test="${currentPage == 1}">
				<c:set var="count" value="1"></c:set>
			</c:if>
			<c:if test="${currentPage != 1}" >
				<c:set var="count" value="${(currentPage -1)*limit +1}"></c:set>
			</c:if>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${count}"></c:out></td>
						<td><c:out value="${user.firstName}"></c:out></td>
						<td><c:out value="${user.surName}"></c:out></td>
						<td><c:out value="${user.userName}"></c:out></td>
						<td><a href="users/${user.id}/edit">edit</a> <a
							href="users/${user.id}/delete" class="deleteUser">delete</a></td>
					</tr>
				<c:set var="count" value="${count+1}"></c:set>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div class="pagination">
		<c:if test="${currentPage >1}">
			<a href="users?page=${currentPage-1}">Previous</a>
		</c:if>
		<c:if test="${noOfPages >1}">
		<c:forEach begin="1" end="${noOfPages}" var="i">
			<c:if test="${currentPage == i }">
				<span class="current-page" ><c:out value="${i}">${i}</c:out> </span>
			</c:if>
			<c:if test="${currentPage != i }">
				<a href="users?page=${i}">${i}</a>
			</c:if>
			
		</c:forEach>
		</c:if>
		<c:if test="${currentPage < noOfPages }">
			<a href="users?page=${currentPage+1}">Next</a>
		</c:if>
		</div>
    </jsp:attribute>
</t:genericPage>
