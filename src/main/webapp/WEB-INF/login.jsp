<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>OSNA - Connexion</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="Componants/Nav.jsp">
		<jsp:param value="false" name="connection" />
	</jsp:include>

	<main>
		<h1>Se connecter</h1>

		<section class="form">
			<form method="POST" action="">
				<input type="text" placeholder="Identifiant" name="id" required />
				<input type="text" placeholder="Mot de passe" name="password"
					required /> <input type="submit" value="Connexion" />
			</form>

			<a href="inscription"><button class="btn-secondary">Créer
					un compte</button></a>
		</section>

		<p class="message">${message}</p>

	</main>
	
	<jsp:include page="Componants/Footer.jsp" />
</body>
</html>