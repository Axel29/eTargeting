<%@page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@ page import="eTargeting.model.ListsModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Listes</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
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
					<a href="AddList" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span>  Ajouter une liste</a>
				</div>
			</div>

			<br />

			<!-- Lists' table -->
			<%
			if (request.getAttribute("list-" + 0) != null) {
			%>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Listes</h3>
								<div class="pull-right">
									<span class="clickable filter" data-toggle="tooltip" title="Afficher le filtre de listes" data-container="body">
										<i class="glyphicon glyphicon-filter"></i>
									</span>
								</div>
							</div>
							<div class="panel-body">
								<input type="text" class="form-control" id="lists-table-filter" data-action="filter" data-filters="#lists-table" placeholder="Filtre listes" />
							</div>
							<div class="table-responsive">
								<table id="lists-table" class="table table-striped table-bordered table-hover">
									<thead>
										<tr class="tr-middle thead-bordered">
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
											out.println("<tr class=\"tr-middle\">");
												out.println("<td class=\"hidden table_list_id\">" + list.getId() + "</td>");
												out.println("<td class=\"col-md-10\">" + StringEscapeUtils.unescapeHtml4(list.getName()) + "</td>");
												out.println("<td class=\"col-md-1\"><p><a href=\"EditList?id=" + list.getId() + "\" class=\"btn btn-primary btn-xs center-block update-list href-glyphicon\" data-title=\"Edit\" data-target=\"#edit\" data-placement=\"top\" rel=\"tooltip\"><span class=\"glyphicon glyphicon-pencil\"></span></a></p></td>");
												out.println("<td class=\"col-md-1\"><p><button class=\"btn btn-danger btn-xs center-block delete-list href-glyphicon\" data-title=\"Delete\" data-target=\"#delete\" data-placement=\"top\"><span class=\"glyphicon glyphicon-trash\"></span></button></p></td>");
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
			<% } %>
		
			<!-- Pagination -->
			<div class="row">
				<div class="col-lg-12">
					<%
					int currentPage   = Integer.parseInt(request.getAttribute("currentPage").toString());
					int numberOfPages = Integer.parseInt(request.getAttribute("numberOfPages").toString());
					if (numberOfPages > 1) {
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
					<%
					}
					%>
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
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tablesorter/tables.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>