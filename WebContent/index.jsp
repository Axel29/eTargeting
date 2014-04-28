<%@ page import="eTargeting.ListClass" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/landing-page.css" rel="stylesheet">
	<link href="css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
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
					<% if (session.getAttribute("userId") == null) { %>
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
						<h1>E-Targeting</h1>
						<h3>Plateforme d'envoi d'emails</h3>
						<hr class="intro-divider">
						<ul class="list-inline intro-social-buttons">
							<li>
								<a href="#" class="btn btn-default btn-lg">
									<i class="fa fa-twitter fa-fw"></i> 
									<span class="network-name">Twitter</span>
								</a>
							</li>
							<li>
								<a href="#" class="btn btn-default btn-lg">
									<i class="fa fa-facebook fa-fw"></i> 
									<span class="network-name">Facebook</span>
								</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="content-section-a">
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-sm-6">
					<hr class="section-heading-spacer">
					<div class="clearfix"></div>
					<h2 class="section-heading">Envois illimité</h2>
					<p class="lead">
						Les envois sont illimités pour vous permettre de construire la meilleure relation possible 
						avec vos abonnés. Pas besoin de vous préoccuper du nombre d'emails envoyés afin de ne pas dépasser 
						votre forfait, vous pouvez vous concentrer sur votre stratégie email.
					</p>
				</div>
				<div class="col-lg-5 col-lg-offset-2 col-sm-6">
					<img class="img-responsive" src="img/ipad.png" alt="">
				</div>
			</div>
		</div>
	</div>
	<div class="content-section-b">
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6">
					<hr class="section-heading-spacer">
					<div class="clearfix"></div>
					<h2 class="section-heading">Prix attractif</h2>
					<p class="lead">
						L'emailing étant un des premiers leviers de fidélisation, nous souhaitons le rendre accessible 
						au plus grand nombre. C'est pourquoi nos prix sont accessibles à n'importe quel type de société 
						avec des envois illimités et avec des packs mensuels sans engagement.
					</p>
				</div>
				<div class="col-lg-5 col-sm-pull-6  col-sm-6">
					<img class="img-responsive" src="img/doge.png" alt="">
				</div>
			</div>
		</div>
	</div>
	<div class="content-section-a">
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-sm-6">
					<hr class="section-heading-spacer">
					<div class="clearfix"></div>
					<h2 class="section-heading">Support Client</h2>
					<p class="lead">
						SimpleMail est présent à vos côtés pour l'utilisation de la plateforme. 
						Vous disposez d'un accès illimité et gratuit à une équipe dédiée au support client et à notre 
						blog pour vous aider et vous accompagner.
					</p>
				</div>
				<div class="col-lg-5 col-lg-offset-2 col-sm-6">
					<img class="img-responsive" src="img/phones.png" alt="">
				</div>
			</div>
		</div>
    </div>
	<div class="banner">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<h2>Rejoignez nous !</h2>
				</div>
				<div class="col-lg-6">
					<ul class="list-inline banner-social-buttons">
						<li>
							<a href="https://twitter.com/SBootstrap" class="btn btn-default btn-lg">
								<i class="fa fa-twitter fa-fw"></i> 
								<span class="network-name">Twitter</span>
							</a>
						</li>
						<li>
							<a href="#" class="btn btn-default btn-lg">
								<i class="fa fa-facebook fa-fw"></i> 
								<span class="network-name">Facebook</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<script src="http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>
	<script src="js/morris/chart-data-morris.js"></script>
	<script src="js/tablesorter/jquery.tablesorter.js"></script>
	<script src="js/tablesorter/tables.js"></script>
</body>
</html>