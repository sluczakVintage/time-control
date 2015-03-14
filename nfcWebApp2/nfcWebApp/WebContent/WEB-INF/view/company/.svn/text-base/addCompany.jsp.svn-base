<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">


<span class="headerDescription">Nowy kontrahent</span> 
<br>
<br>
<c:url var="saveCompanyUrl" value="/companies/save.html" />

<form:form modelAttribute="newCompanyForm" method="POST" action="${saveCompanyUrl}">
<table>
		<tr> <td> <form:label path="name">Nazwa [*] : </form:label>  </td><td><form:input path="name" /> </td><td><form:errors cssClass="error" path="name"/></td></tr>
		<tr> <td><form:label path="nip">Numer NIP [*] : </form:label>  </td><td><form:input path="nip" /></td><td><form:errors cssClass="error" path="nip"/></td></tr>
		<tr> <td><form:label path="street">Ulica : </form:label>  </td><td><form:input path="street" /></td><td><form:errors cssClass="error" path="street"/></td></tr>
		<tr> <td> <form:label path="postCode">Kod pocztowy : </form:label>  </td><td><form:input path="postCode" /> </td><td><form:errors cssClass="error" path="postCode"/></td></tr>
		<tr> <td><form:label path="city">Miasto : </form:label>  </td><td><form:input path="city" /></td><td><form:errors cssClass="error" path="city"/></td></tr>
		<tr> <td> <form:label path="country">Kraj : </form:label>  </td><td><form:input path="country"/> </td><td><form:errors cssClass="error" path="country"/></td></tr>
		<tr> <td><form:label path="phoneNumber">Telefon: </form:label>  </td><td><form:input path="phoneNumber"/></td><td><form:errors cssClass="error" path="phoneNumber"/></td></tr>
		<tr> <td> <form:label path="contactPersonName">Imię osoby kontaktowej : </form:label>  </td><td><form:input path="contactPersonName"/> </td><td><form:errors cssClass="error" path="contactPersonName"/></td></tr>
		<tr> <td><form:label path="contactPersonLastName">Nazwisko osoby kontaktowej : </form:label>  </td><td><form:input path="contactPersonLastName"/></td><td><form:errors cssClass="error" path="contactPersonLastName"/></td></tr>
		<tr> <td> <form:label path="contactPersonDescription">Opis osoby kontaktowej : </form:label> </td><td><form:input path="contactPersonDescription"/></td><td><form:errors cssClass="error" path="contactPersonDescription"/></td></tr>	
		<tr> <td> <form:label path="email">E-mail : </form:label> </td><td><form:input path="email"/></td><td><form:errors cssClass="error" path="email"/></td></tr>	
		
		<tr><td><input type="submit" value="Utwórz" /></td></tr>
</table>
</form:form>

</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<p><a href="/nfcWebApp/devices.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>