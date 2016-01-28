<%--
  Created by IntelliJ IDEA.
  User: sagarmatha
  Date: 1/28/16
  Time: 12:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>SMS | ${student.firstName} | Details</title>
  <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
  <h2>Details</h2>

  <div>
    <label>Name: </label>
    <span>${student.firstName} ${student.lastName}</span>
  </div>
  <div>
    <label>Age: </label>
    <span>${student.age}</span>
  </div>
  <div>
    <label>Address: </label>
    <span>${student.address}</span>
  </div>
  <a href="students/${student.studentID}/update" class="update">Update info</a>
  <a href="students/${student.studentID}/delete" class="delete">Delete record</a>
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
</script>
</body>
</html>
