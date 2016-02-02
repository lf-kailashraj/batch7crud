<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Show All Students</title>
</head>
<body>
<h2><a href="students/create" class="create">Add new student</a></h2>
<%--For displaying Previous link except for the 1st page --%>
<c:if test="${page != 1}">
    <td><a href="students?page=${page - 1}">Previous</a></td>
</c:if>
<%--For displaying Next link --%>
<c:if test="${page lt totalPages}">
    <td><a href="students?page=${page + 1}">Next</a></td>
</c:if>
<table border=1>
    <thead>
    <tr>
        <th>Student Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
        <th>Address</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentList}" var="student" varStatus="counter">
        <tr>
            <td>${student.studentID}
            </td>
            <td>${student.firstName}
            </td>
            <td>${student.lastName}
            </td>
            <td>${student.age}
            </td>
            <td>${student.address}
            </td>
            <td><a href="students/${student.studentID}/update" class="update">Update</a></td>
            <td><a href="students/${student.studentID}/delete" class="delete">Delete</a></td>
            <td><a href="students/${student.studentID}/view" class="view">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${page eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="students?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>
<script>
    var deleteElement = document.getElementsByClassName("delete");
    for (var i = 0; i < deleteElement.length; i++) {
        deleteElement[i].onclick = function (e) {
            e.preventDefault();
            var form = document.createElement('form');
            var destinationLink = e.target.getAttribute("href");
            form.setAttribute("method", "POST");
            form.setAttribute("action", destinationLink);
            if (confirm("Are you sure you want to delete this record?") == true) {
                form.submit();
            }
        };
    }
    ;

    var updateElement = document.getElementsByClassName("update");
    for (var i = 0; i < updateElement.length; i++) {
        updateElement[i].onclick = function (e) {
            e.preventDefault();
            var form = document.createElement('form');
            var destinationLink = e.target.getAttribute("href");
            form.setAttribute("method", "GET");
            form.setAttribute("action", destinationLink);
            form.submit();
        };
    }
    var viewElement = document.getElementsByClassName("view");
    for (var i = 0; i < viewElement.length; i++) {
        viewElement[i].onclick = function (e) {
            e.preventDefault();
            var form = document.createElement('form');
            var destinationLink = e.target.getAttribute("href");
            form.setAttribute("method", "GET");
            form.setAttribute("action", destinationLink);
            form.submit();
        };
    }
</script>
</body>
</html>
