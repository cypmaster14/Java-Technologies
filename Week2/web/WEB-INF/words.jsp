<%--
  Created by IntelliJ IDEA.
  User: Ciprian
  Date: 10/14/2017
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Words</title>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--Import materialize.css-->
		<link rel="stylesheet"
		      href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

		<!--Let browser know website is optimized for mobile-->
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	</head>
	<body>
		<div class="container" style="margin-top: 30px">
			<table class="highlight centered">
				<thead>
					<tr>
						<th>Category</th>
						<th>Key</th>
						<th>Value</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${infos}" var="info">
						<tr>
							<td>
									${info.category}
							</td>
							<td>
									${info.name}
							</td>
							<td>
									${info.value}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>

	</body>
</html>
