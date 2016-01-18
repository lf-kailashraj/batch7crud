<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="employees/createProcess" method="post">
First Name:<input type="text" name="firstName"/><br>
Last Name:<input type="text" name="lastName"/><br>
Department:<input type="text" name="department"/><br>
Address:<input type="text" name="address"/><br>

<input type="submit" name="create" value="Create" >
</form>

</body>
</html>