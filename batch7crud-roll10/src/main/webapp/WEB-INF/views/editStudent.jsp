<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<t:mainLayout>

    <jsp:attribute name="mainContent">
        <div class="student-form">
          <form action="students/${student.id}/edit" method="post">
            <div class="form-group">
              <label>name</label><input type="text" name="name" value="${student.name}" class="form-control"><span>${errors.name}</span><br>
            </div>

            <div class="form-group">
              <label>address</label><input type="text" name="address" value="${student.address}" class="form-control"><span>${errors.address}</span><br>
            </div>
            <div class="form-group">
              <label>dob</label><input type="date" name="dob" value="${student.dob}"  class="form-control"><span>${errors.dob}</span><br>
            </div>
            <div class="form-group">
            <label>department</label><input type="text" name="department" value="${student.department}"  class="form-control"><span>${errors.department}</span><br>
            </div>

            <div class="form-group">
              <label>batch</label><input type="text" name="batch" value="${student.batch}"  class="form-control"><span>${errors.batch}</span><br>
            </div>
              <div class="form-group">
              <label>roll</label><input type="number" name="roll" value="${student.roll}"  class="form-control"><span>${errors.roll}</span><br>
              </div>
            <input type="submit" class="button button-primary">
          </form>
          <p>${errorMessage}</p>
        </div>
    </jsp:attribute>
</t:mainLayout>