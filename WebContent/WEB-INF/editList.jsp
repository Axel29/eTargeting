<%@ page import="eTargeting.model.ListsModel" %>
<%@ page import="eTargeting.model.SubscribersModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<% ListsModel list = (ListsModel)request.getAttribute("list"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Editer la liste <% out.print(list.getId()); %></title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
</head>
<body class="editList">
    <div id="wrapper">
     	<jsp:include page="/WEB-INF/menu.jsp" />
		<div id="page-wrapper">
	
			<div class="row">
				<div class="col-lg-12">
					<h1>Editer la liste <% out.print(list.getId()); %></h1>
				</div>
			</div>
	
			<form action="EditList" method="POST" id="edit-list" name="edit-list" class="form-horizontal">
				<input type="hidden" name="id" class="listId" value="<% out.print(list.getId()); %>" />
				<div class="row">
					<div class="col-lg-12">
						<div class="control-group pull-left">
							<label class="control-label" for="name">Nom</label>
							<div class="controls">
								<input id="name" name="name" type="text" class="input-xlarge" value="<% out.print(list.getName()); %>">
							</div>
						</div>
						<div class="pull-right create-button">
							<a href="ImportCsv" class="import-csv-link btn btn-default">Importer depuis CSV</a>
						</div>
					</div>
				</div>
				<div clas="clearfix"></div>				
				<!-- Subscriber's list -->
				<%
				ArrayList<Integer> idsList = (ArrayList<Integer>)request.getAttribute("listSubscribers");
				%>
				<div class="row top20">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Abonnés</h3>
								<div class="pull-right">
									<span class="clickable filter" data-toggle="tooltip" title="Afficher le filtre d'abonnés" data-container="body">
										<i class="glyphicon glyphicon-filter"></i>
									</span>
								</div>
							</div>
							<div class="panel-body">
								<input type="text" class="form-control" id="subscribers-table-filter" data-action="filter" data-filters="#subscribers-list" placeholder="Filtre abonnés" />
							</div>
							<div class="table-responsive">
								<table id="subscribers-list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th data-sorter="false"><input type="checkbox" id="checkall" /></th>
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
											String checked = (idsList.contains(subscriber.getId())) ? "checked=\"checked\"" : "" ;
											%>
											<tr>
												<td class="col-md-1"><input type="checkbox" name="subscribersChecked[]" value="<% out.print(subscriber.getId()); %>" class="checkthis" <% out.print(checked); %> /></td>
												<td class="col-md-4"><input type="text" name="email" class="input-styleless" value="<% out.print(subscriber.getEmail()); %>" disabled="disabled" /></td>
												<td class="col-md-2"><input type="text" name="first_name" class="input-styleless" value="<% out.print(subscriber.getFirstName()); %>" disabled="disabled" /></td>
												<td class="col-md-2"><input type="text" name="last_name" class="input-styleless" value="<% out.print(subscriber.getLastName()); %>" disabled="disabled" /></td>
												<td class="col-md-1"><input type="text" name="age" class="input-styleless" value="<% out.print(age); %>" disabled="disabled" /></td>
												<td class="col-md-2"><input type="text" name="gender" class="input-styleless" value="<% out.print(subscriber.getGender()); %>" disabled="disabled" /></td>
											</tr>
											<%
											i++;
										}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- Pagination -->
				<div class="row">
					<div class="col-lg-12">
						<%
						int currentPage   = Integer.parseInt(request.getAttribute("currentPage").toString());
						int numberOfPages = Integer.parseInt(request.getAttribute("numberOfPages").toString());
						%>
						<ul class="pagination">
							<li class="<% out.print((currentPage == 1) ? "disabled" : ""); %>">
								<a href="<% out.print(request.getAttribute("prevPage")); %>" class="page-link">&laquo;</a>
							</li>
							<% for (int j = 1; j <= numberOfPages; j++) { %>
								<%
								String isActive = (currentPage == j) ? "active" : "" ;
								%>
								<li class="<% out.print(isActive); %>">
									<a href="EditList?id=<% out.print(list.getId()); %>&page=<% out.print(j); %>" class="page-link"><% out.print(j); %></a>
								</li>
							<% } %>
							<li class="<% out.print((currentPage == numberOfPages) ? "disabled" : ""); %>">
								<a href="<% out.print(request.getAttribute("nextPage")); %>" class="page-link">&raquo;</a>
							</li>
						</ul>
					</div>
				</div>
				
				<!-- Send button -->
				<div class="row">
					<div class="col-lg-12">
						<div class="control-group">
							<label class="control-label" for="edit"></label>
							<div class="controls">
								<button id="edit" name="edit" class="btn btn-primary" type="submit">Editer</button>
							</div>
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