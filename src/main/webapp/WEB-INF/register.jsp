<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>OSNA - Créer un compte</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/STYLE/CSS/settings.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/STYLE/CSS/register.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/STYLE/IMG/Favicon.png"/>
</head>
<body>
	<jsp:include page="Componants/Nav.jsp" />
	
	<h1>S'inscrire</h1>
	
	<form method="POST" action=""> 
		<div class="form">
			<h2>Nom et Prénom</h2>
			<div class="form-inputs">
				<input type="text" placeholder="Prénom" name="firstName" value="${firstNameSave}" required />
				<input type="text" placeholder="Nom" name="lastName" value="${lastNameSave}" required />
			</div>
			
			<h2>Pseudo, Mail et Téléphone</h2>
			<div class="form-inputs">
				<input type="text" placeholder="Pseudo" name="pseudo" value="${pseudoSave}" required />
				<input type="email" placeholder="Mail" name="mail" value="${mailSave}" required />
				<input type="text" placeholder="Téléphone" name="phone" value="${phoneSave}" required />
			</div>
			
			<h2>Adresse</h2>
			<div class="form-inputs">
				<input type="text" placeholder="Rue" name="street" value="${streetSave}" required />
				<input type="number" placeholder="Code Postal" name="postalCode" value="${postalCodeSave}" required />
				<input type="text" placeholder="Ville" name="city" value="${citySave}" required />
			</div>
			
			<h2>Mot de passe</h2>
			<div class="form-inputs">
				<input type="password" placeholder="Mot de passe" name="password" required />
				<input type="password" placeholder="Confirmer le mot de passe" name="confirmPassword" required />
			</div>
		</div>
		
		<input type="submit" value="Inscription" />
	</form>
	
	<p class="message">${message}</p>
	
	<jsp:include page="Componants/Footer.jsp" />
</body>
</html>