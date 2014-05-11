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
	<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
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
						<div class="control-group">
							<label class="control-label" for="name">Nom</label>
							<div class="controls">
								<input id="name" name="name" type="text" class="input-xlarge" value="<% out.print(list.getName()); %>">
							</div>
						</div>
					</div>
				</div>
				
				<!-- Subscriber's list -->
				<%
				ArrayList<Integer> idsList = (ArrayList<Integer>)request.getAttribute("listSubscribers");
				%>
				<div class="row">
					<div class="col-md-12">
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
											<td class="col-md-1"><input type="text" name="age" class="input-styleless" value="<% out.print(subscriber.getAge()); %>" disabled="disabled" /></td>
											<td class="col-md-2"><input type="text" name="email" class="input-styleless" value="<% out.print(subscriber.getGender()); %>" disabled="disabled" /></td>
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
				<!-- Pagination -->
				<div class="row">
					<div class="col-lg-12">
						<%
						int currentPage   = Integer.parseInt(request.getAttribute("currentPage").toString());
						int numberOfPages = Integer.parseInt(request.getAttribute("numberOfPages").toString());
						%>
						<ul class="pagination">
							<li class="<% out.print((currentPage == 1) ? "disabled" : ""); %>">
								<a href="<% out.print(request.getAttribute("prevPage")); %>">&laquo;</a>
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
								<a href="<% out.print(request.getAttribute("nextPage")); %>">&raquo;</a>
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