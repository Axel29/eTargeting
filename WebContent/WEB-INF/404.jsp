<%@ page import="eTargeting.model.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - 404 Not Found</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/landing-page.css" rel="stylesheet">
	<link href="css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/eTargeting/Index">E-targeting</a>
			</div>
			<div class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<%
					UserModel user = (UserModel)request.getAttribute("user");
					if (user.getLoggedUser(request).getUserId() == 0) {
					%>
						<li><a href="Login">Connexion</a></li>
						<li><a href="Registration">Inscription</a></li>
					<% } else { %>
						<li><a href="Dashboard">Dashboard</a></li>
					<% } %>
				</ul>
			</div>
		</div>
	</nav>
	<div class="intro-header">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="intro-message">
						<h1>Page introuvable</h1>
						<hr class="intro-divider">
						<p class="shadowed-p">
							Oops ! Une erreur s'est produite.
							<br />
							Il semblerait que la page à laquelle vous essayez d'accéder n'existe plus ou ait été déplacée.
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>