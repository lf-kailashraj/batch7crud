<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
  <jsp:attribute name="pageTitle">Add Employee</jsp:attribute>
  <jsp:attribute name="userName">
      <c:out value="${userName}"></c:out>
  </jsp:attribute>
  <jsp:attribute name="dataContainer">
    <div class="panel-container">
      <div class="panel-header">Add Employee Information</div>
      <div class="panel-body">
        <form action="" method="post" id="employee-form">
          <label>Name:</label> <input name="name" type="text" value="${param.name}" id="name">
          <div class="error-msg" id="errorName"><c:out value="${errors['name']}"></c:out></div>

          <label>Address :</label> <input name="address" type="text" value="${param.address}" id="address">
          <div class="error-msg" id="errorAddress"><c:out value="${errors['address']}"></c:out></div>

          <label>Department:</label> <input name="department" type="text" value="${param.department}" id="department">
          <div class="error-msg" id="errorDepartment"><c:out value="${errors['department']}"></c:out></div>

          <label>Position:</label> <input name="position" type="text" value="${param.position}" id="position">
          <div class="error-msg" id="errorPosition"><c:out value="${errors['position']}"></c:out></div>

          <label>Salary:</label> <input name="salary" type="text" value="${param.salary}" id="salary">
          <div class="error-msg" id="errorSalary"><c:out value="${errors['salary']}"></c:out></div>
          <button type="submit"> Add </button>
        </form>
      </div>
    </div>
    <script type="text/javascript">
      $("#employee-form").submit(function(e){
        e.preventDefault();
        createJsonObject();
      });

      function createJsonObject(){
        var empJson = new Object();
        empJson = {
          "name" : $("#name").val(),
          "address" : $("#address").val(),
          "department" : $("#department").val(),
          "position" : $("#position").val(),
          "salary" : $("#salary").val()
        };
        var jsonString = JSON.stringify(empJson);
        $.ajax({
          url  : "/employee/add",
          data : jsonString,
          type : "POST",
          success : function(errors){
            if(errors) {
              $(".error-msg").text("");
              $("#errorName").text(errors.name);
              $("#errorAddress").text(errors.address);
              $("#errorDepartment").text(errors.department);
              $("#errorPosition").text(errors.position);
              $("#errorSalary").text(errors.salary);
            }else{
              window.location.replace("employee");
            }
          }
        });
      }

    </script>
  </jsp:attribute>
</t:genericpage>