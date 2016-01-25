<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/18/16
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Details</title>
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<form method="POST" action="students/${student.getId()}/edit">
    <div>
        <label>First Name</label>
        <input type="text" value="${student.getFirstName()}" name="fname"/>

    </div>
    <div>
        <label>Middle Name</label>
        <input type="text" value="${student.getMiddleName()}" name="mname"/>
    </div>
    <div>
        <label>Last Name</label>
        <input type="text" value="${student.getLastName()}" name="lname"/>
    </div>
    <div>
        <label>Address</label>
        <input type="text" value="${student.getAddress()}" name="address"/>
    </div>
    <div>
        <label>Grade</label>
        <input type="text" value="${student.getGrade()}" name="grade"/>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>
