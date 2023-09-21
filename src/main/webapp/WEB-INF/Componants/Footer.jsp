<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="currentYear" value="<%= java.time.Year.now().getValue() %>" />

<footer>
	<p>Copyright © ${currentYear} OSNA</p>
</footer>