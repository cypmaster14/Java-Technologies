<%--
  Created by IntelliJ IDEA.
  User: Ciprian
  Date: 10/21/2017
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
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
		<title>Query2</title>

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
		<div class="container" style="margin-top: 30px">
			<div class="row">
				<form action="/query2" method="post" class="col s12">
					<div class="input-field col s12">
						<select name="category">
							<option value="" disabled selected>
								<fmt:message key="input.select.default"/>
							</option>
							<option value="1">RO</option>
							<option value="2">EN</option>
							<option value="3">AU</option>
						</select>
					</div>
					<div class="input-field col s4">
						<input type="submit" value='<fmt:message  key="query1.search.button"/>' class="btn">
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
