<%--
  Created by IntelliJ IDEA.
  User: Pratish Shrestha <pratishshrestha@lftechnology.com>
  Date: 1/14/16
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:section sectionTitle="Employees Table">
    <jsp:attribute name="sectionBody">

        <div class="table">
            <a href="employees/create" class="add-btn">Add Employee</a>
            <table>
                <tr>
                    <td>SN</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Station</td>
                    <td>Action</td>
                </tr>

                <c:set var="count" value="${(currentPage - 1) * 20 + 1}"/>

                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td>${count}</td>
                        <td><a href="employees/${employee.id}">${employee.firstName}</a></td>
                        <td>${employee.lastName}</td>
                        <td>${employee.station}</td>
                        <td>
                            <a href="employees/${employee.id}/edit">Edit</a>
                            <a class="delete-btn" href="employees/${employee.id}/delete">Delete</a>
                        </td>
                    </tr>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                </c:forEach>
            </table>
        </div>
        </jsp:attribute>

        <jsp:attribute name="sectionFooter">
            <div class="pagination">
                <ul>
                    <c:if test="${currentPage != 1}">
                        <li><a href="employees?page=${currentPage - 1}">Previous</a></li>
                    </c:if>

                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:if test="${i == currentPage}">
                            <li>${i}</li>
                        </c:if>
                        <c:if test="${i != currentPage}">
                            <li><a href="employees?page=${i}">${i}</a></li>
                        </c:if>
                    </c:forEach>

                    <c:if test="${currentPage != noOfPages}">
                        <li><a href="employees?page=${currentPage + 1}">Next</a></li>
                    </c:if>
                </ul>
            </div>
        </jsp:attribute>

</t:section>

