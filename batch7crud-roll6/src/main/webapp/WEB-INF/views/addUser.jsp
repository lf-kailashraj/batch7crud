<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericPage>
	<jsp:attribute name="username"><c:out value="${username}"></c:out></jsp:attribute>
	<jsp:attribute name="pageTitle"></jsp:attribute>
    <jsp:attribute name="dataContainer">
  <div class="panel-container">
  <div class="panel-header">Add User Information</div>
    <div class="panel-body">
      	<form method="post" id= "addUser">
		
			<label>First Name:</label> <input name="firstname" value="${param.firstname}" type="text" id= "firstname">
			<span class="error"></span>
			<label>Surname :</label> <input name="surname" value="${param.surname}" type="text" id= "surname">
			<span class="error"></span>
			<label>Username:</label> <input name="username" value="${param.username }" type="text" id="username">
			<span class="error"></span>
			<label>Password(minimum 6 character):</label> <input name="password" value="${param.password}" type="password" id="password">
			<span class="error"></span>
			<label>Age:</label> <input name="age"  type="text" value="${param.age }" id= "age">
			<span class="error"></span>
			<input type="submit" value="submit">
	
	</form>
    </div>
  	</div>

	<script>
		$("#addUser").submit(
			function(e){
				e.preventDefault();
				mapToJson();
			}
		)
	</script>
    </jsp:attribute>
</t:genericPage>
