<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="detail-field">
<h2>Details</h2>
  <div class="info-field">
    <span class="info-label">Name: </span>
    <span class="info-value">${student.firstName} ${student.lastName}</span>
  </div>
  <div class="info-field">
    <span class="info-label">Age: </span>
    <span class="info-value">${student.age}</span>
  </div>
  <div class="info-field">
    <span class="info-label">Address: </span>
    <span class="info-value">${student.address}</span>
  </div>
  <div class="feature-menu">
    <a href="students/${student.studentID}/update" class="update">Update info</a>
    <a href="students/${student.studentID}/delete" class="delete">Delete record</a>
  </div>
<script>
  var deleteElement = document.getElementsByClassName("delete");
  for (var i = 0; i < deleteElement.length; i++) {
    deleteElement[i].onclick = function (e) {
      e.preventDefault();
      var form = document.createElement('form');
      var destinationLink = e.target.getAttribute("href");
      form.setAttribute("method", "POST");
      form.setAttribute("action", destinationLink);
      if (confirm("Are you sure you want to delete this record?") == true) {
        form.submit();
      }
    };
  }
</script>
</div>
</div>
<%@ include file="footer.jsp" %>