<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<c:if test="${!empty event}">
<c:set var="statusCreated" value="EVENT_STATUS_CREATED"/>
	<table>
			<tr> <td> ID :</td>	<td><c:out value="${event.id}" /> </td> </tr>
			<tr> <td> Nazwa Lokalizacji </td>	<td><c:out value="${event.location.name }" /> </td> </tr>
			<tr> <td> Typ Zdarzenia </td>	<td><c:out value="${event.eventType}"/></td></tr>
			<tr> <td> Data Utworzenia </td>	<td><fmt:formatDate value="${event.eventCreationDate}" pattern="dd-MM-yyyy hh:mm:ss"/></td></tr>
			<tr> <td> Data rozpoczęcia zdarzenia </td>	<td><fmt:formatDate value="${event.eventStartDate}" pattern="dd-MM-yyyy hh:mm:ss"/></td></tr>
			<tr> <td> Data zakończenia zdarzenia </td>	<td><fmt:formatDate value="${event.eventFinishDate}" pattern="dd-MM-yyyy hh:mm:ss"/></td></tr>
			<tr> <td> Czas : </td> <td><c:out value= "${time }"  /></td></tr>
			<tr> <td> Status zdarzenia </td>	<td><nfc:translate value="${event.status}"/></td></tr>
			<tr> <td> Zdarzenie utworzył </td>	<td><c:out value="${event.creator.userName}"/></td></tr>
			<tr> <td> Zdarzenie obsługiwał </td>	<td><c:out value="${event.user.userName}"/></td></tr>
			<tr> <td> Komentarz </td>	<td><c:out value="${event.comment}"/></td></tr>
			<tr> <td> Tag ID </td>	<td><c:out value="${event.tagId}"/></td></tr>
			<tr> <td> Token </td>	<td><c:out value="${event.token}"/></td></tr>
								
	</table>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<c:url var="closeEventUrl" value="/events/close/${event.id }" />
		<c:set var="statusFinished" value="EVENT_STATUS_FINISHED" />
		<sec:authentication var="userName" property="principal.username"/>
		
		<c:choose>
			<c:when test="${event.user.userName eq userName}">
				<c:if test="${event.status eq statusFinished }">
					<p><a href="/nfcWebApp/events/close/${event.id }">Zamknij </a></p>
				<br>
				</c:if>
			</c:when>
		
			<c:otherwise>
				<sec:authorize  ifAnyGranted="ROLE_ADMIN"> 
				<c:if test="${event.status eq statusFinished }">
					<p><a href="/nfcWebApp/events/close/${event.id }">Zamknij </a></p>
				<br>
				</c:if>
				</sec:authorize>
			</c:otherwise>
		</c:choose>
		
		
		
		
		<sec:authorize  ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
			<c:if test="${event.status eq statusCreated}" >
				<a href="/nfcWebApp/events/edit/${event.id}">Edytuj</a>
			</c:if>
		</sec:authorize>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>