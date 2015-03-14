<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">



<c:url var="saveEventUrl" value="/events/save.html" />

<form:form modelAttribute="newEventForm" method="POST" action="${saveEventUrl}">
	<table> 
	<tr> <td> <form:label path="eventType">Typ Zdarzenia : </form:label> </td> <td><form:input path="eventType" /> </td> <td> <form:errors cssClass="error"  path="eventType" /> </td> </tr>
	<tr> <td> <form:label path="comment">Komentarz : </form:label> </td>	
			<td>
				 <spring:bind path="comment" >
				 <textarea  name="comment" rows="10" cols="90">  </textarea>
				 </spring:bind>
			</td><td><form:errors cssClass="error" path="comment" /> </td>
	 		</tr>
	
	
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
	<td> <form:errors cssClass="error"  path="location_id"/> </td>
	<td> <form:errors cssClass="error"  path="status"/></td>
	</tr>
	<tr> <td> <input type="submit" value="Save" /> </td></tr>
	
	</table>
</form:form>

</div>
</div><!-- end #content -->
		<div id="sidebar">

		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>