 <%@ page import="eTargeting.model.UserModel" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="Index">
			<%
				out.println(((UserModel)request.getAttribute("user")).getFirstName() + " " + ((UserModel)request.getAttribute("user")).getLastName());
			%>
		</a>
	</div>
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav side-nav">
			<li class="active"><a href="Dashboard"><i class="fa fa-dashboard"></i> Tableau de bord</a></li>
			<li><a href="Campaigns"><i class="fa fa-desktop"></i> Campagnes</a></li>
			<li><a href="Lists"><span class="fa fa-list"></span> Listes</a></li>
			<li><a href="Subscribers"><span class="fa fa-list"></span> Abonnés</a></li>
			<li><a href="Charts"><i class="fa fa-bar-chart-o"></i> Rapports</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right navbar-user">
			<li class="dropdown user-dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<i class="fa fa-user"></i> 
					<% out.println(((UserModel)request.getAttribute("user")).getEmail()); %>
					<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="fa fa-user"></i>Mon Compte</a></li>
					<li><a href="#"><i class="fa fa-gear"></i>Réglages</a></li>
					<li class="divider"></li>
					<li><a href="Login?logout=1"><i class="fa fa-power-off"></i>Déconnexion</a></li>
				</ul>
			</li>
		</ul>
	</div>
</nav>