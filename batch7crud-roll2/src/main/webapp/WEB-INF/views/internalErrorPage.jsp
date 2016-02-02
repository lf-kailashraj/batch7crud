<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/18/16
  Time: 12:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <base href="${pageContext.request.contextPath}/">
</head>
<body>
<h1>Internal Server Error!!</h1>
${message}
<a href="employees">Go Back</a>
</body>
</html>
