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
<body class="subscribers">
    <div id="wrapper">
     	<jsp:include page="/WEB-INF/menu.jsp" />
		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1>Liste des abonnés</h1>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<form action="Subscribers" method="POST" id="add-subscriber" name="add-subscriber" class="add-subscriber form-inline" role="form">
						<input type="hidden" name="insertSubscriber" value="1" />
						<div class="form-group">
							<label class="sr-only" for="email">Adresse email</label>
							<input type="email" id="email" name="email" class="form-control" placeholder="Adress email">
							<span class="required-entry">*</span>
						</div>
						<div class="form-group">
							<label class="sr-only" for="last_name">Nom</label>
							<input type="text" id="last_name" name="last_name" class="form-control" placeholder="Nom">
						</div>
						<div class="form-group">
							<label class="sr-only" for="first_name">Prénom</label>
							<input type="text" id="first_name" name="first_name" class="form-control" placeholder="Prénom">
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
						<button type="submit" id="add" name="add" class="btn btn-primary">Ajouter</button>
					</form>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="row">
				<div class="col-md-12">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Email</th>
									<th>Prénom</th>
									<th>Nom</th>
									<th>Age</th>
									<th>Sexe</th>
									<th>Editer</th>
									<th>Supprimer</th>
								</tr>
							</thead>
							<tbody class="subscribers-list">
								<%
								int i = 0;
								while(request.getAttribute("subscriber-" + i) != null) {
									SubscribersModel subscriber = (SubscribersModel)request.getAttribute("subscriber-" + i);
									String age = (subscriber.getAge() == 0) ? "N/A" : Integer.toString(subscriber.getAge());
									out.println("<tr>");
										out.println("<td class=\"col-md-1 table_user_id\">" + subscriber.getId() + "</td>");
										out.println("<td class=\"col-md-4 table_user_email\">" + subscriber.getEmail() + "</td>");
										out.println("<td class=\"col-md-2 table_user_first_name\">" + subscriber.getFirstName() + "</td>");
										out.println("<td class=\"col-md-1 table_user_last_name\">" + subscriber.getLastName() + "</td>");
										out.println("<td class=\"col-md-1 table_user_age\">" + age + "</td>");
										out.println("<td class=\"col-md-1 table_user_gender\">" + subscriber.getGender() + "</td>");
										out.println("<td class=\"col-md-1\"><p><button class=\"btn btn-primary btn-xs center-block edit-subscriber\" data-title=\"Edit\" data-target=\"#edit\" data-placement=\"top\" rel=\"tooltip\"><span class=\"glyphicon glyphicon-pencil\"></span></button></p></td>");
										out.println("<td class=\"col-md-1\"><p><button class=\"btn btn-danger btn-xs center-block delete-subscriber\" data-title=\"Delete\" data-target=\"#delete\" data-placement=\"top\" rel=\"tooltip\"><span class=\"glyphicon glyphicon-trash\"></span></button></p></td>");
									out.println("</tr>");
									i++;
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Edit subscriber's form	 -->
	<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
				</div>
				<form action="Subscribers" method="post">
					<div class="modal-body">	
						<div class="form-group">
							<input class="form-control" type="hidden" id="user_id" name="user_id" value=""">
						</div>
						<div class="form-group">
							<label class="sr-only" for="user_email">Email</label>
							<input class="form-control" type="text" id="user_email" name="user_email" placeholder="Email">
						</div>
						<div class="form-group">
							<label class="sr-only" for="user_first_name">Prénom</label>
							<input class="form-control" type="text" id="user_first_name" name="user_first_name" placeholder="Prénom">
						</div>
						<div class="form-group">
							<label class="sr-only" for="user_last_name">Nom</label>
							<input class="form-control" type="text" id="user_last_name" name="user_last_name" placeholder="Nom">
						</div>
						<div class="form-group">
							<label class="sr-only" for="user_last_name">Age</label>
							<input class="form-control" type="text" id="user_age" name="user_age" placeholder="Age">
						</div>
						<div class="form-group">
							<label class="sr-only" for="user_gender">Sexe</label>
							<select id="user_gender" name="user_gender" class="form-control">
								<option value="M">M</option>
								<option value="F">F</option>
							</select>
						</div>
						<div class="modal-footer ">
							<button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span> Mettre à jour</button>
						</div>
					</div>
				</form>
			</div>
		</div> 
	</div>
    
    <!-- Delete subscriber's form -->
	<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
				</div>
				<div class="modal-body">
					<div class="alert alert-warning">
						<span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?
					</div>
				</div>
				<div class="modal-footer ">
					<button type="button" id="confirm-deletion" class="btn btn-warning" ><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
					<button type="button" class="btn btn-warning" data-dismiss="modal" ><span class="glyphicon glyphicon-remove"></span> No</button>
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
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>