<%@ page import="eTargeting.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Connection</title>
	<link rel="stylesheet" type="text/css" href="styles.css">
	<link href="css/bootstrap.css" rel="stylesheet">
</head>
<body class="connexion">
	<%
		UserModel user = (UserModel)request.getAttribute("user");
		System.out.println("user.getLoggedUser(request).getUserId(): " + user.getLoggedUser(request).getUserId());
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
		<h1 class="title">Connexion</h1>
		<p class="infos">Se connecter : </p>
		<form action="Login" method="post">
			<input class="form-control" type="text" id="user_email" name="user_email" placeholder="Email"> 
			<input class="form-control" type="password" id="user_password" name="user_password" placeholder="Mot de passe">
			<input class="align" type="checkbox" id="remember_me" name="remember_me">
			<label for="remember_me" class="align title">Se souvenir de moi</label>
			<input class="button" type="submit" name="connexion" value="Connexion">
		</form>
	</div>
</body>
</html>