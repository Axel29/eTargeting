<%@ page import="eTargeting.model.SubscribersModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Ajouter une liste</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
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
			<form action="AddList" method="POST" id="add-list" name="add-list" class="form-horizontal">
				<div class="row">
					<div class="col-lg-12">
						<fieldset>
							<legend>Ajout d'une liste</legend>
							<div class="control-group">
								<label class="control-label" for="name">Nom</label>
								<div class="controls">
									<input id="name" name="name" type="text" class="input-xlarge" required="required">
								</div>
							</div>
						</fieldset>
					</div>
				</div>
				
				<br />
				
				<!-- Subscriber's list -->
				<div class="row">
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
										<tr class="tr-middle thead-bordered">
											<th data-sorter="false"><input type="checkbox" id="checkall" /></th>
											<th class="hidden">#</th>
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
											%>
											<tr class="tr-middle">
												<td class="col-md-1"><input type="checkbox" name="subscribersChecked[]" value="<% out.print(subscriber.getId()); %>" class="checkthis" /></td>
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
						if (numberOfPages > 1) {
						%>
							<ul class="pagination pull-left">
								<li class="<% out.print((currentPage == 1) ? "disabled" : ""); %>">
									<a href="<% out.print(request.getAttribute("prevPage")); %>">&laquo;</a>
								</li>
								<% for (int j = 1; j <= numberOfPages; j++) { %>
									<%
									String isActive = (currentPage == j) ? "active" : "" ;
									%>
									<li class="<% out.print(isActive); %>">
										<a href="AddList?page=<% out.print(j); %>" class="page-link"><% out.print(j); %></a>
									</li>
								<% } %>
								<li class="<% out.print((currentPage == numberOfPages) ? "disabled" : ""); %>">
									<a href="<% out.print(request.getAttribute("nextPage")); %>">&raquo;</a>
								</li>
							</ul>
						<%
						}
						%>
						<div class="control-group pull-right create-button">
							<label class="control-label" for="add"></label>
							<div class="controls">
								<button id="add" name="add" class="btn btn-primary" type="submit">Créer</button>
							</div>
						</div>
					</div>
				</div>
				<!-- End of pagination -->
			</form>
	    </div>
	</div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tablesorter/tables.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>