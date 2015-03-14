<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<span class="headerDescription">Edycja kontrahenta</span> 
<br>
<br>
<c:url var="updateCompanyUrl" value="/companies/update/${company.id }" />
<c:if test="${!empty company}">
<form:form modelAttribute="editCompanyForm" method="POST" action="${updateCompanyUrl}">
<form:input path="id" value="${company.id }" readonly="true" type="hidden"/>
	<table>
		<tr> <td> <form:label path="name">Nazwa [*] :</form:label>  </td>	<td> <form:input path="name" value="${company.name }"/></td><td><form:errors cssClass="error" path="name"/></td></tr>
		<tr> <td> <form:label path="nip">Numer NIP [*] :</form:label>  </td>	<td> <form:input path="nip" value="${company.nip}"/></td><td><form:errors cssClass="error" path="nip"/></td></tr>
		<tr> <td> <form:label path="street">Ulica :</form:label>  </td>	<td> <form:input path="street" value="${company.street }"/></td><td><form:errors cssClass="error" path="street"/></td></tr>
		<tr> <td> <form:label path="city">Miasto :</form:label>  </td>	<td> <form:input path="city" value="${company.city }"/></td><td><form:errors cssClass="error" path="city"/></td></tr>
		<tr> <td> <form:label path="postCode">Kod pocztowy :</form:label>  </td>	<td> <form:input path="postCode" value="${company.postCode}"/></td><td><form:errors cssClass="error" path="postCode"/></td></tr>
		<tr> <td> <form:label path="country">Kraj :</form:label>  </td>	<td> <form:input path="country" value="${company.country }"/></td><td><form:errors cssClass="error" path="country"/></td></tr>
		<tr> <td> <form:label path="phoneNumber">Numer telefonu :</form:label>  </td>	<td> <form:input path="phoneNumber" value="${company.phoneNumber }"/></td></tr>
		<tr> <td> <form:label path="contactPersonName">Imię osoby kontakowej :</form:label>  </td>	<td> <form:input path="contactPersonName" value="${company.contactPersonName }"/></td><td><form:errors cssClass="error" path="contactPersonName"/></td></tr>
		<tr> <td> <form:label path="contactPersonLastName">Nazwisko osoby kontakowej :</form:label>  </td>	<td> <form:input path="contactPersonLastName" value="${company.contactPersonLastName}"/></td><td><form:errors cssClass="error" path="contactPersonLastName"/></td></tr>
		<tr> <td> <form:label path="contactPersonDescription">Opis osoby kontakowej :</form:label>  </td>	<td> <form:input path="contactPersonDescription" value="${company.contactPersonDescription}"/></td><td><form:errors cssClass="error" path="contactPersonDescription"/></td></tr>
		<tr> <td> <form:label path="email">E-mail :</form:label> </td><td><form:input path="email" value="${company.email }"/></td><td><form:errors cssClass="error" path="email"/></td></tr>	
	</table>
	<input type="submit" value="Aktualizuj" />
</form:form>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<p><a href="/nfcWebApp/companies/show/${company.id }">Wróć do podglądu</a></p>
		<p><a href="/nfcWebApp/devices.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>