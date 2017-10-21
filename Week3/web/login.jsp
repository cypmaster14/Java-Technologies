<%--
  Created by IntelliJ IDEA.
  User: Ciprian
  Date: 10/21/2017
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
	<head>
		<title>Login</title>


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
				<form class="col s12" method="post" action="/login">
					<div class="row">
						<div class="input-field col s12">
							<input type="text" name="username" id="username" required/>
							<label for="username">
								<fmt:message key="login.label.username"/>
							</label>
						</div>
						<div class="input-field col s12">
							<input type="password" name="password" id="password" required/>
							<label for="password">
								<fmt:message key="login.label.password"/>
							</label>
						</div>

						<div class="input-field col s4">
							<input type="submit" value='<fmt:message key="login.button.submit"/>' class="btn"/>
						</div>
					</div>
				</form>
			</div>
		</div>

		<h2>${language.language}</h2>

	</body>
</html>
