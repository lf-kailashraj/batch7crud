<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>login page</title>
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>
    <div class="wrapper">
        <div class="login-content">
            <div class="login-title">
                <h2>Login To Leapfrog EMS</h2>
            </div>

            <div class="login-form">
                <form action="authentication/login" method="post">

                    <span>${errors}</span>

                    <div class="form-group">
                        <label>User Name</label>
                        <input class="form-control" name="userName" type="text">
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input class="form-control" name="password" type="password">
                    </div>

                    <input type="submit" class="button button-primary" value="login">
                </form>
            </div>
        </div>
    </div>

</body>
</html>
