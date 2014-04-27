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
    <form action="index-dashbord.php" method="post">
        <input class="form-control" type="text" name="user_login" id="user_login" placeholder="Login"> 

        <input class="form-control" type="text" name="user_password" id="user_password" placeholder="Votre mot de passe">

        <input class="form-control" type="text" name="nom" id="nom" placeholder="Nom"> 

        <input class="form-control" type="text" name="prenom" id="prenom" placeholder="Prénom">

        <input class="button" type="submit" value="Inscription">
    </form>
</div>
</body>
</html>