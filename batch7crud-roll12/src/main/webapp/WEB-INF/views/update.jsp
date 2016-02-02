<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="updateField">
<form method="POST" action="students/${student.studentID}/update">
  First Name :
  <input type="text" value="${student.firstName}" name="firstName"/>
  <span class="error">${errorMessege.fname}</span><br />
  Last Name :
  <input type="text" value="${student.lastName}" name="lastName"/>
  <span class="error">${errorMessege.lname}</span><br />
  Age :
  <input type="text" value="${student.age}" name="age"/>
  <span class="error">${errorMessege.age}</span><br />
  Address :
  <input type="text" value="${student.address}" name="address"/>
  <span class="error">${errorMessege.address}</span><br />
  <input type="submit"/>
</form>
</div>
</div>
<%@ include file="footer.jsp" %>
