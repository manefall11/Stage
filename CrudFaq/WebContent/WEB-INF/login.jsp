<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="inc/entete.jsp"/>
<p>Bienvenue sur la page de l'administrateur </p>
<p>Veillez vous connecter</p>
	<form method="post">
		<fieldset>
			<legend>Connexion</legend>
			<label>Login</label>
			<input type="text" name="login">
			<label>Password</label>
			<input type="password" name="password">
			<input type="submit" value="Se connecter">
		</fieldset>
	</form>
</body>
</html>