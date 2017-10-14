<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Form</title>
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
			<div class="row">
				<form class="col s12" method="post" action="/word">
					<div class="row">
						<div class="input-field col s5">
							<input placeholder="Key" name="key" id="key" type="text"/>
							<label for="key">Key</label>
						</div>
						<div class="input-field col s5">
							<input placeholder="Value" name="value" id="value" type="text">
							<label for="value">Value</label>
						</div>
						<div class="input-field col s2">
							<input type="submit" value="Add" class="btn">
						</div>

					</div>

				</form>
			</div>
		</div>
	</body>
</html>