<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericPage>

    <jsp:attribute name="dataContainer">
    <a href="users">List User</a><br>
	<a href="users/add">Add User</a>
	<a href="logout">Logout</a>
    </jsp:attribute>
</t:genericPage>
