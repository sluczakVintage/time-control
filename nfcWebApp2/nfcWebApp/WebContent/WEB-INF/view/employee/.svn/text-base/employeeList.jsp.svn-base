<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<span class="headerDescription">Lista pracowników</span>
		<br>
		<br>
</sec:authorize>
<c:url var="searchEmployeeUrl" value="/employees/search" />
<c:if test="${!empty employees}">
<div id="list">
	<table>
		<tr>
			<th> Nazwisko </th>
			<th> Imię </th>
			<th> Stanowisko </th>
			<th> E-mail </th>
			<sec:authorize ifAnyGranted="ROLE_ADMIN"> 
			<th>Login </th>
			</sec:authorize>
		</tr>

		<c:forEach items="${employees}" var="employee">
		
		<tr onclick="document.location = '/nfcWebApp/employees/show/${employee.id }';" style="cursor: pointer;">
				<td><c:out value="${employee.lastName}" /> </td>
				<td><c:out value="${employee.name}"/></td>
				<td><c:out value="${employee.position}"/></td>
				<td><c:out value="${employee.email}"/></td>
				<sec:authorize ifAnyGranted="ROLE_ADMIN"> 
				<td><c:out value="${employee.user.userName }" /> </td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
	</div>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
			<p><a href="employees/add.html" class="adminOption">Dodaj nowego pracownika</a></p>
		</sec:authorize>
		<br>
		<h3>Wyszukiwarka</h3>
		<form:form modelAttribute="employeeSearchForm" method="POST" action="${searchEmployeeUrl}">
		<table>
		<tr><td><form:label path="name" >Imię i Nazwisko : </form:label></td><td><form:input path="name" /></td></tr>
		<sec:authorize ifAnyGranted="ROLE_ADMIN"> 
		<tr><td><form:label path="userName" >login : </form:label></td><td><form:input path="userName" /></td></tr>
		</sec:authorize>
		<tr><td><input type="submit" value="Aktualizuj" /></td></tr>
		<tr></tr>

		</table>
		</form:form>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>