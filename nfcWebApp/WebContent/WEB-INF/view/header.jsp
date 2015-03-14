<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>NFC Time Control</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="<spring:url value="/static/css/style.css" htmlEscape="true" />" rel="stylesheet" type="text/css"  />
<script language="javascript"> 
<!--
var state = 'none';
function showhide(layer_ref) {
if (state == 'block') { 
	state = 'none'; 
} 
else { 
	state = 'block'; 
} 
if (document.all) { //IS IE 4 or 5 (or 6 beta) 
	eval( "document.all." + layer_ref + ".style.display = state"); 
} 
if (document.layers) { //IS NETSCAPE 4 or below 
	document.layers[layer_ref].display = state; 
} 
if (document.getElementById &&!document.all) { 
	hza = document.getElementById(layer_ref); 
	hza.style.display = state; 
} 
} 
//--> 
</script> 

</head>
<body>
	<div id="logo">
		<h1><a href="#">NFC Time Control </a></h1>
	</div>
	<div id="search">
		
		Zalogowany jako :   <sec:authentication property="principal.username"/>
		
		<a href="<c:url value="/j_spring_security_logout"/>">Wyloguj</a>
	</div>
	<hr />
	<!-- end #logo -->
	<div id="header">
		<div id="menu">
			<ul>
				<li><a href="/nfcWebApp/" >Home</a></li>
				<li ><a href="/nfcWebApp/events.do">Zdarzenia</a></li>
				<li><a href="/nfcWebApp/locations.do">Lokalizacje</a></li>
				<li><a href="/nfcWebApp/employees.do">Pracownicy</a></li>
				<li><a href="/nfcWebApp/devices.do">Urzadzenia</a></li>
				<li><a href="/nfcWebApp/companies.do">Kontrahenci</a></li>
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
				<li><a href="/nfcWebApp/users.do">Administracja</a></li>
				</sec:authorize>
			</ul>
		</div>
		<!-- end #menu -->

		<!-- end #search -->
	</div>
	<!-- end #header -->
	<!-- end #header-wrapper -->
