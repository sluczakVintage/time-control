<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">

<c:url var="searchEventUrl" value="/events/search" />


<c:if test="${!empty events}">
	<table class="myTable">
		<tr>
			<th>event type</th>
			<th>location name </th>
			<th>Creation Date</th>
			<th>Start Date</th>
			<th>Finish Date</th>
			<th>status</th>
			</tr>

		<c:forEach items="${events}" var="event">
		  <a href="/nfcWebApp/events/show/${event.id }" >
			<tr>
				<td><c:out value="${event.eventType}"/></td>
				<td><c:out value="${event.location.name}" /> </td>
				<td><fmt:formatDate value="${event.eventCreationDate}" pattern="dd-MM-yyyy"/></td>
				<td><fmt:formatDate value="${event.eventStartDate}" pattern="dd-MM-yyyy"/></td>
				<td><fmt:formatDate value="${event.eventFinishDate}" pattern="dd-MM-yyyy"/></td>
				<td><nfc:translate value="${event.status}"/></td>
			</tr>
		  </a>
		</c:forEach>
	</table>
</c:if>


</div>
</div><!-- end #content -->
		<div id="sidebar">
		Wyszukiwarka:
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
									<option value="0" > Wybierz lokalizacje </option>
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
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<tr><td> <a href="events/add.html">utwórz nowe</a> </td></tr>
		</sec:authorize>
		</table>
		
		
		</form:form>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>