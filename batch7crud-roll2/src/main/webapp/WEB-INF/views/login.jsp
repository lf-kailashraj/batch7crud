<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/26/16
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Login Page"/>
</jsp:include>
<div class="body-wrapper clearfix">
    <div class="col2-left clearfix">
        <div class="login-box">
            <div class="login-title">Login</div>
            <form action="authenticate/login" method="POST">
                <div class="form-label">Username</div>
                <div class="input-text"><input type="text" name="username"></div>
                <div class="form-label">Password</div>
                <div class="input-text"><input type="password" name="password"></div>
                <span class="errorMessage">${loginError}</span>

                <input type="submit" value="Login">
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>