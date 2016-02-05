<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<t:mainLayout>

    <jsp:attribute name="mainContent">
        <h2>Add New Employee</h2>
        <div class="employee-form">
            <form action="employees/${employee.id}/edit" method="post">
                <div class="form-group">
                    <label>User Name :</label>
                    <input type="text" name="userName" value="${employee.userName}" class="form-control">
                    <span>${errors.userName}</span>
                </div>
                <div class="form-group">
                    <label>Password :</label>
                    <input type="password" name="password" value="${employee.password}" class="form-control">
                    <span>${errors.password}</span>
                </div>
                <div class="form-group">
                    <label>Full Name :</label>
                    <input type="text" name="fullName" value="${employee.fullName}" class="form-control">
                    <span>${errors.fullName}</span>
                </div>
                <div class="form-group">
                    <label>Department :</label>
                    <input type="text" name="department" value="${employee.department}" class="form-control">
                    <span>${errors.department}</span>
                </div>
                <div class="form-group">
                    <label>Address</label>
                    <input type="text" name="address" value="${employee.address}" class="form-control">
                    <span>${errors.address}</span>
                </div>

                <div class="form-group">
                    <label>Age</label>
                    <input type="number" name="age" value="${employee.age}" class="form-control">
                    <span>${errors.age}</span>
                </div>
                <input type="submit" value="update" class="button button-primary">
            </form>
        </div>
    </jsp:attribute>

</t:mainLayout>
