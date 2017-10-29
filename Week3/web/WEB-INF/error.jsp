<%--
  Created by IntelliJ IDEA.
  User: Ciprian
  Date: 10/14/2017
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
	<head>
		<title>Error</title>
	</head>
	<body>
		<h2>
			Message:  <%= exception.getMessage()%>
		</h2>
		<h2>
			Go to
			<a href="../home.jsp">
				Home page
			</a>
		</h2>
	</body>
</html>
