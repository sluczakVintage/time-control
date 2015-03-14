<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<span class="headerDescription">Edycja lokalizacji</span> 
<br>
<br>

<c:url var="updateLocationUrl" value="/locations/update/${location.id }" />
<c:if test="${!empty location}">
<form:form modelAttribute="editLocationForm" method="POST" action="${updateLocationUrl}">
<form:input path="id" value="${location.id}" readonly="true" type="hidden"/>
		<table>
			
			<tr> <td> Nazwa lokalizacji : </td>	<td><form:input path="name" value="${location.name }" /> </td>	<td> <form:errors class="error" path="name" /> </td> </tr>
			<tr> <td> Nazwa kontrahenta : </td>	
				<td>
				<c:if test="${!empty companies}">
				<select name="company_id">
					<c:forEach items="${companies}" var="company">
						<c:choose>
							  <c:when test="${company.nip==location.company.nip}">
							    <option value="${company.id }" selected="selected" > ${company.name }</option>
							  </c:when>
							  <c:when test="${company.nip!=location.company.nip}">
							    <option value="${company.id }" > ${company.name }</option> 
							  </c:when>
						</c:choose>									
					</c:forEach>
				</select>
				</c:if>  
				</td> <form:errors class="error" path="company_id" /> </td>
			</tr>
			<tr> <td> Numer seryjny urządzenia [*]:</td><td><form:input path="serialNumber" value="${location.serialNumber}" /> </td> 	<td> <form:errors cssClass="error"  path="serialNumber" /> </td></tr>
			<tr> <td> Identyfikator znacznika : </td>	<td><form:input path="tagID" value="${location.tagID }" /> </td>								<td> <form:errors cssClass="error"  path="tagID" /> </td> </tr>
			<tr> <td> Ulica : </td>	<td><form:input path="street" value="${location.street }" /> </td> 									<td> <form:errors cssClass="error"  path="street" /> </td></tr>
			<tr> <td> Kod pocztowy : </td>	<td><form:input path="postCode"  value="${location.postCode}" /> </td> 						<td> <form:errors cssClass="error"  path="postCode" /> </td></tr>
			<tr> <td> Miasto : </td>	<td><form:input path="city"  value="${location.city }" /> </td> 								<td> <form:errors cssClass="error"  path="city" /> </td></tr>
			<tr> <td> Data rejestracji : </td>	<td><c:out value="${location.creationDate}" /> </td> </tr>
			<tr> <td> Lokalilzację zarejestrował :</td>	<td><c:out value="${location.creator.userName }" /> </td> </tr>
			<tr> <td> Opis : </td>	
				<td> 
					<spring:bind path="details" >
			 			<textarea  name="details" rows="10" cols="60"> ${location.details} </textarea>
					</spring:bind>
				</td><form:errors cssClass="error"  path="details" /> </td>
			</tr>
			<tr> <td> Status lokalizacji : </td>	<td>
					<select name="status">				
						<c:choose>
							  <c:when test="${fn:contains(location.status, 'LOCATION_STATUS_ACTIVE')}">
							    <option value="LOCATION_STATUS_ACTIVE" selected="selected"> Aktywna </option>
							    <option value="LOCATION_STATUS_INACTIVE"> Nieaktywna </option>
							  </c:when>
							  <c:when test="${fn:contains(location.status, 'LOCATION_STATUS_INACTIVE')}">
							  	<option value="LOCATION_STATUS_ACTIVE" > Aktywna </option>
							    <option value="LOCATION_STATUS_INACTIVE" selected="selected"> Nieaktywna </option> 
							  </c:when>
							  <c:otherwise>
							  	<option value="LOCATION_STATUS_ACTIVE" > Aktywna </option>
							    <option value="LOCATION_STATUS_INACTIVE" selected="selected"> Nieaktywna </option> 
   			    			</c:otherwise>
						</c:choose>																			
					</select>
			</td>
			<td> <form:errors cssClass="error" path="status"/> </td>
			</tr>
			<tr> <td> <input type="submit" value="Aktualizuj" /> </td></tr>
	</table>
</form:form>
</c:if>



</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<p><a href="/nfcWebApp/locations/show/${location.id }">Wróć do podglądu</a></p>
		<p><a href="/nfcWebApp/locations.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>