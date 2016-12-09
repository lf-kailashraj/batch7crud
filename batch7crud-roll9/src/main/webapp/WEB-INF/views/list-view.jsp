<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/13/16
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Display Page</title>
    <base href="${pageContext.request.contextPath}/"/>
    <style>
        table td {
            border: 1px solid;
            padding: 10px;
        }

        table {
            border: 1px solid;
        }

        table th {
            border: 2px solid;
        }
    </style>
</head>
<style rel="stylesheet" href="style.css"></style>
<body>
<table>
    <thead>
    <tr>
        <th>S.N.</th>
        <th>First Name</th>
        <th>Middle Name</th>
        <th>Last Name</th>
        <th>Address</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${studentList}" var="studentList" varStatus="counter">
        <tr>
            <td><a href="students/${studentList.getId()}/view" class="view">${counter.count+(page-1)*20}</a></td>
            <td>${studentList.getFirstName()}</td>
            <td>${studentList.getMiddleName()}</td>
            <td>${studentList.getLastName()}</td>
            <td>${studentList.getAddress()}</td>
            <td>${studentList.getGrade()}</td>
            <td><a href="students/${studentList.getId()}/edit" class="edit">Edit This</a></td>
            <td><a href="students/${studentList.getId()}/delete" class="delete">Delete This</a></td>
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
            if (confirm("Are you sure you want to delete this?") == true) {
                form.submit();
            }
        };
    }
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
