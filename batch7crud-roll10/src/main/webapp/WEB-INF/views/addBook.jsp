<%--
  Created by IntelliJ IDEA.
  User: binodnme
  Date: 2/3/16
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <title>Add Book</title>
</head>
<body>
    <div>
        <form action="books/add" method="post">
            <label>name</label><input type="text" name="name" value="${param.name}"><span>${errors.name}</span><br>

            <label>author</label><input type="text" name="author" value="${param.author}"><span>${errors.author}</span><br>

            <label>publisher</label><input type="text" name="publisher" value="${param.publisher}"><span>${errors.publisher}</span><br>

            <label>edition</label><input type="text" name="edition" value="${param.edition}"><span>${errors.edition}</span><br>

            <label>copies</label><input type="number" name="copies" value="${param.copies}"><span>${errors.copies}</span><br>

            <input type="submit">
        </form>
    </div>
</body>
</html>
