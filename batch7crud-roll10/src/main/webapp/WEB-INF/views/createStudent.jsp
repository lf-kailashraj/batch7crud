<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<t:mainLayout>

    <jsp:attribute name="mainContent">

       <h2>Add New student</h2>
        <div class="student-form">
            <form action="students/create" method="post">
                <div class="form-group">
                    <label>name</label><input type="text" name="name" value="${param.name}" class="form-control"><span>${errors.name}</span><br>
                </div>
                <div class="form-group">
                    <label>address</label>
                    <input type="text" name="address" value="${param.address}" class="form-control">
                    <span>${errors.address}</span><br>
                </div>

                <div class="form-group">
                    <label>dob</label><input type="date" name="dob" value="${param.dob}" class="form-control"><span>${errors.dob}</span><br>
                </div>

                <div class="form-group">
                    <label>department</label><input type="text" name="department" value="${param.department}"
                                                    class="form-control">
                    <span>${errors.department}</span><br>
                </div>
                <div class="form-group">
                    <label>batch</label><input type="text" name="batch" value="${param.batch}"
                                               class="form-control"><span>${errors.batch}</span><br>
                </div>
                <div class="form-group">
                    <label>roll</label><input type="number" name="roll" value="${param.roll}"
                                              class="form-control"><span>${errors.roll}</span><br>
                </div>
                <input type="submit" class="button button-primary" value="Add">
            </form>

            <p>${errorMessage}</p>

        </div>
    </jsp:attribute>
</t:mainLayout>