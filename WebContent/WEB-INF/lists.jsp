<%@ page import="eTargeting.model.ListsModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Listes</title>
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
</head>
<body class="lists">
    <div id="wrapper">
     	<jsp:include page="/WEB-INF/menu.jsp" />
		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1>Listes</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<a href="AddList">Ajouter une liste</a>
				</div>
			</div>


			<!-- Lists' table -->
			<%
			if (request.getAttribute("list-" + 0) != null) {
			%>
				<div class="row">
					<div class="col-lg-12">
						<div class="table-responsive">
							<table id="subscribers-list" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="hidden">#</th>
										<th>Nom</th>
										<th>Editer</th>
										<th>Supprimer</th>
									</tr>
								</thead>
								<tbody class="lists-list">
									<%
									int i = 0;
									while(request.getAttribute("list-" + i) != null) {
										ListsModel list = (ListsModel)request.getAttribute("list-" + i);
										out.println("<tr>");
											out.println("<td class=\"hidden table_list_id\">" + list.getId() + "</td>");
											out.println("<td class=\"col-md-10\">" + list.getName() + "</td>");
											out.println("<td class=\"col-md-1\"><p><a href=\"EditList?id=" + list.getId() + "\" class=\"btn btn-primary btn-xs center-block update-subscriber\" data-title=\"Edit\" data-target=\"#edit\" data-placement=\"top\" rel=\"tooltip\"><span class=\"glyphicon glyphicon-pencil\"></span></a></p></td>");
											out.println("<td class=\"col-md-1\"><p><button class=\"btn btn-danger btn-xs center-block delete-list\" data-title=\"Delete\" data-target=\"#delete\" data-placement=\"top\"><span class=\"glyphicon glyphicon-trash\"></span></button></p></td>");
										out.println("</tr>");
										i++;
									}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			<% } %>
		
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
						<% for (int i = 1; i <= numberOfPages; i++) { %>
							<%
							String isActive = (currentPage == i) ? "active" : "" ;
							%>
							<li class="<% out.print(isActive); %>">
								<a href="Lists?page=<% out.print(i); %>"><% out.print(i); %></a>
							</li>
						<% } %>
						<li class="<% out.print((currentPage == numberOfPages) ? "disabled" : ""); %>">
							<a href="<% out.print(request.getAttribute("nextPage")); %>">&raquo;</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- Delete subscriber's form -->
		<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="delete" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title custom_align" id="Heading">Supprimer la liste</h4>
					</div>
					<div class="modal-body">
						<div class="alert alert-warning">
							<span class="glyphicon glyphicon-warning-sign"></span> Etes-vous sûr de vouloir supprimer cette liste?
						</div>
					</div>
					<div class="modal-footer ">
						<button type="button" id="confirm-deletion" class="btn btn-warning" ><span class="glyphicon glyphicon-ok-sign"></span> Oui</button>
						<button type="button" class="btn btn-warning" data-dismiss="modal" ><span class="glyphicon glyphicon-remove"></span> Non</button>
					</div>
				</div> 
			</div> 
		</div>
	</div>
	
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/tablesorter/jquery.tablesorter.js"></script>
	<script src="js/tablesorter/tables.js"></script>
	<script src="js/main.js"></script>
</body>
</html>