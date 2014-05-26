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
					<h1>Campagnes</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<a href="CreateCampaign">Créer une campagne</a>
				</div>
			</div>

			<!-- Campaigns' table -->
			<%
			if (request.getAttribute("campaign-0") != null) {
			%>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Campagnes</h3>
								<div class="pull-right">
									<span class="clickable filter" data-toggle="tooltip" title="Afficher le filtre de campagnes" data-container="body">
										<i class="glyphicon glyphicon-filter"></i>
									</span>
								</div>
							</div>
							<div class="panel-body">
								<input type="text" class="form-control" id="campaigns-table-filter" data-action="filter" data-filters="#campaigns-table" placeholder="Filtre campagnes" />
							</div>
							<div class="table-responsive">
								<table id="campaigns-table" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="hidden">#</th>
											<th>Nom</th>
											<th>Revoir</th>
										</tr>
									</thead>
									<tbody class="campaigns-list">
										<%
										int i = 0;
										while(request.getAttribute("campaign-" + i) != null) {
											CampaignsModel campaign = (CampaignsModel)request.getAttribute("campaign-" + i);
											out.println("<tr>");
												out.println("<td class=\"hidden table_campaign_id\">" + campaign.getId() + "</td>");
												out.println("<td class=\"col-md-10\">" + campaign.getName() + "</td>");
												out.println("<td class=\"col-md-1\"><p><a href=\"Campaigns?id=" + campaign.getId() + "\" class=\"btn btn-primary btn-xs center-block see-campaign\" data-title=\"See again\" data-target=\"#see\" data-placement=\"top\" rel=\"tooltip\"><span class=\"glyphicon glyphicon-pencil\"></span></a></p></td>");
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
									<a href="Campaigns?page=<% out.print(i); %>"><% out.print(i); %></a>
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
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/tablesorter/tables.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>