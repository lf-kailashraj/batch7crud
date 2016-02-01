<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="${pageContext.request.contextPath}/" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome to IMS | Login</title>
 	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">

</head>
	<body>
		<div class = "main-wrp">
			<div class = "inner-wrp">
				<div class = "title-container">
					IMS Login
				</div>
				<div class = "login-form-container">
					<form action="login" method="post">
					<div class = "username-container">
						<input type="text" name ="username" placeholder = "Username" >
					</div>
					<div class = "password-container">
						<input type = "password" name ="password" placeholder = "Password">
					</div>
					<div class = "submit-container">
						<input class= "submit" type= "submit" value="Login">
					</div>
					</form>						
					
				</div>

			</div>
		</div>
	</body>
</html>