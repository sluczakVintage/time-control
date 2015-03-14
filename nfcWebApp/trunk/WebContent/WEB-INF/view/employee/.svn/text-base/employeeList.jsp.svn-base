<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<c:url var="searchEmployeeUrl" value="/employees/search" />
<c:if test="${!empty employees}">
	<table>
		<tr>
			<th>Nazwisko</th>
			<th>Imię</th>
			<th>Pozycja</th>
			<th>E-mail</th>
			<sec:authorize ifAnyGranted="ROLE_ADMIN"> 
			<th>Login </th>
			</sec:authorize>
		</tr>

		<c:forEach items="${employees}" var="employee">
		<a href="employees/show/${employee.id }" ><tr>
				<td><c:out value="${employee.lastName}" /> </td>
				<td><c:out value="${employee.name}"/></td>
				<td><c:out value="${employee.position}"/></td>
				<td><c:out value="${employee.email}"/></td>
				<sec:authorize ifAnyGranted="ROLE_ADMIN"> 
				<td><c:out value="${employee.user.userName }" /> </td>
				</sec:authorize>
			</tr></a>
		</c:forEach>
	</table>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		Wyszukiwarka:
		<form:form modelAttribute="employeeSearchForm" method="POST" action="${searchEmployeeUrl}">
		<table>
		<tr><td><form:label path="name" >Imię i Nazwisko : </form:label></td><td><form:input path="name" /></td></tr>
		<sec:authorize ifAnyGranted="ROLE_ADMIN"> 
		<tr><td><form:label path="userName" >login : </form:label></td><td><form:input path="userName" /></td></tr>
		</sec:authorize>
		<tr><td><input type="submit" value="Aktualizuj" /></td></tr>
		<tr></tr>
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<tr><td><a href="employees/add.html">dodaj nowego pracownika</a></td></tr>
		</sec:authorize>
		</table>
		</form:form>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>