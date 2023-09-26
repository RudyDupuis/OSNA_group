<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>OSNA - Article</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/STYLE/CSS/settings.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/STYLE/CSS/article.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<c:set var="userExists" value="${not empty sessionScope.user}" />
	
	<jsp:include page="Componants/Nav.jsp">
		<jsp:param value="${userExists}" name="connection" />
	</jsp:include>

	<h1>${article.name}</h1>

	<section class="article">
		<div class="article-top">
			<c:url var="imageServletURL" value="/imageServlet">
    			<c:param name="articleId" value="${article.id}" />
			</c:url>
				
			<div class="img" style="background: url(${imageServletURL}) center center / cover"></div>
			
			<div class="article-top-text">
				<h2>Catégorie ${article.categorie}</h2>
				<p>${article.description}</p>
			</div>
		</div>

		<div class="article-center">
			<c:if test="${article.bestOffer != 0}">
				<p>
					<strong>Meilleur offre : </strong> ${article.bestOffer} points par <a
						href="${pageContext.request.contextPath}/profil-utilisateur?userId=${article.idUserBestOffer}">${article.nameUserBestOffer}</a>
				</p>
			</c:if>
			<c:if test="${article.bestOffer == 0}">
				<p>
					<strong>Meilleur offre : </strong> Pas d'offre pour le moment
				</p>
			</c:if>
			<p class="article-center-right">
				<strong>Mise à prix : </strong>${article.startingPrice} points
			</p>
			<p>
				<strong>Retrait : </strong> ${article.street} ${article.postalCode} ${article.city}
			</p>
			<p class="article-center-right">
				<strong>Vendeur : </strong> <a href="${pageContext.request.contextPath}/profil-utilisateur?userId=${article.idSeller}">${article.nameSeller}</a>
			</p>
			<p>
				<strong>Fin de l'enchère : </strong>${article.endDate}</p>
		</div>
		
		<c:if test="${userExists && sessionScope.user.id != article.idSeller}">
			<form method="POST" action="">
				<input type="hidden" value="${article.id}" name="articleId" />
				<input type="number" placeholder="${article.bestOffer != 0 ? article.bestOffer + 5 : article.startingPrice + 5} Points" name="points" />
				<input type="submit" value="Faire une offre" />
			</form>
		</c:if>
	</section>
	
	<p class="message">${message}</p>
	
	<jsp:include page="Componants/Footer.jsp" />
</body>
</html>