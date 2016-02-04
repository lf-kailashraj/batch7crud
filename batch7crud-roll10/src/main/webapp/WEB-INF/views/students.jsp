<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<t:mainLayout>

    <jsp:attribute name="mainContent">

         <h2>student List</h2>
         <div class="student-table">
            <table class="table">
              <tr>
                <th>NAME</th>
                <th>ROLL</th>
                <th>ADDRESS</th>
                <th>DEPARTMENT</th>
                <th>BATCH</th>
              </tr>
              <c:forEach items="${studentList}" var="book">
                <tr>
                  <td>${book.name}</td>
                  <td>${book.roll}</td>
                  <td>${book.address}</td>
                  <td>${book.department}</td>
                  <td>${book.batch}</td>

                  <td><a href="students/${book.id}/edit">edit</a></td>
                  <td><a class="delete" href="students/${book.id}/delete">delete</a></td>
                </tr>
              </c:forEach>
            </table>
          </div>

         <div class="pagination">
           <ul>
          <c:if test="${currentPage != 1}">
            <li><a href="students/list?page=${currentPage-1}">prev</a></li>
          </c:if>
          <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
              <c:when test="${currentPage == i}">
                <li class="active"><a href="students/list?page=${i}">${i}</a></li>
              </c:when>
              <c:otherwise>
                <li><a href="students/list?page=${i}">${i}</a></li>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          <c:if test="${currentPage != totalPages}">
            <li><a href="students/list?page=${currentPage + 1}">next</a></li>
          </c:if>
           </ul>
        </div>

     </jsp:attribute>
</t:mainLayout>




