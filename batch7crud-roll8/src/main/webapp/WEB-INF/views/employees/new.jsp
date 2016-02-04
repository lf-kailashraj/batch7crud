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

  <div class="content-wrapper">
    <div class="content-container">
      <div class="content">
        <div class="employee-create">
          <h2>New Employee Details</h2>
          <div class="employee-create-form">
            <form action="employees/create" method="POST">
              <div class="employee-create-form-inputs">
                <p><label>Name</label></p>
                <p>
                  <input name="name" type="text" value="${employee.name}"/>
                  <span class="form-error">${errors.name}</span>
                </p>

              </div>
              <div class="employee-create-form-inputs">
                <p><label>Address</label></p>
                <p>
                  <input name="address" type="text" value="${employee.address}"/>
                  <span class="form-error">${errors.address}</span>
                </p>
              </div>
              <div class="employee-create-form-inputs">
                <p><label>Designation</label></p>
                <p>
                  <input name="designation" type="text" value="${employee.designation}"/>
                  <span class="form-error">${errors.designation}</span>
                </p>
              </div>
              <div class="employee-create-form-inputs">
                <p><label>Phone</label></p>
                <p>
                  <input name="phone" type="text" value="${employee.phone}"/>
                  <span class="form-error">${errors.phone}</span>
                </p>
              </div>
              <div class="employee-create-form-submit">
                <button type="submit" class="btn-submit">Submit</button>
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
