<%@ page import="eTargeting.model.ListsModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>eTargeting - Envoyer un email</title>
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css">
</head>
<body class="campaign">
    <div id="wrapper">
     	<jsp:include page="/WEB-INF/menu.jsp" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1>Campagne</h1>
				</div>
			</div>

			<form action="CreateCampaign" method="POST" id="campaign-form" name="campaign-form" class="campaign-form form-horizontal" role="form">	
				<div class="row">
					<div class="col-lg-12">
						<label class="sr-only" for="campaign_name">Nom de la campagne</label>
						<input type="text" id="campaign_name" name="campaign_name" class="form-control" placeholder="Nom de la campagne" required="required">
						
						<br />
						
						<label class="sr-only" for="from_name">Nom de l'expéditeur</label>
						<input type="text" id="from_name" name="from_name" class="form-control" placeholder="Nom de l'expéditeur" required="required">
						
						<br />
						
						<label class="sr-only" for="from_email">Email de l'expéditeur</label>
						<input type="text" id="from_email" name="from_email" class="form-control input-xsmall" placeholder="Email de l'expéditeur" required="required">
						
						<br />
						
						<label class="sr-only" for="email_subject">Sujet de l'email</label>
						<input type="text" id="email_subject" name="email_subject" class="form-control" placeholder="Sujet de l'email"required="required">
						
						<br />
						
						<label class="sr-only" for="list">Liste</label>
						<select id="list" name="list" class="form-control" required="required">
							
							<%
							int i = 0;
							while(request.getAttribute("list-" + i) != null) {
								ListsModel list = (ListsModel)request.getAttribute("list-" + i);
								out.print("<option value=\"" + list.getId() + "\">" + list.getName() + "</option>");
								i++;
							}
							%>
						</select>
							
						<br />
						
						<label class="sr-only" for="email_content">Contenu de l'email</label>
						<textarea rows="50" class="form-control" name="email_content" required="required">
						</textarea>
						
						<br />
						
						<button type="submit" id="send" name="send" class="btn btn-primary">Envoyer</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
	<script type="text/javascript">
	tinymce.init({
	    selector: "textarea",
	    language : 'fr_FR',
	    plugins: "code,textcolor,spellchecker",
	    toolbar: "undo redo | styleselect | bold italic underline strikethrough | link image | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | spellchecker | code",
	    //toolbar: "forecolor backcolor",
	 });
	</script>
</body>
</html>