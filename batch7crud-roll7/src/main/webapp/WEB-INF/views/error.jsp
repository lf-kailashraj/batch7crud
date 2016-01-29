<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com>
  Date: 1/21/16
  Time: 4:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Error</title>
</head>
<body>
<p>OoPs something went wrong !!!</p>
<p>${errorMessage}</p>
<a href="students">View All</a>
<div><a href="logOut">Log Out</a></div>

</body>
</html>
