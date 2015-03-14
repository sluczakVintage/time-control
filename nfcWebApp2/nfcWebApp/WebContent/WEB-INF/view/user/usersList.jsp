<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
		<span class="headerDescription">Lista użytkowników</span>
		<br>
		<br>
<c:if test="${!empty employees}">
<div id="list">
	<table>
		<tr>
			<th>Nazwisko</th>
			<th>Imię</th>
			<th>E-mail</th>
			<th>Login</th>
			<th>Aktywny</th>
		</tr>
		<c:set var="isActive" value="1"/>
		<c:forEach items="${employees}" var="employee">
		
		<tr onclick="document.location = '/nfcWebApp/users/show/${employee.id }';" style="cursor: pointer;">
				<td><c:out value="${employee.lastName}" /> </td>
				<td><c:out value="${employee.name}"/></td>
				<td><c:out value="${employee.email}"/></td>
				<td><c:out value="${employee.user.userName}"/></td>
				<td>
					<c:choose>
					<c:when test="${employee.user.isActive eq isActive}">
						<img src="/nfcWebApp/static/images/yes.gif" alt="tak"/>
					</c:when>
					<c:otherwise>
						<img src="/nfcWebApp//static/images/no.gif" alt="nie"/>
					</c:otherwise>
					</c:choose>
				</td>
				
				
			</tr>
		</c:forEach>
	</table>
	</div>
</c:if>
</div>
</div>
<!-- end #content -->
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>