<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">

<span class="headerDescription">Nowy pracownik</span> 
<br>
<br>

<c:url var="saveEmployeeUrl" value="/employees/save.html" />

<form:form modelAttribute="newEmployeeForm" method="POST" action="${saveEmployeeUrl}">
	
		<table>
			<tr> <td> Imię : </td>			<td><form:input path="name" /> </td> 		<td> <form:errors cssClass="error"  path="name" /> </td></tr>
			<tr> <td> Nazwisko : </td>		<td><form:input path="lastName" /> </td> 	<td> <form:errors cssClass="error"  path="lastName" /></td></tr>
			<tr> <td> Ulica :</td>			<td><form:input path="street" /> </td> 		<td> <form:errors cssClass="error"  path="street" /></td></tr>
			<tr> <td> Kod pocztowy : </td>	<td><form:input path="postCode" /> </td>	<td> <form:errors cssClass="error"  path="postCode" /></td> </tr>
			<tr> <td> Miasto : </td>		<td><form:input path="city" /> </td> 		<td> <form:errors cssClass="error"  path="city" /></td></tr>
			<tr> <td> Stanowisko : </td>	<td><form:input path="position" /> </td>	<td> <form:errors cssClass="error"  path="position" /></td></tr>
			<tr> <td> E-mail : </td>		<td><form:input path="email" /> </td>		<td> <form:errors cssClass="error"  path="email"  /></td></tr>
			
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