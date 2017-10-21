<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="key" required="true" %>
<c:import url="/inputs.csv" var="content"/>

<c:set var="LINE" value="\n"/>

<c:set var="lines" value="${fn:split(content, LINE)}"/>
<c:forEach var="line" items="${lines}" varStatus="loop">
	<c:out value="${line} -> ${loop.index}"/>
</c:forEach>


<%--<c:forTokens items="${content}" delims='${initParam.get("LINE_DELIMITER")}' var="line" varStatus="loop">--%>
<%--<c:forTokens items="${line}" delims="," var="value" varStatus="loop">--%>
<%--${line} -> ${loop.index}--%>
<%--<br>--%>
<%--<c:choose>--%>
<%--<c:when test="${loop.index==0 && value==key}">--%>
<%--<c:out value="${value}"/>--%>
<%--</c:when>--%>
<%--</c:choose>--%>
<%--</c:forTokens>--%>
<%----%>
<%--</c:forTokens>--%>


