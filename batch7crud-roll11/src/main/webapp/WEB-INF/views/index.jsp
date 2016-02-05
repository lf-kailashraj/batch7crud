<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="pageTitle">Dashboard</jsp:attribute>
    <jsp:attribute name="userName">
        <c:out value="${userName}"></c:out>
    </jsp:attribute>
    <jsp:attribute name="dataContainer">
        <h1>Employee Management System</h1>
        <h2>Dashboard</h2>
    </jsp:attribute>
</t:genericpage>
