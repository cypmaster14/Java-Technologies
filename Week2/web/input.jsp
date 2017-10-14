<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Input</title>

		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--Import materialize.css-->
		<link rel="stylesheet"
		      href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

		<!--Let browser know website is optimized for mobile-->
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>


		<script src='https://www.google.com/recaptcha/api.js'></script>
		<script>
            function onSubmit() {
                document.getElementById('demo-form').submit();
            }
		</script>

	</head>
	<body>

		<div class="container" style="margin-top: 30px">
			<div class="row">
				<form class="col s12" method="post" action="/words">
					<div class="row">
						<div class="input-field col s12">
							<input placeholder="Key" name="key" id="key" type="text"/>
							<label for="key">Key</label>
						</div>
						<div class="input-field col s12">
							<input placeholder="Value" name="value" id="value" type="text">
							<label for="value">Value</label>
						</div>

						<div class="input-field col s12">
							<select name="category">
								<option value="" disabled selected>Choose your option</option>
								<c:forEach items="${categories}" var="category">
									<c:choose>
										<c:when test="${cookie.categoryId.value==category.id}">
											<option value="${category.id}" selected>${category.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${category.id}">${category.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<label>Materialize Select</label>
						</div>


						<div class="input-field col s4">
							<input type="submit" value="Add" class="btn">
						</div>

						<div class="col s12" style="margin-top: 20px">

							<% if (Boolean.TRUE.equals(request.getAttribute("success"))) { %>
							<div class='status-message'>Thanks for the feedback!</div>
							<% } else if (Boolean.FALSE.equals(request.getAttribute("success"))) { %>
							<div class='status-message'>There was an error.</div>
							<% }%>
							<div class="g-recaptcha"
							     data-sitekey="6LcpejQUAAAAAE7KsEb_06JKtotGf_04Qr3VprX9">

							</div>
							<br>
						</div>
					</div>
				</form>

			</div>
		</div>


		<script type="text/javascript">
            $(document).ready(function () {
                $('select').material_select();
            });
		</script>

	</body>
</html>