<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/18/16
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Sign In</title>
  <jsp:include page="/WEB-INF/views/layout/cssAndJsIncludes.jsp" />
</head>
<body>
<div class="main-wrapper">
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />

  <div class="container-wrapper">
    <div class="container-container">
      <div class="container">
        <div class="signin-form">
          Sign In
          <form action="users/signin" method="POST">
            <div>
              <input name="username" type="text" placeholder="username"/>
            </div>
            <div>
              <input name="password" type="password" placeholder="password"/>
            </div>
            <div>
              <input type="submit"/>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</div>
</body>
</html>
