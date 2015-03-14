<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<span class="headerDescription">Lista zdarzeń</span>
		<br>
		<br>
</sec:authorize>
<c:url var="searchEventUrl" value="/events/search" />


<c:if test="${!empty events}">
<div id="list">
	<table class="myTable">
		<tr>
			<th>Typ zdarzenia</th> 
			<th>Nazwa lokalizacji</th>
			<th>Data wprowadzenia</th>
			<th>Data rozpoczęcia</th>
			<th>Data zakończenia</th>
			<th>Status</th>
			</tr>

		<c:forEach items="${events}" var="event">
		  
			<tr onclick="document.location = '/nfcWebApp/events/show/${event.id }';" style="cursor: pointer;">
				<td><c:out value="${event.eventType}"/></td>
				<td><c:out value="${event.location.name}" /> </td>
				<td><fmt:formatDate value="${event.eventCreationDate}" pattern="dd-MM-yyyy"/></td>
				<td><fmt:formatDate value="${event.eventStartDate}" pattern="dd-MM-yyyy"/></td>
				<td><fmt:formatDate value="${event.eventFinishDate}" pattern="dd-MM-yyyy"/></td>
				<td><nfc:translate value="${event.status}"/></td>
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
			<p><a href="events/add.html" class="adminOption">Dodaj nowe zdarzenie</a></p>
		</sec:authorize>
		<br>
		<h3>Wyszukiwarka</h3>
		<form:form modelAttribute="eventSearchForm" method="POST" action="${searchEventUrl}">
		<table>
		<tr><td>Status : </td>
							<td><c:if test="${!empty eventStatus}">
								<select name="status">
									<option value="null" > Wszystkie </option>
								<c:forEach items="${eventStatus}" var="status">
									<option value="${status.key}" > ${status.value}</option>		
								</c:forEach>
								</select>
							</c:if>  
							</td>
		</tr> 
		<tr><td>Lokalizacja : </td>
								 <td><c:if test="${!empty locations}">
								<select name="location_id">
									<option value="0" > Wybierz lokalizację </option>
								<c:forEach items="${locations}" var="location">
									<option value="${location.id }" > ${location.name }</option>		
								</c:forEach>
								</select>
							</c:if>  
							</td>
		</tr> 
<!--  	<tr><td>Data rozpoczęcia : </td><td> TO DO </td></tr>
		<tr><td>Data zakończenia : </td><td> TO DO</td></tr> -->
		<tr><td><input type="submit" value="Aktualizuj" /></td></tr>
		
		</table>
		
		
		</form:form>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>