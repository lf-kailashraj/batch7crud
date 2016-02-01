<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <title>Dashboard</title>
    <link href="static/css/reset.css" rel="stylesheet" type="text/css" />
    <link href="static/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="header-wrapper"><div class="logo">Dashboard</div></div>
<div class="left-container">
    <ul>
        <li><a href="students">All Students</a></li>
        <li><a href="students/create">New Student</a></li>
        <li><a href="logout" class="logout">Logout</a></li>
    </ul>
</div>
<div class="main-container">
<div>Welcome! ${username}</div>
</div>


<div class="footer">footer</div>
<script>
    var logoutElement = document.getElementsByClassName("logout")[0];
    logoutElement.onclick = function (e) {
        e.preventDefault();
        var form = document.createElement('form');
        var destinationLink = e.target.getAttribute("href");
        form.setAttribute("method", "POST");
        form.setAttribute("action", destinationLink);
        if (confirm("Are you sure you want to Logout???") == true) {
            form.submit();
        }
    };
</script>
</body>
</html>
