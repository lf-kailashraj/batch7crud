<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath}/">
<title>Creating Using Ajax</title>
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
	
	<div class="container clearfix">
		<jsp:include page="sideMenu.jsp"></jsp:include>
		<div class="content">
			<div class="form">
				<div class="title">Sign Up Form</div>
				<form method="post">
					<ul>
						<li class="input">
							<label>First Name:</label><input type="text" name="firstName" value=${param.firstName }>
							<span class="error">${message.firstName}</span>
						</li>
						
						<li class="input">
							<label>Last Name:</label><input type="text" name="lastName" value=${param.lastName } >
							<span class="error">${message.lastName}</span>
						</li>
						
						<li class="input">
							<label>Password:</label><input type="password" name="password" value=${param.password } >
							<span class="error">${message.pass}</span>
						</li>
						
						<li class="input">
							<label>Department:</label><input type="text" name="department" value=${param.department } >
							<span class="error">${message.department}</span>
						</li>
						
						<li class="input">
							<label>Address:</label><input type="text" name="address" value=${param.address } >
							<span class="error">${message.address}</span>
						</li>
						
						<li class="input">
							<label>check</label><input type="submit" name="create" id="create" value="Create" >
						</li>
					</ul>					
				</form>
			</div>
		</div>
	</div>
	
<jsp:include page="footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script>
	$('#create').click(function(e) {
		e.preventDefault();
		var firstName = $('#firstName').val();
		var lastName = $('#lastName').val();
		var password = $('#password').val();
		var department = $('#department').val();
		var address = $('#address');
		
		var jsonDataObject = new Object();
		jsonDataObject.firstName = firstName;
		jsonDataObject.lastName  = lastName;
		jsonDataObject.password = password;
		jsonDataObject.department = department;
		jsonDataObject.address = address;
		var jsonData = JSON.stringify(jsonDataObject);
		
		 $.ajax({
			url : 'employees/createProcessAjax',
			type : 'POST',
			dataType: 'json',
            data: jsonData,
			success: function () {
   				 alert("success");
			},
			error: function () {
			    alert("error");
			}
		});
	});
	</script>

</body>
</html>
