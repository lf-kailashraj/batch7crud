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
</head>
<body>
  <div>
    ${message}
    <table>
      <tbody>
        <tr>
          <td>Name:</td>
          <td>${employee.getName()}</td>
        </tr>
        <tr>
          <td>Address:</td>
          <td>${employee.getAddress()}</td>
        </tr>
        <tr>
          <td>Email:</td>
          <td>${employee.getDesignation()}</td>
        </tr>
        <tr>
          <td>Contact Number:</td>
          <td>${employee.getPhone()}</td>
        </tr>
        <tr>
          <td><a href="employees">Employees Home</a></td>
        </tr>
      </tbody>
    </table>
  </div>
</body>
</html>
