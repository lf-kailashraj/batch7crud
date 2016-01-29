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
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>error</title>
</head>
<body>
    <h2>Error!</h2>
    <h4><c:out value="${errorMessage}"/></h4>

</body>
</html>
