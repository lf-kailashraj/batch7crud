<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<t:mainLayout>

    <jsp:attribute name="mainContent">
        <h2>Add New Employee</h2>
        <div class="employee-form">
            <form action="employees/create" method="post" id="ajaxForm">
                <div class="form-group">
                    <label>User Name :</label>
                    <input type="text" name="userName" value="${param.userName}" class="form-control" id="username">
                    <span id="username-error"></span>
                </div>

                <div class="form-group">
                    <label>Password :</label>
                    <input type="password" name="password" value="${param.password}" class="form-control" id="password">
                    <span id="password-error"></span>
                </div>

                <div class="form-group">
                    <label>Full Name :</label>
                    <input type="text" name="fullName" value="${param.fullName}" class="form-control" id="fullname">
                    <span id="fullname-error"></span>
                </div>

                <div class="form-group">
                    <label>Department :</label>
                    <input type="text" name="department" value="${param.department}" class="form-control" id="department">
                    <span id="department-error"></span>
                </div>

                <div class="form-group">
                    <label>Address</label>
                    <input type="text" name="address" value="${param.address}" class="form-control" id="address">
                    <span id="address-error"></span>
                </div>

                <div class="form-group">
                    <label>Age</label>
                    <input type="number" name="age" value="${param.age}" class="form-control" id="age">
                    <span id="age-error"></span>
                </div>

                <input type="submit" value="submit" class="button button-primary">
            </form>
        </div>
    </jsp:attribute>

</t:mainLayout>