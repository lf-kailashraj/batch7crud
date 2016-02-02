<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>add</title>
</head>
<body>

    <h2>Employee Form</h2>

    <div>
        <form action="employees/create" method="post">
            <div>
                <label>User Name :</label>
                <input type="text" name="userName" value="${param.userName}">
                <span>${errors.userName}</span>
            </div>
            <div>
                <label>Password :</label>
                <input type="password" name="password" value="${param.password}">
                <span>${errors.password}</span>
            </div>
            <div>
                <label>Full Name :</label>
                <input type="text" name="fullName" value="${param.fullName}">
                <span>${errors.fullName}</span>
            </div>
            <div>
                <label>Department :</label>
                <input type="text" name="department" value="${param.department}">
                <span>${errors.department}</span>
            </div>
            <div>
                <label>Address</label>
                <input type="text" name="address" value="${param.address}">
                <span>${errors.address}</span>
            </div>

            <div>
                <label>Age</label>
                <input type="number" name="age" value="${param.age}">
                <span>${errors.age}</span>
            </div>
            <input type="submit" value="submit">
        </form>
    </div>

</body>
</html>
