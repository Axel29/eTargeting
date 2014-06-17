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
					<h1><% out.print(request.getAttribute("name")); %></h1>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<label for="campaign_name">Nom de la campagne:</label>
					<input type="texte" id="campaign_name" name="campaign_name" class="form-control" disabled="disabled" value="<% out.print(request.getAttribute("name")); %>" />
					
					<br />
					
					<label for="from_name">Nom de l'expéditeur:</label>
					<input type="text" id="from_name" name="from_name" class="form-control" disabled="disabled" value="<% out.print(request.getAttribute("from_name")); %>" />
					
					<br />
					
					<label for="from_email">Email de l'expéditeur:</label>
					<input type="text" id="from_email" name="from_email" class="form-control" disabled="disabled" value="<% out.print(request.getAttribute("from_email")); %>" />
					
					<br />
					
					<label for="sent_on" for="sent_on">Envoyée le:</label>
					<input type="text" id="sent_on" name="sent_on" class="form-control" disabled="disabled" value="<% out.print(request.getAttribute("sent_on")); %>" />
					
					<br />
					
					<label for="email_subject">Sujet de l'email:</label>
					<input type="text" id="email_subject" name="email_subject" class="form-control" disabled="disabled" value="<% out.print(request.getAttribute("subject")); %>">
					
					<br />
					
					<label for="list">Liste:</label>
					<input type="text" id="list" name="list" class="form-control" disabled="disabled" value="<% out.print(request.getAttribute("list")); %>">
						
					<br />
					
					<label for="email_content">Contenu de l'email</label>
					<textarea rows="50" class="form-control" name="email_content">
						<% out.print(request.getAttribute("content")); %>
					</textarea>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/tablesorter/jquery.tablesorter.js"></script>
	<script type="text/javascript" src="js/tablesorter/tables.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript">
	tinymce.init({
	    selector: "textarea",
	    readonly: 1,
	    language : 'fr_FR',
	    plugins: "code,textcolor,spellchecker",
	    toolbar: "undo redo | styleselect | bold italic underline strikethrough | link image | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | spellchecker | code",
	    //toolbar: "forecolor backcolor",
	 });
	</script>
</body>
</html>