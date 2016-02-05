<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="entry-field">
  <form method="POST" action="${pageContext.request.contextPath}/students/create">
    <span class="label">First Name : </span><br/>
    <input type="text" name="firstName" placeholder="first name" value="<c:out value="${param.firstName}" />"/>
    <span class="error">${errorMessage.fname}</span><br/>
    <span class="label">Last Name : </span><br/>
    <input type="text" name="lastName" placeholder="last name" value="<c:out value="${param.lastName}" />"/>
    <span class="error">${errorMessage.lname}</span><br/>
    <span class="label">Age : </span><br/>
    <input type="text" name="age" placeholder="age" value="<c:out value="${param.age}" />"/>
    <span class="error">${errorMessage.age}</span><br/>
    <span class="label">Address : </span><br/>
    <input type="text" name="address" placeholder="address" value="<c:out value="${param.address}" />"/>
    <span class="error">${errorMessage.address}</span><br/>
    <input type="submit" value="Submit"/>
  </form>
</div>
</div>
<%@ include file="footer.jsp" %>
