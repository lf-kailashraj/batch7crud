<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href = "${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action = "students/${student.getId()}/edit" method = "post">
Roll:<input type="number" name="roll" value = "${student.getRoll()}"><br/>  
Name:<input type="text" name="name" value = "${student.getName()}"><br/>
<input type="submit" value="submit"/>    
</form>
</body>
</html>