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
  First Name : <input type="text" name="firstName" placeholder="first name" value="<c:out value="${param.firstName}" />" />
  <span class="error">${errorMessege.fname}</span><br />
  Last Name : <input type="text" name="lastName" placeholder="last name" value="<c:out value="${param.lastName}" />" />
  <span class="error">${errorMessege.lname}</span><br />
  Age :  <input type="text" name="age" placeholder="age" value="<c:out value="${param.age}" />" />
  <span class="error">${errorMessege.age}</span><br />
  Address : <input type="text" name="address" placeholder="address" value="<c:out value="${param.address}" />" />
  <span class="error">${errorMessege.address}</span><br />
  <input type="submit" value="Submit" />
</form>
</body>
</html>