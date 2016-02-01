<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 2/1/16
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="${pageContext.request.contextPath}/">
<div class="header-wrapper">
  <div class="header-container">
    <div class="header clearfix">
      <div class="header-title">
        <h1>Employee Management System</h1>
      </div>
      <c:if test = "${sessionScope.user != null}">
        <div class="user-info">
          <div class="user-name">
            <h2>Grishma Shrestha</h2>
          </div>
          <div class="user-menu">
            <ul>
              <li><a href="#">Settings</a></li>
              <li><a class="logout" href="/users/logout">Log Out</a></li>
            </ul>
          </div>
        </div>
      </c:if>
    </div>
  </div>
</div>
<script>
  $("a.logout").click(function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    var $form = $("<form method='POST'></form>");
    $form.attr("action", href);
    $("body").append($form);
    $form.submit();
  });
</script>