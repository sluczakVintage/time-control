<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<span class="headerDescription">Szczegóły urządzenia </span> 
<br>
<br>

<c:if test="${!empty device}">
	<table>
			<tr> <th> Pole </th> <th> Wartość </th> </tr>
			<tr> <td> Identyfikator </td><td><c:out value="${device.id}" /> </td> </tr>
			<tr> <td> Typ urządzenia </td>	<td><c:out value="${device.deviceType}" /> </td> </tr>
			<tr> <td> Numer telefonu </td>	<td><c:out value="${device.phoneNumber}" /> </td> </tr>
			<tr><td>&nbsp;</td></tr>
			<tr> <td> Właściciel </td>	<td><c:out value="${device.employee.name} ${device.employee.lastName }" /> </td> </tr>
			<tr> <td> Numer IMEI </td>	<td><c:out value="${device.deviceImei}" /> </td> </tr>
			<tr> <td> Numer IMSI </td>	<td><c:out value="${device.imsi}" /> </td> </tr>
			<tr><td>&nbsp;</td></tr>
			<tr> <td> Opis </td>	<td><c:out value="${device.description}" /> </td> </tr>
			<tr> <td> Status </td>	<td><nfc:translate value="${device.deviceStatus }" /> </td> </tr>
			
	</table>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<p><a href="/nfcWebApp/devices/edit/${device.id}">Edytuj</a></p>
		</sec:authorize>
		<p><a href="/nfcWebApp/devices.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>