<%@ page import="eTargeting.model.ListsModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Import depuis CSV</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
</head>
<body class="importcsv">
    <div id="wrapper">
     	<jsp:include page="/WEB-INF/menu.jsp" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1>Importer un CSV</h1>
				</div>
			</div>
			<!-- Info alert -->
			<div class="row">
				<div class="col-lg-12">
					<div class="alert alert-info">
						<h4>Information importante</h4>
						<p>
							La liste sera mise à jour automatiquement.
							Les emails déjà présents dans celle-ci ne seront pas impactés, et les nouveaux inscrits ajoutés à votre liste d'abonnés.
							<br />
							Important: le CSV doit être séparé par des virgules (,) et échappés par des guillemets (").
						</p>
					</div>
				</div>
			</div>
			<!-- End of info alert -->
			
			<!-- Error alert -->
			<div class="row">
				<div class="col-lg-12">
					<div class="alert alert-danger hidden fade in">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				    </div>
				</div>
			</div>
			<!-- End of error alert -->
			
			<!-- Error alert -->
			<div class="row">
				<div class="col-lg-12">
					<div class="alert alert-warning hidden fade in">
				    </div>
				</div>
			</div>
			<!-- End of error alert -->
			
			<!-- Success alert -->
			<div class="row">
				<div class="col-lg-12">
					<div class="alert alert-success hidden fade in">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
						<p>
							Les abonnés ont correctement été importés.
						</p>
				    </div>
				</div>
			</div>
			<!-- End of success alert -->
			
			<div class="row">
				<div class="col-lg-12">
					<form action="FileUploader" method="POST" enctype="multipart/form-data" id="uploadCsv" name="uploadCsv" class="form-horizontal" role="form">
		            	<input type="file" name="csv">
		            	<br />
		            	<button type="submit" id="uploadcsv" name="uploadcsv" class="btn btn-primary">Envoyer le CSV</button>
					</form>
				</div>
			</div>
			<br />
			<!-- File example -->
			<div class="row">
				<form action="ImportCsv" method="POST" enctype="multipart/form-data" id="importCsv" name="importCsv" class="form-horizontal" role="form">
					<div class="col-md-12">
						<div class="table-responsive content-examples">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>	
	<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/importcsv.js"></script>
</body>
</html>