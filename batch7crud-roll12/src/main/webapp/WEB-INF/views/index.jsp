<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<!DOCTYPE html>
<sql:query var="rs" dataSource="jdbc/student">
	select * from studentinfo;
</sql:query>

<html>
<head>
	<title>DB Test</title>
</head>
<body>

<h2>Results</h2>

<c:forEach var="row" items="${rs.rows}">
	id ${row.id}<br/>
	age ${row.age}<br/>
	address ${row.address}<br/>
</c:forEach>

</body>
</html>