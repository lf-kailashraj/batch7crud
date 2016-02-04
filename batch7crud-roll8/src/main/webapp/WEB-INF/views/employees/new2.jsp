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
            <form action="employees/create2" method="POST">
              <div class="employee-create-form-inputs">
                <p><label>Name</label></p>
                <p>
                  <input id="name" name="name" type="text" value="${employee.name}"/>
                  <span class="form-error">${errors.name}</span>
                </p>

              </div>
              <div class="employee-create-form-inputs">
                <p><label>Address</label></p>
                <p>
                  <input id="address" name="address" type="text" value="${employee.address}"/>
                  <span class="form-error">${errors.address}</span>
                </p>
              </div>
              <div class="employee-create-form-inputs">
                <p><label>Designation</label></p>
                <p>
                  <input id="designation" name="designation" type="text" value="${employee.designation}"/>
                  <span class="form-error">${errors.designation}</span>
                </p>
              </div>
              <div class="employee-create-form-inputs">
                <p><label>Phone</label></p>
                <p>
                  <input id="phone" name="phone" type="text" value="${employee.phone}"/>
                  <span class="form-error">${errors.phone}</span>
                </p>
              </div>
              <div class="employee-create-form-submit">
                <button type="submit" class="btn-submit" id="submit-btn">Submit</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="test"></div>

  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />

</div>
<script>
  $("#submit-btn").click(function(e){
      e.preventDefault();
      submitForm();
    }
  );

  function submitForm() {
    var name = $('#name').val();
    var address = $('#address').val();
    var designation = $('#designation').val();
    var phone = $('#phone').val();

    var jsonObject = new Object();
    jsonObject.name = name;
    jsonObject.address = address;
    jsonObject.designation = designation;
    jsonObject.phone = phone;

    var jsonData = JSON.stringify(jsonObject);

    $.ajax({
      url: 'employees/create2',
      type: 'post',
      data: jsonData,
      success: function(data) {
        if (data.success) {
          alert(data.success);
          window.location.replace("employees");
        }
        else {
          $('.form-error').empty();
          $('.form-error:eq(0)').html(data.name);
          $('.form-error:eq(1)').html(data.address);
          $('.form-error:eq(2)').html(data.designation);
          $('.form-error:eq(3)').html(data.phone);
        }
      },
      error: function(data) {
        $("#test").text("Something went wrong :(");
      }
    });
  }
</script>
</body>
</html>
