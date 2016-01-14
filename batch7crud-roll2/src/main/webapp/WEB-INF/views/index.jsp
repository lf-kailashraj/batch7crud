<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<title>Form Page</title>
</head>
<body>
<h1>User information form!</h1>
<p>Enter your information</p>
<form name="jspForm" method="POST" action="Display">
	<table>
		<tbody>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>Address:</td>
			<td><input type="text" name="address"></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>Contact Number:</td>
			<td><input type="number" name="contact"></td>
		</tr>
		</tbody>
	</table>
	<br/>
	<input type="reset" value="Reset">
	<input type="submit" value="Submit">
</form>
</body>
</html>
