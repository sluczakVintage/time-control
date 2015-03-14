<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">

<c:if test="${!empty location}">

	<table>
			<tr> <td> ID :</td><td><c:out value="${location.id}" /> </td> </tr>
			<tr> <td> Nazwa Lokalizacji : </td>	<td><c:out value="${location.name }" /> </td> </tr>
			<tr> <td> Nazwa Firmy : </td>	<td><c:out value="${location.company.name}" /> </td> </tr>
			<tr> <td> Numer seryjny urządzenia :</td>	<td><c:out value="${location.serialNumber}" /> </td> </tr>
			<tr> <td> Tag id : </td>	<td><c:out value="${location.tagID }" /> </td> </tr>
			<tr> <td> Ulica : </td>	<td><c:out value="${location.street }" /> </td> </tr>
			<tr> <td> Kod Pocztowy : </td>	<td><c:out value="${location.postCode}" /> </td> </tr>
			<tr> <td> Miasto : </td>	<td><c:out value="${location.city }" /> </td> </tr>
			<tr> <td> Opis : </td>	<td><c:out value="${location.details}" /> </td> </tr>
			<tr> <td> Data zarejestrowania : </td>	<td><fmt:formatDate value="${location.creationDate}" pattern="dd-MM-yyyy hh:mm:ss"/> </td> </tr>
			<tr> <td> Lokalilzacje zarejestrował :</td>	<td><c:out value="${location.creator.userName }" /> </td> </tr>
			<tr> <td> Status Lokalizacji : </td>	<td><nfc:translate value="${location.status }" /> </td> </tr>
	</table>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<a href="/nfcWebApp/locations/edit/${location.id}">edytuj</a>
		</sec:authorize>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>