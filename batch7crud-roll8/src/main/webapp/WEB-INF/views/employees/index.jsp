<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Grishma Shrestha <grishmashrestha@lftechnology.com>
  Date: 1/19/16
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Employees</title>
  <jsp:include page="/WEB-INF/views/layout/cssAndJsIncludes.jsp" />
</head>
<body>
<div class="main-wrapper">
  <jsp:include page="/WEB-INF/views/layout/header.jsp" />

  <div class="content-wrapper">
    <div class="content-container">
      <div class="content">
        <div class="employees-index">
          <div class="employee-index-menu clearfix">
            <a class="clearfix blue" href="/employees/create">
              <h2 class="new-emp-link">New Employee</h2>
              <img src="/images/add-user.png">
            </a>
          </div>
          <div class="employee-index-list">
            <h2>Employee Details</h2>
            <div class="employee-index-table">
              <table class="employee-index-tbl">
                <thead>
                <tr>
                  <th class="th-name">Name</th>
                  <th class="th-address">Address</th>
                  <th class="th-designation">Designation</th>
                  <th class="th-phone">Phone</th>
                  <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${employeeList}" var="employee">
                  <tr>
                    <td>${employee.getName()}</td>
                    <td>${employee.getAddress()}</td>
                    <td>${employee.getDesignation()}</td>
                    <td>${employee.getPhone()}</td>
                    <td><a href="employees/${employee.getId()}">View</a></td>
                    <td><a href="employees/${employee.getId()}/edit">Edit</a></td>
                    <td><a class="delete" href= "employees/${employee.getId()}/delete">Delete</a></td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
            <div class="pages">
              <c:if test = "${pageNo > 1}">
                <span><a href="employees?page=${pageNo - 1}">Previous</a></span>
              </c:if>
              <c:forEach begin="1" end="${lastPageNo}" var="i">
                <c:choose>
                  <c:when test="${pageNo eq i}">
                   <span>${i}</span>
                  </c:when>
                  <c:otherwise>
                    <span><a href="employees?page=${i}">${i}</a></span>
                  </c:otherwise>
                </c:choose>
              </c:forEach>

              <c:if test = "${pageNo < lastPageNo}">
                <span><a href="employees?page=${pageNo + 1}">Next</a></span>
              </c:if>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="/WEB-INF/views/layout/footer.jsp" />
</div>
<script>
  $("a.delete").click(function (event) {
    event.preventDefault();
    var href = $(this).attr("href");
    if(confirm("Are you sure you want to delete this?")) {
      var $form = $("<form method='POST'></form>");
      $form.attr("action", href);
      $("body").append($form);
      $form.submit();
    }
  });
</script>
</body>
</html>

