<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/21/16
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Employee Details</title>
  <jsp:include page="/WEB-INF/views/layout/cssAndJsIncludes.jsp" />
</head>
<body>
<div class="main-wrapper">
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />

  <div class="content-wrapper">
    <div class="content-container">
      <div class="content">
        <div class="employee-index-menu clearfix">
          <a class="clearfix blue" href="employees">
            <h2 class="emp-home-link">Employees Home</h2>
          </a>
        </div>
        <div class="employee-view employee-create">
          <h2>Employee Details</h2>
          ${message}
          <div class="employee-create-form">
            <div class="employee-view-deets">
              <p><label>Name</label></p>
              <p>${employee.getName()}</p>
            </div>
            <div class="employee-view-deets">
              <p><label>Address</label></p>
              <p>${employee.getAddress()}</p>
            </div>
            <div class="employee-view-deets">
              <p><label>Designation</label></p>
              <p>${employee.getDesignation()}</p>
            </div>
            <div class="employee-view-deets">
              <p><label>Phone</label></p>
              <p>${employee.getPhone()}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</div>
</body>
</html>
