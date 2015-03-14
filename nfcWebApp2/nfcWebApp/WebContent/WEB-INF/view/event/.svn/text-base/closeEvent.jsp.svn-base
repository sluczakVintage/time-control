<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<span class="headerDescription">Zamykanie zdarzenia</span> 
<br>
<br>
<c:url var="saveClosedEventUrl" value="/events/saveClosed/${event.id }" />
<c:if test="${!empty event}">
<form:form modelAttribute="editEventForm" method="POST" action="${saveClosedEventUrl}">
<form:input path="id" value="${event.id }" readonly="true" type="hidden"/>
	<table>
		<tr> <td> <form:label path="eventType">Typ zdarzenia : </form:label> </td>	<td> <form:input path="eventType" value="${event.eventType}"/> </td>	<td>  <form:errors cssClass="error"  path="eventType" /> </td></tr>
		<tr> <td> Lokalizacja : </td><td> <c:out value="${event.location.name }" /> </td></tr>
		<tr> <td> Data utworzenia : </td>	<td><fmt:formatDate value="${event.eventCreationDate}" pattern="dd-MM-yyyy hh:mm:ss"/></td></tr>
		<tr> <td> Data rozpoczęcia zdarzenia : </td>	<td><fmt:formatDate value="${event.eventStartDate}" pattern="dd-MM-yyyy hh:mm:ss"/></td></tr>
		<tr> <td> Data zakończenia zdarzenia : </td>	<td><fmt:formatDate value="${event.eventFinishDate}"  pattern="dd-MM-yyyy hh:mm:ss" /></td></tr>
		<tr> <td> Status zdarzenia : </td>	<td><nfc:translate value="${event.status}"/></td></tr>
		<tr> <td> Zdarzenie utworzył : </td>	<td><c:out value="${event.creator.userName}"/></td></tr>
		<tr> <td> Zdarzenie obsługiwał : </td>	<td><c:out value="${event.user.userName}"/></td></tr>
		<tr> <td> <form:label path="comment">Komentarz : </form:label> </td>	
			 <td>
			 <spring:bind path="comment" >
			 <textarea  name="comment" rows="10" cols="60"> ${event.comment } </textarea>
			 </spring:bind>
			 </td>
			 <td> <form:errors cssClass="error"  path="comment" /> </td>
	 	</tr>
		<tr> <td> Identyfikator znacznika : </td>	<td><c:out value="${event.tagId}"/></td></tr>
		<tr> <td> Token :</td>	<td><c:out value="${event.token}"/></td></tr>
		<tr> <td> <form:input path="location_id" type="hidden" value="${event.location.id }"/> </td></tr>
		<tr> <td>	<input type="submit" value="Zamknij" /> </td> </tr>
	</table>
</form:form>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<p><a href="/nfcWebApp/events/show/${event.id }">Wróć do podglądu</a></p>
		<p><a href="/nfcWebApp/events.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>