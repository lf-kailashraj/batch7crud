<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Hello</title>
</head>
<body>

    <h2>Welcome in Employee Management System</h2>

    <ul>
        <li><a href="employees/create">Add Employee</a></li>
        <li><a href="employees">List Employee</a></li>
        <li><a href="authentication/logout" id="logout">Logout</a></li>
    </ul>

    <script type="text/javascript">
        var logoutButton = document.getElementById("logout");
        logoutButton.onclick = function (e) {
            e.preventDefault();
            var href = this.getAttribute("href");
            var form = document.createElement("form");
            form.action = href;
            form.method = "post";
            form.submit();
        }
    </script>
</body>
</html>
