<%@ page import="com.lftechnology.batch7crud.services.DatabaseConnector" %>
<%@ page import="java.sql.*" %>

<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/13/16
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Display Page</title>
</head>
<body>
<table>

  <tr>
    <td><%=request.getAttribute("fname")%></td>
    <td><%=request.getAttribute("mname")%></td>
    <td><%=request.getAttribute("lname")%></td>
    <td><%=request.getAttribute("address")%></td>
    <td><%=request.getAttribute("grade")%></td>

  </tr>
  </table>
</body>
</html>
