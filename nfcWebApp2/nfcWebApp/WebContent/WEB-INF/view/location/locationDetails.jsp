<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<hr style="size:2px;">

<div class="post">
<span class="headerDescription">Szczegóły lokalizacji </span>
<br>
<br>

<c:if test="${!empty location}">

	<table>
			<tr> <th> Pole </th> <th> Wartość </th> </tr>
			<tr> <td> Identyfikator</td><td><c:out value="${location.id}" /> </td> </tr>
			<tr> <td> Nazwa lokalizacji </td>	<td><c:out value="${location.name }" /> </td> </tr>
			<tr> <td> Nazwa firmy </td>	<td><c:out value="${location.company.name}" /> </td> </tr>
			<tr><td>&nbsp;</td></tr>
			<tr> <td> Ulica </td>	<td><c:out value="${location.street }" /> </td> </tr>
			<tr> <td> Kod pocztowy </td>	<td><c:out value="${location.postCode}" /> </td> </tr>
			<tr> <td> Miasto </td>	<td><c:out value="${location.city }" /> </td> </tr>
			<tr><td>&nbsp;</td></tr>
			<tr> <td> Numer seryjny urządzenia</td>	<td><c:out value="${location.serialNumber}" /> </td> </tr>
			<tr> <td> Identyfikator znacznika </td>	<td><c:out value="${location.tagID }" /> </td> </tr>
			<tr> <td> Data rejestracji </td>	<td><fmt:formatDate value="${location.creationDate}" pattern="dd-MM-yyyy HH:mm:ss"/> </td> </tr>
			<tr> <td> Lokalizację zarejestrował </td>	<td><c:out value="${location.creator.userName }" /> </td> </tr>
			<tr><td>&nbsp;</td></tr>
			<tr> <td> Opis </td>	<td><c:out value="${location.details}" /> </td> </tr>
			<tr> <td> Status lokalizacji </td>	<td><nfc:translate value="${location.status }" /> </td> </tr>
	</table>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<p><a href="/nfcWebApp/locations/edit/${location.id}">Edytuj lokalizację</a></p>
		</sec:authorize>
		<p><a href="/nfcWebApp/locations.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>