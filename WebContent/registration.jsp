<%@ page import="eTargeting.ListClass" %>
<%@ page import="eTargeting.UserClass" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Inscription</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
	<link href="css/bootstrap.css" rel="stylesheet">
</head>
<body class="inscription">
	<%
	UserClass user = (UserClass)request.getAttribute("user");
	if (user.getLoggedUser(request).getUserId() != 0) {
		response.sendRedirect("/eTargeting/Dashboard");
	}
	%>
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
					<% if (user.getLoggedUser(request).getUserId() == 0) { %>
						<li><a href="Login">Connexion</a></li>
						<li><a href="Registration">Inscription</a></li>
					<% } else { %>
						<li><a href="Dashboard">Dashboard</a></li>
					<% } %>
				</ul>
			</div>
		</div>
	</nav>
	<div class="post background">
		<h1 class="title">Inscription</h1>
		<p class="infos">Inscrivez-vous en 30 secondes sur eTargeting.</p>
		<form action="Registration" method="post">
			<input class="form-control" type="text" id="user_login" name="user_login" placeholder="Adresse e-mail"> 
			<input class="form-control" type="password" id="user_password" name="user_password" placeholder="Votre mot de passe">
			<input class="form-control" type="text" id="lastName" name="lastName" placeholder="Nom"> 
			<input class="form-control" type="text" id="firstName" name="firstName" placeholder="Prénom">
			<input class="button" type="submit" value="Inscription">
		</form>
	</div>
</body>
</html>