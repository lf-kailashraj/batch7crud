<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com>
  Date: 1/21/16
  Time: 4:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
  <link rel="stylesheet" type="text/css" href="static/css/layout.css" />

  <title>Student</title>
</head>
<body>
<%--Id : ${student.getId()}<br>--%>
<%--Name : ${student.getName()}<br>--%>
<%--Address : ${student.getAddress()}<br>--%>
<%--Roll : ${student.getRoll()}<br>--%>

<%--<a href="students">View All</a>--%>

<div class="header">
  <div class="header-text">
    SHREE KAMANA BOARDING HIGH SCHOOL
  </div>

</div>

<div class="body clr">
  <div class="navigation">
    <ul>
      <li><a href="students">View All</a></li>
      <li><a href="logOut">Log Out</a></li>
    </ul>
  </div>
  <div class = "content">
    <table class="show">
      <col width="150">
      <col width="300">
      <tr class="row-odd">
        <td height="40">Id</td>
        <td height="40">${student.getId()}</td>
      </tr>
      <tr class="row-even">
        <td height="40">Name</td>
        <td height="40">${student.getName()}</td>
      </tr>
      <tr class="row-odd">
        <td height="40">Address</td>
        <td height="40">${student.getAddress()}</td>
      </tr>
      <tr class="row-even">
        <td height="40">Roll</td>
        <td height="40">${student.getRoll()}</td>
      </tr>
    </table>
    <div>
      <a href="students/${student.id}/edit">Edit</a>
    </div>
  </div>


  <div class="footer">
    <div class="copyright">Copyright</div>
    <div class="contact">Contact</div>
  </div>


</body>
</html>
