<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">

<c:if test="${!empty company}">

	<table>
			<tr> <td> ID :</td>	<td><c:out value="${company.id}" /> </td> </tr>
			<tr> <td> Nazwa :</td><td><c:out value="${company.name }" /></td></tr>
			<tr> <td> NIP :</td><td><c:out value="${company.nip}" /></td></tr>
			<tr> <td>Ulica :  </td><td><c:out value="${company.street}" /></td></tr>
			<tr> <td> Kod Pocztowy :  </td><td><c:out value="${company.postCode}" /> </td></tr>
			<tr> <td>Miasto :  </td><td><c:out value="${company.city }" /></td></tr>
			<tr> <td>Kraj :   </td><td><c:out value="${ company.country }"/> </td></tr>
			<tr> <td>Telefon:   </td><td><c:out value="${company.phoneNumber }"/></td></tr>
			<tr> <td> Imię osoby kontakowej :  </td><td><c:out value="${company.contactPersonName}"/> </td></tr>
			<tr> <td>Nazwisko osoby kontakowej:   </td><td><c:out value="${company.contactPersonLastName}"/></td></tr>
			<tr> <td> Opis osoby kontakowej </td><td><c:out value="${company.contactPersonDescription}"/></td></tr>	
				
	</table>
	<br><br><br>
	
	<table>
		<tr>
			<th>Nazwa</th>
			<th>Tag</th>
			<th>Data utworzenia</th>
			<th>Status</th>
		</tr>

		<c:forEach items="${locations}" var="location">
			<tr>
				<td><c:out value="${location.name}"/></td>
				<td><c:out value="${location.tagID}" /> </td>
				<td><fmt:formatDate value="${location.creationDate}" pattern="dd-MM-yyyy"/></td>
				<td><nfc:translate value="${location.status}"/></td>
			</tr>
		</c:forEach>
	</table>
	
	
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN"> 
		<a href="/nfcWebApp/companies/edit/${company.id}">edytuj</a>
		</sec:authorize>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>