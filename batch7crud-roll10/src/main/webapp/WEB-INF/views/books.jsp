<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binodnme
  Date: 2/3/16
  Time: 10:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book list</title>
</head>
<body>

    <div>
        <table>
            <tr>
                <th>NAME</th>
                <th>AUTHOR</th>
                <th>PUBLISHER</th>
                <th>EDITION</th>
                <th>TOTAL COPIES</th>
                <th>AVAILABLE COPIES</th>
            </tr>
            <c:forEach items="${bookList}" var="book">
                <tr>
                    <td>${book.name}</td>
                    <td>${book.author}</td>
                    <td>${book.publisher}</td>
                    <td>${book.edition}</td>
                    <td>${book.total}</td>
                    <td>${book.available}</td>

                    <%--<td><a href="students/${book.id}/edit">edit</a></td>--%>
                    <%--<td><a class="delete" href="students/${book.id}/delete">delete</a></td>--%>
                </tr>
            </c:forEach>
        </table>


    </div>
</body>
</html>
