<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">

<c:url var="searchLocationUrl" value="/locations/search" />
<c:if test="${!empty locations}">
	<table>
		<tr>
			<th>nazwa</th>
			<th>tag</th>
			<th>Creation Date</th>
			<th>company name</th>
			<th>status</th>
		</tr>

		<c:forEach items="${locations}" var="location">
		<a href="locations/show/${location.id }" ><tr>
				<td><c:out value="${location.name}"/></td>
				<td><c:out value="${location.tagID}" /> </td>
				<td><fmt:formatDate value="${location.creationDate}" pattern="dd-MM-yyyy"/></td>
				<td><c:out value="${location.company.name}"/></td>
				<td><nfc:translate value="${location.status}"/></td>
			</tr></a>
		</c:forEach>
	</table>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		Wyszukiwarka:
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
		<tr></tr>
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<tr><td><a href="locations/add.html">utwórz nowe</a></td></tr>
		</sec:authorize>
		</table>
		</form:form>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>