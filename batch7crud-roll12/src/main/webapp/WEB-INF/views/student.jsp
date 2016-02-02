<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="entryField">
<form method="POST" action="${pageContext.request.contextPath}/students/create">
  First Name : <input type="text" name="firstName" placeholder="first name" value="<c:out value="${param.firstName}" />" />
  <span class="error">${errorMessege.fname}</span><br />
  Last Name : <input type="text" name="lastName" placeholder="last name" value="<c:out value="${param.lastName}" />" />
  <span class="error">${errorMessege.lname}</span><br />
  Age :  <input type="text" name="age" placeholder="age" value="<c:out value="${param.age}" />" />
  <span class="error">${errorMessege.age}</span><br />
  Address : <input type="text" name="address" placeholder="address" value="<c:out value="${param.address}" />" />
  <span class="error">${errorMessege.address}</span><br />
  <input type="submit" value="Submit" />
</form>
</div>
</div>
<%@ include file="footer.jsp" %>
