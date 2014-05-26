<%@ page import="eTargeting.model.CampaignsModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Campagnes</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
</head>
<body class="campaigns">
    <div id="wrapper">
     	<jsp:include page="/WEB-INF/menu.jsp" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1><% out.print(request.getAttribute("name")); %></h1>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					Nom: <% out.print(request.getAttribute("name")); %>
					<br />
					Nom de l'expéditeur: <% out.print(request.getAttribute("from_name")); %>
					<br />
					Email de l'expéditeur: <% out.print(request.getAttribute("from_email")); %>
					<br />
					Prévue le: <% out.print(request.getAttribute("scheduled_at")); %>
					<br />
					Envoyée le: <% out.print(request.getAttribute("sent_on")); %>
					<br />
					Liste: <% out.print(request.getAttribute("list")); %>
					<br />
					Sujet: <% out.print(request.getAttribute("subject")); %>
					<br />
					Contenu: <% out.print(request.getAttribute("content")); %>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/tablesorter/tables.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>