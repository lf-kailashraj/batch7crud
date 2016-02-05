<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
  <jsp:attribute name="pageTitle">Add New User</jsp:attribute>
  <jsp:attribute name="userName"><c:out value="${userName}"></c:out> </jsp:attribute>
  <jsp:attribute name="dataContainer">
    <div class="panel-container">
      <div class="panel-header">Add New User</div>
        <div class="panel-body">
          <form action="/user/add" method="POST">
            <label>Username</label>
            <input type="text" name="userName" value="${param.userName}"> <c:out value="${errors.userName}"></c:out>
            <label>Password</label>
            <input type="password" name="password"> <c:out value="${errors.password}"></c:out>
            <button type="submit">Register</button>
          </form>
        </div>
    </div>
  </jsp:attribute>
</t:genericpage>
