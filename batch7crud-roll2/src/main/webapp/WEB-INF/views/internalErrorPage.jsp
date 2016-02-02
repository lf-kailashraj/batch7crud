<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/18/16
  Time: 12:06 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Internal Server Error"
</jsp:include>
<jsp:include page="sidepane.jsp"/>
<div class="body-title"> ${message}</div>
<jsp:include page="footer.jsp"/>