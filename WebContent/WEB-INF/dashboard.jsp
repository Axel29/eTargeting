<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Dashboard</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
</head>
<body>
    <div id="wrapper">
     <jsp:include page="/WEB-INF/menu.jsp" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1>Tableau de bord - Statistiques</h1>
					<div class="alert alert-success alert-dismissable">
						<p>Bienvenue dans l'espace d'administration</p>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-bar-chart-o"></i> 
								Newsletter envoyée du 1er Octobre 2013 - 31 Octobre 2013
							</h3>
						</div>
						<div class="panel-body">
							<div id="morris-chart-line"></div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-4">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-long-arrow-right"></i> 
								Ouverture Newsletters du 1er Octobre 2013 au 31 Octobre 2013
							</h3>
						</div>
						<div class="panel-body">
							<div id="morris-chart-donut"></div>
							<div class="text-right">
								<a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title"><i class="fa fa-clock-o"></i> Activité récente</h3>
						</div>
						<div class="panel-body">
							<div class="list-group">
								<a href="#" class="list-group-item">
									<span class="badge">just now</span>
									<i class="fa fa-calendar"></i> Calendar updated
								</a>
								<a href="#" class="list-group-item">
									<span class="badge">4 minutes ago</span>
									<i class="fa fa-comment"></i> Commented on a post
								</a>
								<a href="#" class="list-group-item">
									<span class="badge">23 minutes ago</span>
									<i class="fa fa-truck"></i> Order 392 shipped
								</a>
								<a href="#" class="list-group-item">
									<span class="badge">46 minutes ago</span>
									<i class="fa fa-money"></i> Invoice 653 has been paid
								</a>
								<a href="#" class="list-group-item">
									<span class="badge">1 hour ago</span>
									<i class="fa fa-user"></i> A new user has been added
								</a>
								<a href="#" class="list-group-item">
									<span class="badge">2 hours ago</span>
									<i class="fa fa-check"></i> Completed task: "pick up dry cleaning"
								</a>
								<a href="#" class="list-group-item">
									<span class="badge">yesterday</span>
									<i class="fa fa-globe"></i> Saved the world
								</a>
								<a href="#" class="list-group-item">
									<span class="badge">two days ago</span>
									<i class="fa fa-check"></i> Completed task: "fix error on sales page"
								</a>
							</div>
							<div class="text-right">
								<a href="#">View All Activity <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/raphael-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/morris/morris-0.4.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/morris/chart-data-morris.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tablesorter/tables.js"></script>
</body>
</html>