<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title>Add new user</title>
</head>
<body>
<form method="POST" action="${pageContext.request.contextPath}/students/create">
  Student ID : <input type="text" readonly="readonly" name="studentID" value="<c:out value="${student.studentID}" />" /> <br />
  First Name : <input type="text" name="firstName" value="<c:out value="${student.firstName}" />" /> <br />
  Last Name : <input type="text" name="lastName" value="<c:out value="${student.lastName}" />" /> <br />
  Age :  <input type="text" name="age" value="<c:out value="${student.age}" />" /> <br />
  Address : <input type="text" name="address" value="<c:out value="${student.address}" />" /> <br />
  <input type="submit" value="Submit" />
</form>
</body>
</html>