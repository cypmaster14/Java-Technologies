<%--
  Created by IntelliJ IDEA.
  User: Ciprian
  Date: 10/21/2017
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" uri="https://journaldev.com/jsp/tlds/mytags" %>
<html>
	<head>
		<title>Record Tag</title>

		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--Import materialize.css-->
		<link rel="stylesheet"
		      href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

		<!--Let browser know website is optimized for mobile-->
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
		<style>
			body {
				display: flex;
				min-height: 100vh;
				flex-direction: column;
			}

			main {
				flex: 1 0 auto;
			}

			footer {
				position: absolute;
				bottom: 0;
				width: 100%;
			}

		</style>
	</head>
	<body>
		<tag:record key="${key}"/>
	</body>
</html>
