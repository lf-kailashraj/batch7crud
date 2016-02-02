<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/18/16
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Users</title>
  <jsp:include page="/WEB-INF/views/layout/cssAndJsIncludes.jsp" />
</head>
<body>
<div class="main-wrapper">
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />

  <div class="content-wrapper">
    <div class="content-container">
      <div class="content">
        <div class="user-boxes clearfix">
          <div class="user-box-left clearfix">
            <div class="user-box-l">
              <a href="/employees">Manage Employees</a>
            </div>
            <div class="user-box-r">
              <a href="#">Manage your details</a>
            </div>
          </div>
          <div class="user-box-right">
            <a href="#">Add New Admin</a>
          </div>
        </div>
      </div>
    </div>
  </div>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</div>
</body>
</html>
