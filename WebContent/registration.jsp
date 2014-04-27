<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>eTargeting - Inscription</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body class="inscription">
<div class="post background">
    <h1 class="title">Inscription</h1>
    <p class="infos">Inscrivez-vous en 30 secondes sur eTargeting.</p>
    <form action="Registration" method="post">
        <input class="form-control" type="text" id="user_login" name="user_login" placeholder="Adresse e-mail"> 
        <input class="form-control" type="password" id="user_password" name="user_password" placeholder="Votre mot de passe">
        <input class="form-control" type="text" id="lastName" name="lastName" placeholder="Nom"> 
        <input class="form-control" type="text" id="firstName" name="firstName" placeholder="Prénom">
        <input class="button" type="submit" value="Inscription">
    </form>
</div>
</body>
</html>