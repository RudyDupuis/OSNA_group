<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>OSNA - Accueil</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<c:set var="userExists" value="${not empty sessionScope.user}" />

	<jsp:include page="Componants/Nav.jsp">
		<jsp:param value="${userExists}" name="connection" />
	</jsp:include>

	<header>
		<div>
			<h2 class="title">OSNA</h2>
			<p>Objets solidaire pour une nouvelle alternative</p>
		</div>
		<a href="#auction"><button>Voir les enchères</button></a>
	</header>

	<h1 id="auction">Liste des enchères</h1>

	<section class="filter">
		<form method="POST" action="" class="filter-unconnected">
			<input type="text" placeholder="Rechercher un article"
				name="filterSearch" class="filter-unconnected-search"/>

			<select name="categorie">
				<option value="all">Toutes catégories</option>
				<option value="home">Maison</option>
				<option value="garden">Jardin</option>
				<option value="electronics">Électronique</option>
				<option value="clothing">Vêtements</option>
			</select>

			<input type="submit" value="Rechercher" class="filter-unconnected-submit" />
		</form>

		<c:if test="${userExists}">
			<form method="POST" action="" class="filter-connected">
				<input type="submit" value="Mes enchères en cours" name="userInfo" class="filter-connected-btn" />
				<input type="submit" value="Mes enchères remportées" name="userInfo" class="filter-connected-btn" />
				<input type="submit" value="Mes ventes" name="userInfo" class="filter-connected-btn" /> 
				<input type="submit" value="Mes ventes terminées" name="userInfo" class="filter-connected-btn" />
			</form>
		</c:if>
	</section>

	<section class="auction-list">
		<c:forEach items="${articles}" var="article">
			<div class="article-card">
				<div class="article-card-img" ></div>
				<div>
					<h3>${article.title}</h3>
					<p class="article-card-p"><strong>Prix : </strong>${article.startingPrice} points</p>
					<p class="article-card-p" style="color: ${article.expiredDate ? '#DF7800' : 'inherit' };"><strong>Fin de l'enchère : </strong>${article.endDate}</p>
					<div class="article-card-btns">
						<c:if test="${article.expiredDate && (sessionScope.user.id == article.idSeller || sessionScope.user.id == article.idUserOffer) }">
							<form method="GET" action="vente-terminee">
								<input type="hidden" value="${article.id}" name="idArticle">
								<input type="submit" value="Voir" class="article-card-submit"/>
							</form>
						</c:if>
						<c:if test="${!article.expiredDate}">
							<form method="GET" action="article">
								<input type="hidden" value="${article.id}" name="idArticle">
								<input type="submit" value="Voir" class="article-card-submit"/>
							</form>
						</c:if>
						<c:if test="${!article.expiredDate && sessionScope.user.id == article.idSeller}">
							<form method="GET" action="vendre-un-article">
								<input type="hidden" value="${article.id}" name="idArticle">
								<input type="submit" value="Modifier" class="article-card-submit"/>
							</form>
						</c:if>
					</div>	
				</div>
			</div>
		</c:forEach>
	</section>


	<jsp:include page="Componants/Footer.jsp" />
</body>
</html>