<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<c:if test="${!empty employees}">
	<table>
		<tr>
			<th>Nazwisko</th>
			<th>Imię</th>
			<th>E-mail</th>
			<th>Login</th>
			<th>Zablokowany</th>
		</tr>
		<c:set var="isActive" value="1"/>
		<c:forEach items="${employees}" var="employee">
		<a href="users/show/${employee.id }" ><tr>
				<td><c:out value="${employee.lastName}" /> </td>
				<td><c:out value="${employee.name}"/></td>
				<td><c:out value="${employee.email}"/></td>
				<td><c:out value="${employee.user.userName}"/></td>
				<td>
					<c:choose>
					<c:when test="${employee.user.isActive eq isActive}">
						<img src="/nfcWebApp/static/images/no.gif" alt="nie"/>
					</c:when>
					<c:otherwise>
						<img src="/nfcWebApp//static/images/yes.gif" alt="tak"/>
					</c:otherwise>
					</c:choose>
				</td>
				
				
			</tr></a>
		</c:forEach>
	</table>
</c:if>
</div>
</div>
<!-- end #content -->
		<div id="sidebar">
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>