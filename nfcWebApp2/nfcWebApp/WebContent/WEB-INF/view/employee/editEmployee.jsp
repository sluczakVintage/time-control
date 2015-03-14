<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">
<span class="headerDescription">Edycja pracownika</span> 
<br>
<br>
<c:url var="updateEmployeeUrl" value="/employees/update/${employee.id }" />
<c:if test="${!empty employee}">
<form:form modelAttribute="editEmployeeForm" method="POST" action="${updateEmployeeUrl}">
<form:input path="id" value="${employee.id}" readonly="true" type="hidden"/> 
		<table>
			<tr> <td> Login : </td> 		<td><c:out value="${employee.user.userName }" /> </td> </tr>
			<tr> <td> Imię : </td>			<td><form:input path="name" value="${employee.name}" /> </td> 			<td> <form:errors cssClass="error"  path="name" /> </td></tr>
			<tr> <td> Nazwisko : </td>		<td><form:input path="lastName" value="${employee.lastName}" /> </td> 	<td> <form:errors cssClass="error"  path="lastName" /></td></tr>
			<tr> <td> Ulica :</td>			<td><form:input path="street" value="${employee.street}" /> </td> 		<td> <form:errors cssClass="error"  path="street" /></td></tr>
			<tr> <td> Kod pocztowy : </td>	<td><form:input path="postCode" value="${employee.postCode}" /> </td>	<td> <form:errors cssClass="error"  path="postCode" /></td> </tr>
			<tr> <td> Miasto : </td>		<td><form:input path="city" value="${employee.city}" /> </td> 			<td> <form:errors cssClass="error"  path="city" /></td></tr>
			<tr> <td> Stanowisko : </td>	<td><form:input path="position" value="${employee.position}" /> </td>	<td> <form:errors cssClass="error"  path="position" /></td></tr>
			<tr> <td> E-mail : </td>		<td><form:input path="email" value="${employee.email}" /> </td>			<td> <form:errors cssClass="error"  path="email"  /></td></tr>
	</table>
	<input type="submit" value="Aktualizuj" />
</form:form>
</c:if>
</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<p><a href="/nfcWebApp/employees/show/${employee.id }">Wróć do podglądu</a></p>
		<p><a href="/nfcWebApp/employees.do">Wróć do listy</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>