<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/19/16
  Time: 2:27 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Page Not Found"/>
</jsp:include>
<jsp:include page="sidepane.jsp"/>
<div class="body-title"> ${message}</div>
<jsp:include page="footer.jsp"/>