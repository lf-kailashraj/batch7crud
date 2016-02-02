<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="detailField">
<h2>Details</h2>

  <div>
    <label>Name: </label>
    <span>${student.firstName} ${student.lastName}</span>
  </div>
  <div>
    <label>Age: </label>
    <span>${student.age}</span>
  </div>
  <div>
    <label>Address: </label>
    <span>${student.address}</span>
  </div>
  <a href="students/${student.studentID}/update" class="update">Update info</a>
  <a href="students/${student.studentID}/delete" class="delete">Delete record</a>
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