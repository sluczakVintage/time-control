<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<span class="headerDescription">Nowa lokalizacja</span> 
<br>
<br>

<c:url var="saveLocationUrl" value="/locations/save.html" />

<form:form modelAttribute="newLocationForm" method="POST" action="${saveLocationUrl}">
	
	<table>
		<tr> <td> <form:label path="name">Nazwa lokalizacji : </form:label>  </td><td><form:input path="name" /> </td> <td> <form:errors cssClass="error"  path="name" /> </td></tr>
		<tr> <td> <form:label path="company_id">Kontrahent [*] : </form:label> </td> 
			<td>
			<c:if test="${!empty companies}">
			<select name="company_id">
					<option value="0"> Wybierz kontrahenta</option>
				<c:forEach items="${companies}" var="company">
					<option value="${company.id }" > ${company.name }</option>		
				</c:forEach>
			</select>
			</c:if> 
			</td>
			<td><form:errors class="error" path="company_id" /> </td>
		</tr>
		<tr> <td><form:label path="tagID">Identyfikator znacznika : </form:label>  </td>								<td><form:input path="tagID" /></td>		<td> <form:errors cssClass="error" path="tagID" /> </td></tr>
		<tr> <td><form:label path="serialNumber">Numer seryjny urządzenia [*] : </form:label>  </td><td><form:input path="serialNumber" /></td>	<td> <form:errors cssClass="error"  path="serialNumber" /> </td></tr>
		<tr> <td><form:label path="street">Ulica : </form:label>  </td>								<td><form:input path="street" /></td>		<td> <form:errors cssClass="error"  path="street" /> </td></tr>
		<tr> <td> <form:label path="postCode">Kod pocztowy : </form:label>  </td>					<td><form:input path="postCode" /> </td>	<td> <form:errors cssClass="error"  path="postCode" /> </td></tr>
		<tr> <td><form:label path="city">Miasto : </form:label>  </td>								<td><form:input path="city" /></td>			<td> <form:errors cssClass="error"  path="city" /> </td></tr>
		<tr> <td> <form:label path="details">Opis : </form:label> </td>	
			 <td>
				<form:textarea path="details" rows="10" cols="60"/>
			</td>
			<td> <form:errors cssClass="error"  path="details" /> </td>
		</tr>
		<tr><td><input type="submit" value="Utwórz" /></td></tr>
	</table>
</form:form>

</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<p><a href="/nfcWebApp/employees.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>