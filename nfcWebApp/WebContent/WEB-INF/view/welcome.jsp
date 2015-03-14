<%@ include file="/WEB-INF/view/includes.jsp" %>
<%@ include file="/WEB-INF/view/header.jsp" %>	
		<div id="page">
		<div id="content">
		  <div class="post">
				<h2 class="title"><a href="#">Witaj w panelu kontrolnym NFC Time Control </a></h2>
				<p class="meta"><em>Wersja BETA</em></p>
				<div class="entry">
					<p>Za pośrednictwem tego narzędzia możesz obserwować aktywność pracowników i zarządzać ich uprawnieniami.</p>
					<p>Panel jest nadal w fazie rozwoju</p>
					
			</div>
		  </div>
		</div><!-- end #content -->
		<div id="sidebar">
		<table>
			<tr><td>Zdarzenia w systemie :</td></tr>
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