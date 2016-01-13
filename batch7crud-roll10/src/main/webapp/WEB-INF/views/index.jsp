<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Hello</title>
</head>
<body>
	<div>Hello !!</div>
	<div>
        <form action="add" method="post">
            <label>firstname</label><input type="text" name="fname">
            <label>lastname</label><input type="text" name="lname">
            <input type="submit" value="submit">
        </form>
    </div>
</body>
</html>
