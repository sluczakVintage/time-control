<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">


<h1>Add device</h1>

<c:url var="viewDeviceUrl" value="/devices.html" />
<a href="${viewDevicesUrl}">Show All Devices</a>

<br />
<br />
<c:url var="saveDeviceUrl" value="/devices/save.html" />

<form:form modelAttribute="newDeviceForm" method="POST" action="${saveDeviceUrl}">
	
	<table>
			<tr> <td> <form:label path="deviceType">Typ urządzenia : </form:label> </td>	<td><form:input path="deviceType" /></td> 	<td><form:errors cssClass="error" path="deviceType" /></td> </tr>
			<tr> <td> <form:label path="deviceImei">Nr Seryjny/IMEI :</form:label> </td>	<td><form:input path="deviceImei" /></td>	<td><form:errors cssClass="error" path="deviceImei" /></td> </tr>
			<tr> <td> <form:label path="imsi">Nr IMSI : </form:label> </td>					<td><form:input path="imsi" /></td>			<td><form:errors cssClass="error" path="imsi" /> </td></tr>
			<tr> <td> <form:label path="phoneNumber">Nr Telefonu : </form:label> </td>		<td><form:input path="phoneNumber" /></td>	<td><form:errors cssClass="error" path="phoneNumber" /></td></tr>
			<tr> <td> <form:label path="description">Opis : </form:label> </td>				<td><form:input path="description" /></td>	<td><form:errors cssClass="error" path="description" /> </td></tr>
			<tr><td><input type="submit" value="Save" /></td></tr>
	</table>
</form:form>

</div>
</div><!-- end #content -->
		<div id="sidebar">
		sidebar
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>