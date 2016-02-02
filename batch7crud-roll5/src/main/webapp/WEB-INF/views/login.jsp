<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath}/">
<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/layout.css" rel="stylesheet" type="text/css">
<title>Login</title>
</head>
<body>
	<div class="container clearfix">
		<div class="login-form">
			<div class="title">Login</div>
			<form class="header" action="employeeAuthentication/login"
				method="post">
				<ul>
					<li class="input"><label>User Name:</label>
						<input type="text" name="name" />
					</li>

					<li class="input"><label>Password:</label>
						<input type="password" name="password" />
					</li>

					<li class="input login-submit">
						<input type="submit" name="login" value="login" />
					</li>
				</ul>
			</form>
		</div>
	</div>
</body>
</html>