<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>OSNA - Mon profil</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/STYLE/CSS/settings.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/STYLE/CSS/myProfile.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/STYLE/IMG/Favicon.png"/>
</head>
<body>
	<jsp:include page="Componants/Nav.jsp" />
	
	<h1>Mon Profil</h1>
	
	<p class="points"><strong>Mes points : </strong>${sessionScope.user.points} points</p>

	<form method="POST" action="" class="update-form">
		<div class="form">
			<h2>Nom et Prénom</h2>
			<div class="form-inputs">
				<input type="text" placeholder="Prénom" name="firstName" value="${firstNameSave != null ? firstNameSave : sessionScope.user.firstName}" maxlength="30" required />
				<input type="text" placeholder="Nom" name="lastName" value="${lastNameSave != null ? lastNameSave : sessionScope.user.lastName}" maxlength="30" required />
			</div>
			
			<h2>Pseudo, Mail et Téléphone</h2>
			<div class="form-inputs">
				<input type="text" placeholder="Pseudo" name="pseudo" value="${pseudoSave != null ? pseudoSave : sessionScope.user.pseudo}" maxlength="30" required/>
				<input type="email" placeholder="Mail" name="mail" value="${mailSave != null ? mailSave : sessionScope.user.mail}" maxlength="100" required/>
				<input type="text" placeholder="Téléphone" name="phone" value="${phoneSave != null ? phoneSave : sessionScope.user.phone}" maxlength="15" />
			</div>
			
			<h2>Adresse</h2>
			<div class="form-inputs">
				<input type="text" placeholder="Rue" name="street" value="${streetSave != null ? streetSave : sessionScope.user.street}" maxlength="255" required />
				<input type="number" placeholder="Code Postal" name="postalCode" value="${postalCodeSave != null ? postalCodeSave : sessionScope.user.postalCode}" min="10000" min="99999" required />
				<input type="text" placeholder="Ville" name="city" value="${citySave != null ? citySave : sessionScope.user.city}" maxlength="100" required />
			</div>
			
			<h2>Mot de passe</h2>
			<div class="form-inputs">
				<input type="password" title="une majuscule, une minuscule, un chiffre et un caractère parmit @#$%^&+=!." placeholder="Mot de passe" name="password" maxlength="50" required />
				<input type="password" placeholder="Confirmer le mot de passe" name="confirmPassword" maxlength="50" required />
			</div>
		</div>
		
		<input type="hidden" value="update"  name="action"/>
		<input type="submit" value="Enregister" class="update-form-submit"/>
	</form>
	
	<section class="other-btns">
		<form method="POST" action="">
			<input type="hidden" value="disconnection"  name="action"/>
			<input type="submit" value="Se déconnecter" />
		</form>
		<form method="POST" action="">
			<input type="hidden" value="${sure}"  name="sure"/>
			<input type="hidden" value="delete"  name="action"/>
			<input type="submit" value="Supprimer mon compte" class="other-btns-delete" />
		</form>
	</section>
	
	<p class="message">${message}</p>
	
	<jsp:include page="Componants/Footer.jsp" />
</body>
</html>