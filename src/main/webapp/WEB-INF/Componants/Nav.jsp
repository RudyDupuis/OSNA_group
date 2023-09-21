<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${param.connection}">
	<nav>
		<a href="${pageContext.request.contextPath}/">OSNA</a> <a href="vendre-un-article">Vendre un article</a> <a href="mon-profil">Mon Profil</a>
	</nav>
</c:if>

<c:if test="${!param.connection}">
	<nav>
		<a href="${pageContext.request.contextPath}/">OSNA</a> <a href="connexion">Se connecter</a> <a href="inscription">S'inscrire</a>
	</nav>
</c:if>