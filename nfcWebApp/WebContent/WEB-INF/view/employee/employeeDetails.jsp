<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">

<c:if test="${!empty employee}">
	<table>
			<tr> <td> ID :</td><td><c:out value="${employee.id}" /> </td> </tr>
			<tr> <td> Imię : </td>	<td><c:out value="${employee.name }" /> </td> </tr>
			<tr> <td> Nazwisko : </td>	<td><c:out value="${employee.lastName}" /> </td> </tr>
			<tr> <td> Ulica :</td>	<td><c:out value="${employee.street}" /> </td> </tr>
			<tr> <td> Kod Pocztowy : </td>	<td><c:out value="${employee.postCode}" /> </td> </tr>
			<tr> <td> Miasto : </td>	<td><c:out value="${employee.city }" /> </td> </tr>
			<tr> <td> Stanowisko : </td>	<td><c:out value="${employee.position}" /> </td> </tr>
			<tr> <td> E-mail : </td>	<td><c:out value="${employee.email}" /> </td> </tr>
			<sec:authorize ifAllGranted="ROLE_ADMIN"> 
			<tr> <td> Login : </td>	<td><c:out value="${employee.user.userName}" /> </td> </tr>
			</sec:authorize>
	</table>
	
	<br>


	
	<br> <br> <br>
	<table>
	<tr><th> Typ Urządzenia  </th>  <th> Numer Telefonu </th> <th> Status </th></tr>
	<c:forEach items="${devices}" var="device">
	<tr> 
		 <td><c:out value="${device.deviceType }" /> </td>
	     <td><c:out value="${device.phoneNumber}" /> </td>
		 <td><c:out value="${device.deviceStatus }" /> </td>
		 <td><p><a href="/nfcWebApp/employees/unsignDevice/${device.id }/${employee.id}"> Usuń</a> </p></td>
	</tr> 
	</c:forEach>
	</table>
	
</c:if>


</div>
</div><!-- end #content -->
		<div id="sidebar">
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<p><a href="/nfcWebApp/employees/edit/${employee.id}">Edytuj</a></p>
		</sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_ADMIN"> 
		<c:if test="${empty employee.user.userName }" >
			<br>
			<p><a href="/nfcWebApp/users/add/${employee.id }"> Nadaj dostęp </a></p>
		</c:if>
		</sec:authorize>
		<br>
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
								<option value="0" > Wybierz urządzenie :</option>
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
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>