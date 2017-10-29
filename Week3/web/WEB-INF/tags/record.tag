<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="key" required="true" %>

<c:set var="LINE" value="\n"/>

<c:set var="lines" value="${fn:split(content, LINE)}"/>
<c:forEach var="line" items="${lines}" varStatus="loop">
	<c:out value="${line} -> ${loop.index}"/>
</c:forEach>


