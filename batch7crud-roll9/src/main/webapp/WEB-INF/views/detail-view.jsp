<%--
  Created by IntelliJ IDEA.
  User: sanjay
  Date: 1/19/16
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>SMIS | ${student.getFirstName()} | Details</title>
    <base href="${pageContext.request.contextPath}/"/>

</head>
<body>
<h2>Detail View</h2>

<div>
    <label>Name: </label>
    <span>${student.getFirstName()} ${student.getMiddleName()} ${student.getLastName()}</span>
</div>
<div>
    <label>Address: </label>
    <span>${student.getAddress()}</span>
</div>
<div>
    <label>Grade: </label>
    <span>${student.getGrade()}</span>
</div>
<a href="students/${student.getId()}/edit" class="edit">Edit This</a>
<a href="students/${student.getId()}/delete" class="delete">Delete This</a>
<script>
    var deleteElement = document.getElementsByClassName("delete")[0];
    deleteElement.onclick = function (e) {
        e.preventDefault();
        var form = document.createElement('form');
        var destinationLink = e.target.getAttribute("href");
        form.setAttribute("method", "POST");
        form.setAttribute("action", destinationLink);
        if (confirm("Are you sure you want to delete this?") == true) {
            form.submit();
        }

    }
    var editElement = document.getElementsByClassName("edit")[0];
    editElement.onclick = function (e) {
        e.preventDefault();
        var form = document.createElement('form');
        var destinationLink = e.target.getAttribute("href");
        form.setAttribute("method", "GET");
        form.setAttribute("action", destinationLink);
        form.submit();
    };

</script>


</body>
</html>
