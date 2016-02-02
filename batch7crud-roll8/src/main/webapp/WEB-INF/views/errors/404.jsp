<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/21/16
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% response.setStatus(404); %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>404</title>
  <jsp:include page="/WEB-INF/views/layout/cssAndJsIncludes.jsp" />
</head>
<body>
<div class="main-wrapper">
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />

  <div class="content-wrapper">
    <div class="content-container">
      <div class="content">
        404 OOPS! The page was not found!
      </div>
    </div>
  </div>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</div>
</body>
</html>
