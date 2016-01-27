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
    <c:forEach items="${student}" var="student" varStatus="counter">
        <tr>
            <td><a href="students/${student.getStudentId()}/view" class="view">${counter.count+(page-1)*10}</a></td>
            <td>${student.studentID}/>
            </td>
            <td>{student.firstName}/>
            </td>
            <td>${student.lastName}/>
            </td>
            <td>${student.age}/>
            </td>
            <td>${student.address}/>
            </td>
            <td><a href="students/${student.getStudentId()}/edit" class="edit">Edit</a></td>
            <td><a href="students/${student.getStudentId()}/delete" class="delete">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${page>1}"><span><a href="students?page=${page-1}">Previous</a></span> </c:if>
    <span>
        <c:forEach begin="1" end="${totalPages}" var="counter">
            <c:if test="${page == counter}"><span> ${counter} </span></c:if>
            <c:if test="${page != counter}"><span><a href="students?page=${counter}"> ${counter} </a></span></c:if>
        </c:forEach>
    </span>
<c:if test="${page<totalPages}"><span><a href="students?page=${page+1}">Next</a></span> </c:if>
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

    var editElement = document.getElementsByClassName("edit");
    for (var i = 0; i < deleteElement.length; i++) {
        editElement[i].onclick = function (e) {
            e.preventDefault();
            var form = document.createElement('form');
            var destinationLink = e.target.getAttribute("href");
            form.setAttribute("method", "GET");
            form.setAttribute("action", destinationLink);
            form.submit();
        };
    }
    ;

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
    };
</script>
</body>
</html>
