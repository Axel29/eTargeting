<%@ page import="eTargeting.model.SubscribersModel" %>
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
<body class="addList">
    <div id="wrapper">
     	<jsp:include page="/WEB-INF/menu.jsp" />
		<div id="page-wrapper">
	
			<div class="row">
				<div class="col-lg-12">
					<h1>Ajout d'une liste</h1>
				</div>
			</div>
	
			<form action="AddList" method="POST" class="form-horizontal">
				<div class="row">
					<div class="col-lg-12">
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
					</div>
				</div>
				
				<!-- Subscriber's list -->
				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table id="subscribers-list" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th data-sorter="false"><input type="checkbox" id="checkall" /></th>
										<th>#</th>
										<th>Email</th>
										<th>Prénom</th>
										<th>Nom</th>
										<th>Age</th>
										<th>Sexe</th>
									</tr>
								</thead>
								<tbody class="subscribers-list">
									<%
									int i = 0;
									while(request.getAttribute("subscriber-" + i) != null) {
										SubscribersModel subscriber = (SubscribersModel)request.getAttribute("subscriber-" + i);
										String age = (subscriber.getAge() == 0) ? "N/A" : Integer.toString(subscriber.getAge());
										out.println("<tr>");
											out.println("<td class=\"col-md-1\"><input type=\"checkbox\" name=\"subscribersChecked[]\" value=\"" + subscriber.getId() + "\" class=\"checkthis\" /></td>");
											out.println("<td class=\"col-md-1 table_user_id\">" + subscriber.getId() + "</td>");
											out.println("<td class=\"col-md-4 table_user_email\">" + subscriber.getEmail() + "</td>");
											out.println("<td class=\"col-md-2 table_user_first_name\">" + subscriber.getFirstName() + "</td>");
											out.println("<td class=\"col-md-2 table_user_last_name\">" + subscriber.getLastName() + "</td>");
											out.println("<td class=\"col-md-1 table_user_age\">" + age + "</td>");
											out.println("<td class=\"col-md-1 table_user_gender\">" + subscriber.getGender() + "</td>");
										out.println("</tr>");
										i++;
									}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</form>
	    </div>
	</div>

	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/tablesorter/jquery.tablesorter.js"></script>
	<script src="js/tablesorter/tables.js"></script>
	<script src="js/main.js"></script>
</body>
</html>