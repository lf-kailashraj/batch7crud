<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: grishma
  Date: 1/19/16
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
    <div>
        <div>
            <a href="/employees/create">New Employee</a>
        </div>
        <div>
            List of Employees
            <div>
                <table border="1px">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Designation</th>
                        <th>Phone</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${employeeList}" var="employee">
                        <tr>
                            <td>${employee.getName()}</td>
                            <td>${employee.getAddress()}</td>
                            <td>${employee.getDesignation()}</td>
                            <td>${employee.getPhone()}</td>
                            <td><a href="employees/${employee.getId()}/edit">Edit</a></td>
                            <td><a class="delete" href= "employees/${employee.getId()}/deleteProcess">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script>
        $(".delete").click(function (event) {
            event.preventDefault();
            var href = this.getAttribute('href');
            if(confirm("Are you sure you want to delete this?")) {
                var form = document.createElement("form");
                form.action = href;
                form.method = "POST";
                $("body").append(form);
                form.submit();
            }
        });
    </script>
</body>
</html>
