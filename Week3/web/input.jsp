<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
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
				<form class="col s12" method="post" action="/words">
					<div class="row">
						<div class="input-field col s12">
							<input name="key" id="key" type="text"/>
							<label for="key">
								<fmt:message key="input.label.key"/>
							</label>
						</div>
						<div class="input-field col s12">
							<input name="value" id="value" type="text">
							<label for="value">
								<fmt:message key="input.label.value"/>
							</label>
						</div>

						<div class="input-field col s12">
							<select name="category">
								<option value="" disabled selected>
									<fmt:message key="input.select.default"/>
								</option>
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
							<label></label>
						</div>


						<div class="input-field col s4">
							<input type="submit" value='<fmt:message key="input.button.submit"/>' class="btn">
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