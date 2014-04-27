<%@ page import="eTargeting.ListClass" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Ajouter une liste</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
</head>
<body>
	<%
	if(session.getAttribute("userId") == null)
	{
		response.sendRedirect("/eTargeting/Login");
	}
	%>
    <div id="wrapper">
     	<%@ include file="menu.jsp"%>
		<div id="page-wrapper">
	
			<div class="row">
				<div class="col-lg-12">
					<h1>Ajout d'une liste</h1>
				</div>
			</div>
	
			<div class="row">
				<div class="col-lg-12">
					<form action="Lists" method="POST" class="form-horizontal">
						<fieldset>
							<legend>Ajout d'une liste</legend>
							
							<div class="control-group">
								<label class="control-label" for="name">Nom</label>
								<div class="controls">
									<input id="name" name="name" type="text" class="input-xlarge">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="add"></label>
								<div class="controls">
									<button id="add" name="add" class="btn btn-primary" type="submit">Créer</button>
								</div>
							</div>
						</fieldset>
					</form>
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