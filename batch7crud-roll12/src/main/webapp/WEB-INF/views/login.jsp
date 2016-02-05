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
  <link href="static/css/reset.css" rel="stylesheet"/>
  <link href="static/css/adminCss.css" rel="stylesheet"/>
  <title>SMS</title>
</head>
<body>
<div class="login">
  <div class="login-field">
    <div class="error-field"
    <span class="error">${loginError.error}</span>

    <form action="/login" method="POST">
      <span class="label">Username: </span><br/>
      <input type="text" name="user"><br/>
      <span class="label">Password: </span><br/>
      <input type="password" name="pwd"><br/>

      <div class="button-field">
        <input type="submit" value="Login">
      </div>
    </form>
  </div>
</div>
</body>
</html>