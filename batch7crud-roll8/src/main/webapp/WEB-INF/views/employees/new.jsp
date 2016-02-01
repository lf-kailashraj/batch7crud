<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/19/16
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Create New Employee</title>
  <jsp:include page="/WEB-INF/views/layout/cssAndJsIncludes.jsp" />
</head>
<body>
<div class="main-wrapper">
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />

  <div class="container-wrapper">
    <div class="container-container">
      <div class="container">
        <div>
          Create New Employee
          <div>
            <form action="employees/create" method="POST">
              <div>
                <label>Name:</label>
                <input name="name" type="text" value="${employee.name}"/>
                ${errors.name}
              </div>
              <div>
                <label>Address:</label>
                <input name="address" type="text" value="${employee.address}"/>
                ${errors.address}
              </div>
              <div>
                <label>Designation:</label>
                <input name="designation" type="text" value="${employee.designation}"/>
                ${errors.designation}
              </div>
              <div>
                <label>Phone:</label>
                <input name="phone" type="text" value="${employee.phone}"/>
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
