<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>OSNA - Vente terminée</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<jsp:include page="Componants/Nav.jsp">
		<jsp:param value="true" name="connection" />
	</jsp:include>
	
	<c:if test="${sessionScope.user.id == article.idSeller}">
		<h1>${article.idUserOffer} a remporté : ${article.title}</h1>
	</c:if>
	
	<c:if test="${sessionScope.user.id == article.idUserOffer}">
		<h1>Vous avez remporté : ${article.title}</h1>
	</c:if>
	
	<section class="article">
		<div class="article-top">
			<div class="img"></div>
			<div class="article-top-text">
				<h2>Catégorie ${article.categorie}</h2>
				<p>${article.description}</p>
			</div>
		</div>

		<div class="article-center">
			<p><strong>Meilleur offre : </strong> ${article.bestOffer} points par <a href="${pageContext.request.contextPath}/profil-utilisateur?userId=${article.idUserOffer}">${article.idUserOffer}</a></p>
			<p class="article-center-right">
				<strong>Mise à prix : </strong>${article.startingPrice} points
			</p>
			<p>
				<strong>Retrait : </strong> ${article.street} ${article.postalCode} ${article.city}
			</p>
			<p class="article-center-right">
				<strong>Vendeur : </strong> <a href="${pageContext.request.contextPath}/profil-utilisateur?userId=${article.idSeller}">${article.idSeller}</a>
			</p>
		</div>
		
		<c:if test="${sessionScope.user.id == article.idSeller}">
			<form method="POST" action="">
				<input type="hidden" value="${article.id}" name="idArticle" />
				<input type="submit" value="Retrait effectué" />
			</form>
		</c:if>
	</section>
	
	<p class="message">${message}</p>
	
	<jsp:include page="Componants/Footer.jsp" />
</body>
</html>