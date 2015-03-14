<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<span class="headerDescription">Lista lokalizacji</span>
		<br>
		<br>
</sec:authorize>

<c:url var="searchLocationUrl" value="/locations/search" />
	<c:if test="${!empty locations}">
	<div id="list">
		<table>
			<tr>
				<th>Nazwa</th>
				<th>Identyfikator znacznika</th>
				<th>Data wprowadzenia</th>
				<th>Nazwa kontrahenta</th>
				<th>Status zlecenia</th>
			</tr>
	
			<c:forEach items="${locations}" var="location">
			
			<tr onclick="document.location = '/nfcWebApp/locations/show/${location.id }';" style="cursor: pointer;">
					<td><c:out value="${location.name}"/></td>
					<td><c:out value="${location.tagID}" /> </td>
					<td><fmt:formatDate value="${location.creationDate}" pattern="dd-MM-yyyy"/></td>
					<td><c:out value="${location.company.name}"/></td>
					<td><nfc:translate value="${location.status}"/></td>
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
		<p><a href="locations/add.html" class="adminOption">Dodaj nową lokalizację</a></p>
		</sec:authorize>
		<br>
		<h3>Wyszukiwarka</h3>
		<form:form modelAttribute="locationSearchForm" method="POST" action="${searchLocationUrl}">
		<table>
		<tr><td>Status : </td>
							<td><c:if test="${!empty locationStatus}">
								<select name="status">
									<option value="null" > Wszystkie </option>
								<c:forEach items="${locationStatus}" var="status">
									<option value="${status.key}" > ${status.value}</option>		
								</c:forEach>
								</select>
							</c:if>  
							</td>
		</tr> 

		<tr><td><form:label path="name" >Nazwa : </form:label></td><td><form:input path="name" /></td></tr>
		<tr><td><form:label path="city" >Miasto : </form:label></td><td><form:input path="city" /></td></tr>
		<tr><td><input type="submit" value="Aktualizuj" /></td></tr>
		
		</table>
		</form:form>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>