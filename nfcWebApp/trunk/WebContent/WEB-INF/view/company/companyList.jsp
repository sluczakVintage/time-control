<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">



<c:url var="searchCompanyUrl" value="/companies/search" />
<c:if test="${!empty companies}">
	<table>
		<tr>
			<th>ID </th>
			<th>Nazwa Podmiotu </th>
			<th>NIP </th>
			<th> Miasto </th>
			</tr>

		<c:forEach items="${companies}" var="company">
		<a href="companies/show/${company.id }" >
			<tr>
				<td><c:out value="${company.id }"/> </td>
				<td><c:out value="${company.name}"/></td>
				<td><c:out value="${company.nip}" /> </td>
				<td><c:out value="${company.city }"/> </td>
			</tr>
		</a>
		</c:forEach>
	</table>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
				Wyszukiwarka:
		<form:form modelAttribute="companySearchForm" method="POST" action="${searchCompanyUrl}">
		<table>
		<tr><td><form:label path="name" >Nazwa : </form:label></td><td><form:input path="name" /></td></tr>
		<tr><td><form:label path="nip" >NIP : </form:label></td><td><form:input path="nip" /></td></tr>
		<tr><td><form:label path="city" >Miasto : </form:label></td><td><form:input path="city" /></td></tr>
		<tr><td><input type="submit" value="Aktualizuj" /></td></tr>
		<tr></tr>
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<tr><td><a href="companies/add.html">utwórz nowe</a></td></tr>
		</sec:authorize>
		</table>
		</form:form>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>