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
<form method="POST" action="students/create">
    <div>
        <label>First Name</label>
        <input type="text" value="${student.getFirstName()}" name="fname"/><span>${user_info_errors.errorFname}</span>

    </div>
    <div>
        <label>Middle Name</label>
        <input type="text" value="${student.getMiddleName()}" name="mname"/><span>${user_info_errors.errorMname}</span>
    </div>
    <div>
        <label>Last Name</label>
        <input type="text" value="${student.getLastName()}" name="lname"/><span>${user_info_errors.errorLname}</span>
    </div>
    <div>
        <label>Address</label>
        <input type="text" value="${student.getAddress()}" name="address"/><span>${user_info_errors.errorAddress}</span>
    </div>
    <div>
        <label>Grade</label>
        <select name="grade" id="grade" value="${student.getGrade()}">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select><span>${user_info_errors.errorGrade}</span>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>
