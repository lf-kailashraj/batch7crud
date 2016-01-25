<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/14/16
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Student Registration</title>
    <base href="/students"/>

</head>
<body>
<form action="${pageContext.request.contextPath}/students/create" method="POST">

    <div>
        <label for="fname">First Name: </label>
        <input type="text" placeholder="First Name" name="fname" id="fname" value="${fname}"/><span>${errorFname}</span>
    </div>
    <div>
        <label for="mname">Middle Name: </label>
        <input type="text" placeholder="Middle Name" name="mname" id="mname" value="${mname}"/><span>${errorMname}</span>
    </div>

    <div>
        <label for="lname">Last Name: </label>
        <input type="text" placeholder="Last Name" name="lname" id="lname" value="${lname}"/><span>${errorLname}</span>
    </div>
    <div>
        <label for="address">Address: </label>
        <input type="text" placeholder="Address" name="address" id="address" value="${address}"/><span>${errorAddress}</span>
    </div>
    <div>
        <label for="grade">Grade: </label>
        <select name="grade" id="grade" value="${grade}">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select><span>${errorGrade}</span>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>
