<%@ page import="eTargeting.model.SubscribersModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Abonnés</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
</head>
<body>
    <div id="wrapper">
     	<jsp:include page="/WEB-INF/menu.jsp" />
		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1>Liste des abonnés</h1>
				</div>
			</div>

			<form action="AddSubscribers" method="POST" class="form-inline" role="form">
				<div class="form-group">
					<label class="sr-only" for="last_name">Nom</label>
					<input type="text" id="last_name" name="last_name" class="form-control" placeholder="Nom">
				</div>
				<div class="form-group">
					<label class="sr-only" for="first_name">Prénom</label>
					<input type="text" id="first_name" name="first_name" class="form-control" placeholder="Prénom">
				</div>
				<div class="form-group">
					<label class="sr-only" for="email">Adresse email</label>
					<input type="email" id="email" name="email" class="form-control" placeholder="Adress email">
				</div>
				<div class="form-group">
					<label class="sr-only" for="age">Age</label>
					<input type="text" id="age" name="age" class="form-control input-xsmall" placeholder="Age">
				</div>
				<div class="form-group">
					<label class="sr-only" for="gender">Sexe</label>
					<select id="gender" name="gender" class="form-control">
						<option value="M">M</option>
						<option value="F">F</option>
					</select>
				</div>
				<button id="add" name="add" class="btn btn-primary" type="submit">Ajouter</button>
			</form>
			
			<div class="row">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>#</th>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Email</th>
							<th>Age</th>
							<th>Sexe</th>
						</tr>
					</thead>
					<tbody>
						<%
						int i = 0;
						while(request.getAttribute("subscriber-" + i) != null) {
							SubscribersModel subscriber = (SubscribersModel)request.getAttribute("subscriber-" + i);
							out.println("<tr>");
								out.println("<td>" + subscriber.getId() + "</td>");
								out.println("<td>" + subscriber.getFirstName() + "</td>");
								out.println("<td>" + subscriber.getLastName() + "</td>");
								out.println("<td>" + subscriber.getEmail() + "</td>");
								out.println("<td>" + subscriber.getAge() + "</td>");
								out.println("<td>" + subscriber.getGender() + "</td>");
							out.println("</tr>");
							i++;
						}
						%>
					</tbody>
				</table>
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