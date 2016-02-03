<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<t:mainLayout>

    <jsp:attribute name="mainContent">

         <h2>Employee List</h2>
         <div class="employee-table">
             <table class="table">
                 <thead>
                     <tr>
                         <th>id</th>
                         <th>user name</th>
                         <th>full name</th>
                         <th>department</th>
                         <th>address</th>
                         <th>age</th>
                         <th>options</th>
                     </tr>
                 </thead>

                 <tbody>
                     <c:forEach items="${employees}" var="employee">
                         <tr>
                             <td><c:out value="${employee.id}" /></td>
                             <td><c:out value="${employee.userName}" /></td>
                             <td><c:out value="${employee.fullName}" /></td>
                             <td><c:out value="${employee.department}" /></td>
                             <td><c:out value="${employee.address}" /></td>
                             <td><c:out value="${employee.age}" /></td>
                             <td>
                                 <a href="employees/${employee.id}/edit" class="edit button button-primary"> Edit</a>
                                 <a href="employees/${employee.id}/delete" class="delete button button-danger"> Delete</a>
                             </td>
                         </tr>
                     </c:forEach>
                 </tbody>
             </table>
         </div>

         <div class="pagination">
             <ul>
                 <c:if test="${currentPage > 1}">
                     <li><a href="employees?page=${currentPage-1}">prev</a></li>
                 </c:if>
                 <c:forEach begin="1" end="${totalPage}" var="page">
                     <c:choose>
                         <c:when test="${page == currentPage}">
                            <li class="active"><a href="employees?page=${page}">${page}</a></li>
                         </c:when>
                         <c:otherwise>
                             <li><a href="employees?page=${page}">${page}</a></li>
                         </c:otherwise>
                     </c:choose>
                 </c:forEach>
                 <c:if test="${currentPage < totalPage}">
                     <li><a href="employees?page=${currentPage + 1}">next</a></li>
                 </c:if>
             </ul>
         </div>

    </jsp:attribute>

</t:mainLayout>


