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

<t:userprofile firstName="${employee.firstName}"
               lastName="${employee.lastName}"
               station="${employee.station}">
</t:userprofile>
