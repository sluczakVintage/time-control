<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
		<div id="page">
		<div id="content">
		  <div class="post">
				<h2 class="title"><a href="#">Witaj w panelu kontrolnym NFC Time Control </a></h2>
				<p class="meta"><em>Wersja BETA</em></p>
				<div class="entry">
					<p>Za pośrednictwem tego narzędzia możesz obserwować aktywność pracowników i zarządzać ich uprawnieniami.</p>
					<h3 class="headerDescription">Szybki start</h3>
					<sec:authorize ifAnyGranted="ROLE_SUPER_USER,ROLE_ADMIN">
					<div style="margin-left:20px">
						<p><a href="events/add.html" class="adminOption">Dodaj nowe zdarzenie</a><br>
						<a href="locations/add.html" class="adminOption">Dodaj nową lokalizację</a><br>
						<a href="employees/add.html" class="adminOption">Dodaj nowego pracownika</a><br>
						<a href="devices/add.html" class="adminOption">Dodaj nowe urządzenie</a><br>
						<a href="companies/add.html" class="adminOption">Dodaj nowego kontrahenta</a></p>
					</div>
					</sec:authorize>
					<p>ą, ć, ę, ł, ń, ó, ś, ź, ż</p>
					<p>Ą, Ć, Ę, Ł, Ń, Ó, Ś, Ź, Ż</p>
					
			</div>
		  </div>
		</div><!-- end #content -->
		<div id="sidebar">
		<h3>Zdarzenia w systemie:</h3>
		<table>
			<tr><td>Wprowadzone :</td><td><c:out value="${created}"/></td></tr>
			<tr><td>Rozpoczęte :</td><td><c:out value="${started}"/></td></tr>
			<tr><td>Zakończone :</td><td><c:out value="${finished}"/></td></tr>
			<tr><td>Zamknięte :</td><td><c:out value="${closed}"/></td></tr>
		</table>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
<%@ include file="/WEB-INF/view/footer.jsp" %>