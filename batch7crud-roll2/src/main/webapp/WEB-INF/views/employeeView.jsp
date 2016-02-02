<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/20/16
  Time: 4:00 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="View Page"/>
</jsp:include>
<div class="body-wrapper clearfix">
    <div class="col2-left clearfix">
        <jsp:include page="sidepane.jsp"/>
        <div class="right">
            <div class="body-title">Employee Details</div>
            <div class="view-employee">
                <ul>
                    <li>Name:<span>${employee.getName()}</span></li>
                    <li>Address:<span>${employee.getAddress()}</span></li>
                    <li>Email:<span>${employee.getEmail()}</span></li>
                    <li>Contact Number:<span>${employee.getContact()}</span></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>