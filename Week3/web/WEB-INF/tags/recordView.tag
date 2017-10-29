<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="categoryId" required="true" type="java.lang.Integer" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/java"
                   user="admin" password="admin"/>

<sql:query var="result" dataSource="${dataSource}">
	select userinput.name as "name", userinput.value as "value" from userinput
	join categories
	on userinput.id = categories.id
	where categories.id= ? ;
	<sql:param value="${categoryId.intValue()}"/>
</sql:query>
<div class="container" style="margin-top: 30px">
	<table class="highlight centered">
		<thead>
			<tr>
				<th>
					<fmt:message key="words.table.head2"/>
				</th>
				<th>
					<fmt:message key="words.table.head3"/>
				</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${result.rows}" var="row">
				<tr>
					<td>
						<c:out value="${row.name}"/>
					</td>
					<td>
						<c:out value="${row.value}"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
