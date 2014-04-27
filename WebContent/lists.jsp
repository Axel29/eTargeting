<%@page import="eTargeting.ListClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>eTargeting - Listes</title>
<!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Add custom CSS here -->
    <link href="css/sb-admin.css" rel="stylesheet">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <!-- Page Specific CSS -->
    <link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
</head>
<body>
    <div id="wrapper">

     	<%@ include file="/menu.jsp"%>

		<div id="page-wrapper">
	
			<div class="row">
				<div class="col-lg-12">
					<h1>Listes</h1>
				</div>
			</div>
	
			<div class="row">
				<div class="col-lg-12">
					<a href="addList.jsp">Ajouter une liste</a>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<%
					int i = 0;
					while(request.getAttribute("list-" + i) != null) {
						ListClass list = (ListClass)request.getAttribute("list-" + i);
						out.println(list.getName());
						i++;
					}
					%>
				</div>
			</div>
		</div>
	</div>

    <!-- JavaScript -->
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>

    <!-- Page Specific Plugins -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>
    <script src="js/morris/chart-data-morris.js"></script>
    <script src="js/tablesorter/jquery.tablesorter.js"></script>
    <script src="js/tablesorter/tables.js"></script>

  </body>
</html>