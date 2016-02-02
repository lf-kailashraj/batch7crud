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

  <div class="content-wrapper">
    <div class="content-container">
      <div class="content">
        <div class="signin-form">
          <div class="signin-form-content">
            <form action="users/signin" method="POST">
              <p>
                <input name="username" type="text" placeholder="Username"/>
              </p>
              <p>
                <input name="password" type="password" placeholder="Password"/>
              </p>
              <p>
                <input class="signin-submit" type="submit" value="Log In"/>
              </p>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</div>
</body>
</html>
