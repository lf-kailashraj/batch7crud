<%--
  Created by IntelliJ IDEA.
  User: binodnme
  Date: 2/3/16
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>create student</title>
</head>
<body>
    <div>
        <form id="createForm">
            <label>name</label><input id="name" type="text" name="name" value="${param.name}"><span id="name-error"></span><br>

            <label>address</label><input id="address" type="text" name="address" value="${param.address}"><span id="address-error"></span><br>

            <label>dob</label><input id="date" type="date" name="dob" value="${param.dob}"><span id="dob-error"></span><br>

            <label>department</label><input id="department" type="text" name="department" value="${param.department}"><span id="department-error"></span><br>

            <label>batch</label><input id="batch" type="text" name="batch" value="${param.batch}"><span id="batch-error"></span><br>

            <label>roll</label><input id="roll" type="number" name="roll" value="${param.roll}"><span id="roll-error"></span><br>

            <input type="submit">
        </form>

        <p>${errorMessage}</p>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script src="static/js/create.js"></script>
</body>
</html>
