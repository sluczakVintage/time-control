<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">

<br />
<br />
<c:url var="updateLocationUrl" value="/locations/update/${location.id }" />
<c:if test="${!empty location}">
<form:form modelAttribute="editLocationForm" method="POST" action="${updateLocationUrl}">
		<table>
			<tr> <td> ID :</td><td><form:input path="id" value="${location.id}" readonly="true" /> </td> </tr>
			<tr> <td> Nazwa Lokalizacji : </td>	<td><form:input path="name" value="${location.name }" /> </td>	<td> <form:errors class="error" path="name" /> </td> </tr>
			<tr> <td> Nazwa Firmy : </td>	
				<td><c:if test="${!empty companies}">
				<select name="company_id">
					<c:forEach items="${companies}" var="company">
						<option value="${company.id }" > ${company.name }</option>		
					</c:forEach>
				</select>
				</c:if>  
				</td> <form:errors class="error" path="company_id" /> </td>
			</tr>
			<tr> <td> Numer seryjny urządzenia [*]:</td><td><form:input path="serialNumber" value="${location.serialNumber}" /> </td> 	<td> <form:errors cssClass="error"  path="serialNumber" /> </td></tr>
			<tr> <td> tag id : </td>	<td><form:input path="tagID" value="${location.tagID }" /> </td>								<td> <form:errors cssClass="error"  path="tagID" /> </td> </tr>
			<tr> <td> Ulica : </td>	<td><form:input path="street" value="${location.street }" /> </td> 									<td> <form:errors cssClass="error"  path="street" /> </td></tr>
			<tr> <td> Kod Pocztowy : </td>	<td><form:input path="postCode"  value="${location.postCode}" /> </td> 						<td> <form:errors cssClass="error"  path="postCode" /> </td></tr>
			<tr> <td> Miasto : </td>	<td><form:input path="city"  value="${location.city }" /> </td> 								<td> <form:errors cssClass="error"  path="city" /> </td></tr>
			<tr> <td> Opis : </td>	
				<td> 
					<spring:bind path="details" >
			 			<textarea  name="details" rows="10" cols="100"> ${location.details} </textarea>
					 </spring:bind>
				</td><form:errors cssClass="error"  path="details" /> </td>
			</tr>
			<tr> <td> Data zarejestrowania : </td>	<td><c:out value="${location.creationDate}" /> </td> </tr>
			<tr> <td> Lokalilzacje zarejestrował :</td>	<td><c:out value="${location.creator.userName }" /> </td> </tr>
			<tr> <td> Status Lokalizacji : </td>	<td>
					<select name="status">
							<option value="null" > Wybierz status </option>
							<option value="LOCATION_STATUS_ACTIVE" > Aktywna </option>
							<option value="LOCATION_STATUS_INACTIVE"> Nieaktywna </option>		
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
		sidebar
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>