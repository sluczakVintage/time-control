<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<span class="headerDescription">Edycja urządzenia</span> 
<br>
<br>
<c:url var="updateDeviceUrl" value="/devices/update/${device.id }" />
<c:if test="${!empty device}">
<form:form modelAttribute="editDeviceForm" method="POST" action="${updateDeviceUrl}">
<form:input path="id" value="${device.id}" readonly="true" type="hidden" />
<table>
			<tr> <td> Typ urządzenia : </td>	<td><form:input path="deviceType" value="${device.deviceType}" /> </td> 	<td><form:errors cssClass="error" path="deviceType" /></td></tr>
			<tr> <td> Numer telefonu : </td>	<td><form:input path="phoneNumber" value="${device.phoneNumber}" /> </td>	<td><form:errors cssClass="error" path="phoneNumber" /></td> </tr>
			<tr> <td> Numer IMEI :</td><td><form:input path="deviceImei" value="${device.deviceImei}" /> </td> 	<td><form:errors cssClass="error" path="deviceImei" /></td></tr>
			<tr> <td> Numer IMSI :</td>			<td><form:input path="imsi" value="${device.imsi}" /> </td> 				<td><form:errors cssClass="error" path="imsi" /> </td></tr>
			<tr> <td> Właściciel : </td>		<td><c:out value="${device.employee.name} ${device.employee.lastName }" /> </td> </tr>
			<tr> <td> Status : </td>			<td>
					<select name="deviceStatus">
							<option value="null" > Wybierz status </option>
													<c:choose>
							  <c:when test="${fn:contains(device.deviceStatus, 'DEVICE_STATUS_ACTIVE')}">
							    <option value="DEVICE_STATUS_ACTIVE" selected="selected"> Aktywne </option>
							    <option value="DEVICE_STATUS_INACTIVE"> Nieaktywne </option>
							  </c:when>
							  <c:when test="${fn:contains(device.deviceStatus, 'DEVICE_STATUS_INACTIVE')}">
							  	<option value="DEVICE_STATUS_ACTIVE" > Aktywne </option>
							    <option value="DEVICE_STATUS_INACTIVE" selected="selected"> Nieaktywne </option> 
							  </c:when>
							  <c:otherwise>
							  	<option value="DEVICE_STATUS_ACTIVE" > Aktywne </option>
							    <option value="DEVICE_STATUS_INACTIVE" > Nieaktywne </option> 
   			    				</c:otherwise>
						</c:choose>	
					</select>
			</td>
			<td><form:errors cssClass="error" path="deviceStatus" /> </td>
			</tr>
			
			<tr> <td> <form:label path="description">Komentarz :</form:label> </td>	
			<td>
				 <spring:bind path="description" >
				 <textarea  name="description" rows="5" cols="40"> ${device.description } </textarea>
				 </spring:bind>
			</td><td><form:errors cssClass="error" path="description" /> </td>
	 		</tr>
	 		
	</table>
	<input type="submit" value="Aktualizuj" />
</form:form>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<p><a href="/nfcWebApp/devices/show/${device.id }">Wróć do podglądu</a></p>
		<p><a href="/nfcWebApp/devices.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>