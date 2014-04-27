<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>eTargeting - Connection</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body class="connexion">
<div class="post background">
    <h1 class="title">Connexion</h1>
    <p class="infos">Se connecter : </p>
    <form action="Login" method="post">
        <input class="form-control" type="text" name="user_login" id="user_login" placeholder="Login"> 

        <input class="form-control" type="password" name="user_password" id="user_password" placeholder="Votre mot de passe">

        <input class="align" type="checkbox" name="remember" id="remember" value="1">
        <label for="remember" class="align title">Se souvenir de moi</label>

        <input class="button" type="submit" name="connexion" value="Connexion">
    </form>
</div>
</body>
</html>