<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<c:url var="updateEventUrl" value="/events/update/${event.id }" />
<c:if test="${!empty event}">
<form:form modelAttribute="editEventForm" method="POST" action="${updateEventUrl}">
	
	<table>
		<tr> <td> <form:label path="id"> ID : </form:label> </td> 					<td> <form:input path="id" value="${event.id }" readonly="true" /></td> </tr>
		<tr> <td> <form:label path="eventType">Typ zdarzenia : </form:label> </td>	<td> <form:input path="eventType" value="${event.eventType}"/> </td>	<td>  <form:errors cssClass="error"  path="eventType" /> </td></tr>
		<tr> <td> <form:label path="location_id">Lokalizacja : </form:label>	</td> 		
				<td>
				<c:if test="${!empty locations}">
					<select name="location_id">
						<option value="0" > Wybierz lokalizacje </option>
					<c:forEach items="${locations}" var="location">
						<option value="${location.id }" > ${location.name }</option>		
					</c:forEach>
					</select>
				</c:if> 
				</td>
				<td> <form:errors cssClass="error" path="location_id"/> </td>
					<td> <form:errors cssClass="error"  path="status"/></td>
		</tr>
		<tr> <td> Data Utworzenia </td>	<td><fmt:formatDate value="${event.eventCreationDate}" pattern="dd-MM-yyyy hh:mm:ss"/></td></tr>
		<tr> <td> Data rozpoczęcia zdarzenia </td>	<td><fmt:formatDate value="${event.eventStartDate}" pattern="dd-MM-yyyy hh:mm:ss"/></td></tr>
		<tr> <td> Data zakończenia zdarzenia </td>	<td><fmt:formatDate value="${event.eventFinishDate}" pattern="dd-MM-yyyy hh:mm:ss"/></td></tr>
		<tr> <td> Status zdarzenia </td>	<td><nfc:translate value="${event.status}"/></td></tr>
		<tr> <td> Zdarzenie utworzył </td>	<td><c:out value="${event.creator.userName}"/></td></tr>
		<tr> <td> Zdarzenie obsługiwał </td>	<td><c:out value="${event.user.userName}"/></td></tr>
		<tr> <td> <form:label path="comment">komentarz</form:label> </td>	
			 <td>
			 <spring:bind path="comment" >
			 <textarea  name="comment" rows="10" cols="90"> ${event.comment } </textarea>
			 </spring:bind>
			 </td>
			 <td> <form:errors cssClass="error"  path="comment" /> </td>
	 	</tr>
		<tr> <td> Tag ID </td>	<td><c:out value="${event.tagId}"/></td></tr>
		<tr> <td> Token </td>	<td><c:out value="${event.token}"/></td></tr>
		<tr> <td>	<input type="submit" value="Aktualizuj" /> </td> </tr>
	</table>
</form:form>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<c:url var="closeEventUrl" value="/events/close/${event.id }" />
		<c:set var="statusFinished" value="EVENT_STATUS_FINISHED" />
		<c:if test="${event.status eq statusFinished }">\
		basdja
		</c:if>

		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>