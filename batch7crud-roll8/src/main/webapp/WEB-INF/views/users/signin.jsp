<%--
  Created by IntelliJ IDEA.
  User: grishma
  Date: 1/18/16
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
    Hello From Signin
    <form action="create" method="POST">
        <div>
            <label>Username:</label>
            <input name="username" type="text"/>
        </div>
        <div>
            <label>Password:</label>
            <input name="password" type="password"/>
        </div>
        <div>
            <input type="submit"/>
        </div>

    </form>
</body>
</html>
