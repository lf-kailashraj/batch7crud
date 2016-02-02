<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base href="${pageContext.request.contextPath}/">
<link href='https://fonts.googleapis.com/css?family=Montserrat'
	rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/reset.css" />
<link type="text/css" rel="stylesheet" href="css/layout.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="login-wrp">
		<form action="authentication/login" method="post">
			<h1>SMS</h1>
			<div class="login-form">
				<div class="login-field">
					<input type="text" name="name">
				</div>
				<div class="login-field">
					<input type="password" name="password">
				</div>
				<div class="submit-btn">
					<input type="submit" value="Login" />
				</div>
		</form>
	</div>
</body>
</html>