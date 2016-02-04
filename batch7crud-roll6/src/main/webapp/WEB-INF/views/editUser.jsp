<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericPage>

    <jsp:attribute name="dataContainer">
    <div class="panel-container">
  	<div class="panel-header">Edit User </div>
    <div class="panel-body">
  	<form method="post" action="users/${userAttributes['id']}/edit">
		<label>First Name:</label> <input type = "text" name = "firstname" value ="${userAttributes['firstname']}">
		<span class="error"><c:out value="${errors['firstname']}"></c:out></span>
		<label>Surname :</label> <input type = "text" name = "surname" value = "${userAttributes['surname']}">
		<span class="error"><c:out value="${errors['surname']}"></c:out></span>
		<label>Username:</label> <input type = "text" name="username" value = "${userAttributes.username}">
		<span class="error"><c:out value="${errors['username']}"></c:out></span>
		<label>Age:</label> <input name="age"  type="text" value="${userAttributes.age}">
		<span class="error"><c:out value="${errors['age']}"></c:out></span>
		<input type="submit" value = "submit">
	
	</form>
	  </div>
  	</div>
    </jsp:attribute>
</t:genericPage>
