<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
<div id="page">
<div id="content">
<div class="post">

<span class="headerDescription">Nadawanie dostępu</span>
<br>
<br>
<c:url var="saveUserUrl" value="/users/save/${employeeID}"  />
<form:form modelAttribute="newUserForm" method="POST" action="${saveUserUrl}">
	<table> 
	<tr> <td><form:label path="userName">Login [*] :</form:label> </td> 		<td> <form:input path="userName" /> </td>     <td> <form:errors cssClass="error"  path="userName" /> </td> </tr>
	<tr> <td><form:label path="userPassword">Hasło [*] :</form:label> </td>  <td> <form:input path="userPassword" type="password"/> </td> <td> <form:errors cssClass="error"  path="userPassword" /> </td> </tr>
	<tr> <td><form:label path="userPassword2">Powtórz hasło [*] :</form:label> </td>  <td> <form:input path="userPassword2" type="password"/> </td> <td> <form:errors cssClass="error"  path="userPassword2" /> </td> </tr>
	<tr> <td><form:input path="employeeID" value="${employeeID}" type="hidden"/> </td> </tr>
	<tr> <td><input type="submit" value="Zapisz" /> </td> </tr>
	</table>
</form:form>

</div>
</div><!-- end #content -->
		<div id="sidebar">
		<h3>Opcje</h3>
		<p><a href="/nfcWebApp/employees/show/${employeeID}">Wróć do szczegółów pracownika</a></p>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>