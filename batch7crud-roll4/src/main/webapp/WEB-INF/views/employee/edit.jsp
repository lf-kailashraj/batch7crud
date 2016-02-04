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

<t:section sectionTitle="Edit Employee">
    <jsp:attribute name="sectionBody">
        <div class="form">
            <h2>With AJAX</h2>

            <form action="employees/${employee.id}/edit" method="post" id="ajaxForm">
                <div class="form-element">
                    <label>First Name</label>
                    <input type="text" id="first-name" name="firstName" value="${employee.firstName}">
                    <span class="error">${errors.firstName}</span>
                </div>
                <div class="form-element">
                    <label>Last Name</label>
                    <input type="text" id="last-name" name="lastName" value="${employee.lastName}">
                    <span class="error">${errors.lastName}</span>
                </div>
                <div class="form-element">
                    <label>Station</label>
                    <input type="text" id="station" name="station" value="${employee.station}">
                    <span class="error">${errors.station}</span>
                </div>
                <div class="form-element button">
                    <input id="submitBtn" type="submit" name="submit" value="Save">
                </div>
            </form>
        </div>
    </jsp:attribute>
</t:section>
