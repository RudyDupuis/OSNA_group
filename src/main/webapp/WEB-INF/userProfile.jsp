<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>OSNA - Profil utilisateur</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/STYLE/CSS/settings.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/STYLE/CSS/userProfile.css">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="Componants/Nav.jsp">
		<jsp:param value="true" name="connection" />
	</jsp:include>
	<main>
		<h1>Profil de ${user.pseudo}</h1>
	
		<div class="img"></div>
		
		<div class="center">
			<p><strong>Prénom et Nom : </strong>${user.firstName} ${user.lastName}</p>
			<p><strong>Téléphone : </strong>${user.phone}</p>
			<p><strong>Mail : </strong>${user.mail}</p>
			<p><strong>Adresse : </strong>${user.street} ${user.postalCode} ${user.city}</p>
		</div>
	</main>
	
	<jsp:include page="Componants/Footer.jsp" />
</body>
</html>