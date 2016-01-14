<%@ page import="java.util.List" %>
<%@ page import="com.lftechnology.batch7crud.model.FormInformation" %>
<%--
  Created by IntelliJ IDEA.
  User: romit
  Date: 1/14/16
  Time: 2:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Display Page</title>
</head>
<body>

<h1>Form Data</h1>
<table>
  <tbody>
  <tr>
    <td>Name:</td>
    <td>${name}</td>
  </tr>
  <tr>
    <td>Address:</td>
    <td>${address}</td>
  </tr>
  <tr>
    <td>Email:</td>
    <td>${email}</td>
  </tr>
  <tr>
    <td>Contact:</td>
    <td>${contact}</td>
  </tr>
  </tbody>
</table>
</body>
</html>
