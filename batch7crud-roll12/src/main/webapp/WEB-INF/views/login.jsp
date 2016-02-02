<%--
		Created by IntelliJ IDEA.
		User: sagarmatha
		Date: 2/1/16
		Time: 5:52 PM
		To change this template use File | Settings | File Templates.
		--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <base href="${pageContext.request.contextPath}/"/>
  <link href="static/css/style.css" rel="stylesheet">
  <title>SMS</title>
</head>
<body>
<div class="login">
<div class="loginField">
<span class="error">${loginError.error}</span>
<form action="${pageContext.request.contextPath}/LoginController" method="POST">
  Username: <br/>
  <input type="text" name="user">
  <br/>
  Password: <br/>
  <input type="password" name="pwd">
  <br/>
  <input type="submit" value="Login">
</form>
</div>
</div>
</body>
</html>