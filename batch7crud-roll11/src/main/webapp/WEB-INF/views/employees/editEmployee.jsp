<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
  <jsp:attribute name="pageTitle">Edit Employee Info</jsp:attribute>
  <jsp:attribute name="userName">
      <c:out value="${userName}"></c:out>
  </jsp:attribute>
  <jsp:attribute name="dataContainer">
    <div class="panel-container">
      <div class="panel-header">Edit Employee Information</div>
      <div class="panel-body">
        <form action="/employee/${employee.empId}/edit" method="post">
          <label>Name:</label> <input name="name" type="text"  value="${employee.name}"> <c:out value="${errors.name}"></c:out> <br/>
          <label>Address :</label> <input name="address" type="text"  value="${employee.address}"> <c:out value="${errors.address}"></c:out><br/>
          <label>Department:</label> <input name="department" type="text"  value="${employee.department}"><c:out value="${errors.department}"></c:out> <br/>
          <label>Position:</label> <input name="position" type="text"  value="${employee.position}"><c:out value="${errors.position}"></c:out> <br/>
          <label>Salary:</label> <input name="salary" type="text"  value="${employee.salary}"> <c:out value="${errors.salary}"></c:out><br/>
          <button type="submit"> Update </button>
        </form>
      </div>
      </div>
  </jsp:attribute>
</t:genericpage>