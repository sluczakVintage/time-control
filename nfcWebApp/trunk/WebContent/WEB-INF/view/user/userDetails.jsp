<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<c:set var="isActive" value="1"/>
<c:if test="${!empty employee}">
	<table>
			<tr> <td> ID :</td><td><c:out value="${employee.user.id}" /> </td> </tr>
			<tr> <td> Imię : </td>	<td><c:out value="${employee.name }" /> </td> </tr>
			<tr> <td> Nazwisko : </td>	<td><c:out value="${employee.lastName}" /> </td> </tr>
			<tr> <td> Rola : </td>	<td><c:out value="${privilege.role}" /> </td> </tr>
			<tr> <td> E-mail : </td>	<td><c:out value="${employee.email}" /> </td> </tr>
			<tr> <td> Login : </td>	<td><c:out value="${employee.user.userName}" /> </td> </tr>
			<tr> <td> Data zarejestrowania : </td>	<td><fmt:formatDate value="${employee.user.creationDate}" pattern="dd-MM-yyyy hh:mm:ss"/> </td> </tr>
			<tr> <td> Zablokowany : </td> <td>
					<c:choose>
					<c:when test="${employee.user.isActive eq isActive}">
						<img src="/nfcWebApp/static/images/no.gif" alt="nie"/>
					</c:when>
					<c:otherwise>
						<img src="/nfcWebApp//static/images/yes.gif" alt="tak"/>
					</c:otherwise>
					</c:choose>
			</td></tr>
	</table>
</c:if>


</div>
</div><!-- end #content -->
		<div id="sidebar">
		<c:url var="addPrivilegeToUserUrl" value="/users/addPrivilege/${employee.id}/${employee.user.id}" />
		<c:url var="lockUserUrl" value="/users/lockUser/${employee.id}/${employee.user.id}" />
		<c:url var="unlockUserUrl" value="/users/unlockUser/${employee.id}/${employee.user.id}" />
			<c:choose>
				<c:when test="${employee.user.isActive eq isActive}">
					<p><a href="${lockUserUrl}">Zablokuj użytkownika</a></p>
				</c:when>
				<c:otherwise>
					<p><a href="${unlockUserUrl}">Odblokuj użytkownika</a></p>
				</c:otherwise>
				</c:choose>
		<p><a href="#" onclick="showhide('div1');">Nadaj Uprawnienie</a></p> 
			<!-- Start of hidden content -->
			<div id="div1" style="display: none;">
			<form:form modelAttribute="addPrivilegeToUserForm" method="POST" action="${addPrivilegeToUserUrl}">
			<table>
			<tr><td>
				<c:if test="${!empty roles}">
				<p>	<select name="role">
						<option value="null" > Wybierz rolę :</option>
					<c:forEach items="${roles}" var="role">
						<option value="${role.key }" > ${role.value}</option>		
					</c:forEach>
					</select>
					</p>
				</c:if>
			</td></tr> 
			<tr><td><form:errors cssClass="error"  path="role" /></td></tr>
			<tr><td><p><input type="submit" value="Nadaj" /></p></td></tr>
			</table>
			</form:form>
			</div> 
			<!-- end of hidden content -->
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>