<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/view/includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/nfcWebApp/static/css/login.css" rel="stylesheet" type="text/css"  />

<title>NFC Time Control</title>

</head>

<body >

<div id="container">
<c:if test="${param.error == 'true'}">

</c:if>
  <div id="mainContent">
  <h1> Panel Admnistracyjny </h1>
  	<div id="box">
    <form action="/nfcWebApp/j_spring_security_check" method="post" >
    <table>
        <tr>
            <td>Login:</td><td><input name="j_username"></input></td>
        </tr>
        <tr>
            <td>Hasło:</td><td><input type="password" name="j_password" /></td>
        </tr>
        <tr>
            <td></td><td><input type="submit" value="Zaloguj"></input></td>
        </tr>
    </table>
        <c:if test="${param.error == 'true'}">
			 <div id="error" >Niepoprawny login lub hasło.  </div>
		</c:if>
		<c:if test="${param.logout == 'true'}">
			 <div id="success" > Wylogowałeś się.  </div>
		</c:if>
</form>
	</div>
	<!-- end #mainContent --></div>
<!-- end #container --></div>
</body>
</html>