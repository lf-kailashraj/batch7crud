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
  <% DatabaseConnector db=new DatabaseConnector();
    try {
      String query = "SELECT * FROM tbl_userinfo";

      Statement stmt = db.connection.createStatement();
      ResultSet rs = null;
      rs = stmt.executeQuery(query);
      while(rs.next()){%>
  <tr></tr><td><%=rs.getString(1)%></td>
  <td><%=rs.getString(2)%></td>
  <td><%=rs.getString(3)%></td></tr>
  <%
      }
      db.connection.close();
      System.out.println("Insert Success");
    } catch (Exception ex) {
      System.out.println("Insert Error - " + ex);
    }
  %></table>
</body>
</html>
