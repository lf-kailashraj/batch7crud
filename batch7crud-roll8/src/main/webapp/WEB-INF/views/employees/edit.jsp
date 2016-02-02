<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/20/16
  Time: 7:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Edit Details of Employee</title>
  <jsp:include page="/WEB-INF/views/layout/cssAndJsIncludes.jsp" />
</head>
<body>
<div class="main-wrapper">
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />

  <div class="content-wrapper">
    <div class="content-container">
      <div class="content">
        <div>
          <h1>Edit Employee Form</h1>
          <div>
            <form action="employees/${employee.getId()}/edit" method="POST">
              <div>
                <label>Name:</label>
                <input name="name" type="text" value = "${employee.getName()}">
                ${errors.name}
              </div>
              <div>
                <label>Address:</label>
                <input name="address" type="text" value = "${employee.getAddress()}">
                ${errors.address}
              </div>
              <div>
                <label>Designation:</label>
                <input name="designation" type="text" value = "${employee.getDesignation()}">
                ${errors.designation}
              </div>
              <div>
                <label>Phone:</label>
                <input name="phone" type="text" value = "${employee.getPhone()}">
                ${errors.phone}
              </div>
              <div>
                <input type="submit"/>
              </div>
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
