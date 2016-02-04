<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="update-field">
<form method="POST" action="students/${student.studentID}/update">
  <span class="label">First Name :</span><br/>
  <input type="text" value="${student.firstName}" name="firstName"/>
  <span class="error">${errorMessege.fname}</span><br/>
  <span class="label">Last Name :</span><br/>
  <input type="text" value="${student.lastName}" name="lastName"/>
  <span class="error">${errorMessege.lname}</span><br/>
  <span class="label">Age :</span><br/>
  <input type="text" value="${student.age}" name="age"/>
  <span class="error">${errorMessege.age}</span><br/>
  <span class="label">Address :</span><br/>
  <input type="text" value="${student.address}" name="address"/>
  <span class="error">${errorMessege.address}</span><br/>
  <input type="submit"/>
</form>
</div>
</div>
<%@ include file="footer.jsp" %>
