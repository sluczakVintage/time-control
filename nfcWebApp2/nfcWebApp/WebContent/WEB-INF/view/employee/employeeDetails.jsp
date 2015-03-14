<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<hr style="size:2px;">

<div class="post">
<span class="headerDescription">Szczegóły pracownika </span> 
<br>
<br>
<c:if test="${!empty employee}">
	<table>
			<tr> <th> Pole </th> <th> Wartość </th> </tr>
			<tr> <td> Identyfikator</td><td><c:out value="${employee.id}" /> </td> </tr>
			<tr> <td> Imię </td>	<td><c:out value="${employee.name }" /> </td> </tr>
			<tr> <td> Nazwisko </td>	<td><c:out value="${employee.lastName}" /> </td> </tr>
			<sec:authorize ifAllGranted="ROLE_ADMIN">
			<tr> <td> Login </td>	<td><c:out value="${employee.user.userName}" /> </td> </tr>
			</sec:authorize>
			<tr><td>&nbsp;</td></tr>
			<tr> <td> Ulica </td>	<td><c:out value="${employee.street}" /> </td> </tr>
			<tr> <td> Kod pocztowy </td>	<td><c:out value="${employee.postCode}" /> </td> </tr>
			<tr> <td> Miasto </td>	<td><c:out value="${employee.city }" /> </td> </tr>
			<tr> <td> Stanowisko </td>	<td><c:out value="${employee.position}" /> </td> </tr>
			<tr><td>&nbsp;</td></tr> 
			<tr> <td> E-mail </td>	<td><c:out value="${employee.email}" /> </td> </tr>
	</table>
	
	<br>
	<br> <br> <br>
	<table>
	<tr><th> Typ urządzenia  </th>  <th> Numer telefonu </th> <th> Status </th></tr>
	<c:forEach items="${devices}" var="device">
	<tr> 
		 <td><c:out value="${device.deviceType }" /> </td>
	     <td><c:out value="${device.phoneNumber}" /> </td>
		 <td><c:out value="${device.deviceStatus }" /> </td>
		 <td><p><a href="/nfcWebApp/employees/unsignDevice/${device.id }/${employee.id}"> Usuń</a> </p></td>
	</tr> 
	</c:forEach>
	</table>

	<p><a href="#" onclick="showhide('div2');">Wyświetl zdarzenia</a></p> 
			<!-- Start of hidden content -->
			<div id="div2" style="display: none;">
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
			<!-- end of hidden content -->



	
</c:if>


</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<p><a href="/nfcWebApp/employees/edit/${employee.id}">Edytuj</a></p>
		</sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_ADMIN"> 
		<c:if test="${empty employee.user.userName }" >			
			<p><a href="/nfcWebApp/users/add/${employee.id }"> Nadaj dostęp </a></p>
		</c:if>
		</sec:authorize>
		
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<c:url var="addDeviceToEmployeeUrl" value="/employees/addDevice/${employee.id }" />
		<p><a href="#" onclick="showhide('div1');">Dodaj urządzenie</a></p> 
			<!-- Start of hidden content -->
			<div id="div1" style="display: none;">
			<p>
			<form:form modelAttribute="addDeviceToEmployeeForm" method="POST" action="${addDeviceToEmployeeUrl}">
				<c:choose>
					<c:when test="${!empty freeDevices}">
						<table>
						<tr><td>
							<select name="device_id">
								<option value="0" > Wybierz urządzenie</option>
							<c:forEach items="${freeDevices}" var="freeDevice">
								<option value="${freeDevice.id }" > ${freeDevice.deviceType }</option>		
							</c:forEach>
							</select>
							</td></tr> 
						<tr><td><form:errors cssClass="error"  path="device_id" /></td></tr>
						<tr><td><input type="submit" value="Dodaj" /></td></tr>
						</table>
					</c:when>
					<c:otherwise>
					<p>Brak wolnych i aktywnych urządzeń</p>
					</c:otherwise>
				</c:choose>
			</form:form>
			</p>
			
			</div> 
			<!-- end of hidden content -->
		</sec:authorize>
		<p><a href="/nfcWebApp/employees.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>