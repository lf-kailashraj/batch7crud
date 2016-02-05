<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericPage>
	<jsp:attribute name="username"><c:out value="${username}"></c:out></jsp:attribute>
	<jsp:attribute name="pageTitle"> Hello <c:out value="${username}"></c:out> </jsp:attribute>
    <jsp:attribute name="dataContainer">
    <a href="users">List User</a><br>
	<a href="users/add">Add User</a>
	<a href="logout">Logout</a>
    </jsp:attribute>
</t:genericPage>
