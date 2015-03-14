<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">

<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<span class="headerDescription">Lista kontrahentów</span>
		<br>
		<br>
		
</sec:authorize>

<c:url var="searchCompanyUrl" value="/companies/search" />
<c:if test="${!empty companies}">
<div id="list">
	<table>
		<tr>
			<th> Id </th>
			<th> Nazwa kontrahenta </th>
			<th> Numer NIP </th>
			<th> Miasto </th>
			</tr>

		<c:forEach items="${companies}" var="company">
		
			<tr onclick="document.location = '/nfcWebApp/companies/show/${company.id }';" style="cursor: pointer;">
				<td><c:out value="${company.id }"/> </td>
				<td><c:out value="${company.name}"/></td>
				<td><c:out value="${company.nip}" /> </td>
				<td><c:out value="${company.city }"/> </td>
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
			<p><a href="companies/add.html" class="adminOption">Dodaj nowego kontrahenta</a></p>
		</sec:authorize>
		<br>
		<h3>Wyszukiwarka</h3>
		<form:form modelAttribute="companySearchForm" method="POST" action="${searchCompanyUrl}">
		<table>
		<tr><td><form:label path="name" >Nazwa : </form:label></td><td><form:input path="name" /></td></tr>
		<tr><td><form:label path="nip" >NIP : </form:label></td><td><form:input path="nip" /></td></tr>
		<tr><td><form:label path="city" >Miasto : </form:label></td><td><form:input path="city" /></td></tr>
		<tr><td><input type="submit" value="Aktualizuj" /></td></tr>
		<tr></tr>
		</table>
		
		</form:form>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>