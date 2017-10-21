<%--
  Created by IntelliJ IDEA.
  User: Ciprian
  Date: 10/21/2017
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--Import materialize.css-->
		<link rel="stylesheet"
		      href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

		<!--Let browser know website is optimized for mobile-->
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

		<title>Query1</title>

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

		<div class="container" style="margin-top: 30px">
			<div class="row">
				<form action="/query1" method="post" class="col s12">
					<div class="input-field col s12">
						<input placeholder="Key" name="key" id="key" type="text">
						<label for="key">Key</label>
					</div>
					<div class="input-field col s4">
						<input type="submit" value="Search" class="btn">
					</div>
				</form>
			</div>
		</div>

	</body>
</html>
