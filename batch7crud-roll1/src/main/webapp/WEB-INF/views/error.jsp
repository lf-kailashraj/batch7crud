<%--
  Created by IntelliJ IDEA.
  User: kiran
  Date: 1/18/16
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>error</title>
</head>
<body>

    <p><c:out value="${errorMessage}"/></p>

</body>
</html>
