<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">



<c:url var="searchDeviceUrl" value="/devices/search" />
<c:if test="${!empty devices}">
	<table>
		<tr>
			<th>Nazwa</th>
			<th>Właściciel</th>
			<th>Numer Seryjny</th>
			<th>status</th>
		</tr>

		<c:forEach items="${devices}" var="device">
		<a href="devices/show/${device.id }" ><tr>
				<td><c:out value="${device.deviceType}"/></td>
				<td><c:out value="${device.employee.name} ${device.employee.lastName }" /> </td>
				<td><c:out value="${device.deviceImei}"/></td>
				<td><nfc:translate value="${device.deviceStatus}"/></td>
			</tr></a>
		</c:forEach>
	</table>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		Wyszukiwarka:
		<form:form modelAttribute="deviceSearchForm" method="POST" action="${searchDeviceUrl}">
		<table>
		<tr><td>Status : </td>
							<td><c:if test="${!empty deviceStatus}">
								<select name="status">
									<option value="null" > Wszystkie </option>
								<c:forEach items="${deviceStatus}" var="status">
									<option value="${status.key}" > ${status.value}</option>		
								</c:forEach>
								</select>
							</c:if>  
							</td>
		</tr> 

		<tr><td><form:label path="name" >Właściciel : </form:label></td><td><form:input path="name" /></td></tr>
		<tr><td><input type="submit" value="Aktualizuj" /></td></tr>
		<tr></tr>
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<tr><td><a href="devices/add.html">utwórz nowy</a></td></tr>
		</sec:authorize>
		</table>
		</form:form>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>