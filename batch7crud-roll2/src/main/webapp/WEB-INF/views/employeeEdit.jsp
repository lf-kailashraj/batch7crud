<%--
  Created by IntelliJ IDEA.
  User: Romit Amgai <romitamgai@lftechnology.com>
  Date: 1/18/16
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Edit Employee"/>
</jsp:include>
<div class="body-wrapper clearfix">
    <div class="col2-left clearfix">
        <jsp:include page="sidepane.jsp"/>
        <div class="right">
            <div class="body-title">Edit Details of the Employee</div>
            <div class="employee-form clearfix">
                <form name="jspForm" method="POST" action="employees/${employee.id}/edit">
                    <ul>
                        <li><span class="name-field">Name:</span>
                            <span class="input-field"><input type="text" name="name" value="${employee.name}"></span> <span
                                    class="error-message">${validationError.name}</span>
                        </li>
                        <li><span class="name-field">Address:</span>
                            <span class="input-field"><input type="text" name="address" value="${employee.address}"></span><span
                                    class="error-message">${validationError.address}
                            </span></li>
                        <li><span class="name-field">Email:</span>
                            <span class="input-field"><input type="text" name="email" value="${employee.email}"></span><span
                                    class="error-message">${validationError.email}</span>
                        </li>
                        <li><span class="name-field"> Contact Number:</span>
                            <span class="input-field"><input type="text" name="contact" value="${employee.contact}"></span><span
                                    class="error-message">${validationError.contact}</span>
                        </li>
                    </ul>
                    <input type="submit" value="Edit">
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>