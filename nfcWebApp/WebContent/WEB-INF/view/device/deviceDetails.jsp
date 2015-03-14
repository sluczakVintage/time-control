<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">


<c:if test="${!empty device}">
	<table>
			<tr> <td> ID :</td><td><c:out value="${device.id}" /> </td> </tr>
			<tr> <td> Typ urządzenia : </td>	<td><c:out value="${device.deviceType}" /> </td> </tr>
			<tr> <td> Numer Telefonu : </td>	<td><c:out value="${device.phoneNumber}" /> </td> </tr>
			<tr> <td> Numer seryjny /IMEI :</td>	<td><c:out value="${device.deviceImei}" /> </td> </tr>
			<tr> <td> Numer IMSI :</td>	<td><c:out value="${device.imsi}" /> </td> </tr>
			<tr> <td> Właściciel : </td>	<td><c:out value="${device.employee.name} ${device.employee.lastName }" /> </td> </tr>
			<tr> <td> Status : </td>	<td><nfc:translate value="${device.deviceStatus }" /> </td> </tr>
			<tr> <td> Opis : </td>	<td><c:out value="${device.description}" /> </td> </tr>
	</table>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<a href="/nfcWebApp/devices/edit/${device.id}">edytuj</a>
		</sec:authorize>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>