<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${param.connection}">
	<nav>
		<a href="${pageContext.request.contextPath}/" class="nav-logo">OSNA</a>
		<div>
			<a href="vendre-un-article">Vendre un article</a> <a
				href="mon-profil" class="nav-space">Mon Profil</a>
		</div>
	</nav>
</c:if>

<c:if test="${!param.connection}">
	<nav>
		<a href="${pageContext.request.contextPath}/" class="nav-logo">OSNA</a>
		<div>
			<a href="connexion">Se connecter</a> <a href="inscription" class="nav-space">S'inscrire</a>
		</div>
	</nav>
</c:if>