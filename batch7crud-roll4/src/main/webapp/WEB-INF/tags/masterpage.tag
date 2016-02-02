<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="section" fragment="true" %>

<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>EMS</title>
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/layout.css">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>
<body>

<div class="wrapper">
    <!-- header starts here -->
    <div class="header clrfix">
        <div class="logo">
            <a href="#">EMS</a>
        </div>
        <div class="user-info clrfix">
            <a href="#" class="username">Pratish Shrestha</a>
            <a href="auth/logout" class="logout-btn">
                <img src="resources/images/logout.png">
            </a>
        </div>
    </div>
    <!-- main body starts here -->
    <div class="main">
        <div class="col2-left-layout clrfix">
            <div class="col-left">
                <div class="navigation">
                    <ul>
                        <li class="employees"><a href="employees">Employees</a></li>
                    </ul>
                </div>
            </div>
            <div class="main-container">
                <jsp:invoke fragment="section"/>
            </div>
        </div>
    </div>
    <!-- footer starts here -->
    <div class="footer">
        <span class="bold">Copyright</span> &copy <a href="http://lftechnology.com">Leapfrog Technology Inc.</a>
    </div>
</div>

<script src="resources/js/PostForm.js"></script>
</body>
</html>