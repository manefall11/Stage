
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import ="dao.FaqDao" %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:import url="inc/entete.jsp"/>
	<c:import url="inc/menu.jsp"/>
	<form method="post" action="update">
		<fieldset>
			<legend>Modification d'une question</legend>
			<input name="idqa" type="text" hidden="true" value="${ requestScope.faqUpdate.idqa }"/>
			<label>Question :</label>
			<input type="text" name="question" value="${ requestScope.faqUpdate.question }">
			<span>${ messageErreurs.question }</span><br>
			<label>Reponse :</label>
			<input type="text" name="reponse" value="${ requestScope.faqUpdate.reponse }">
			<span>${ messageErreurs.reponse }</span><br>
			
			<label>Categorie:</label>

			<select name="category">
			  <c:forEach  var="faq" items="${FaqDao.getCategorie()}">
			  <option value= "${faq.idCategory}" >${faq.libelle}</option>
			  </c:forEach>
			</select>
			<span>${ messageErreurs.uder_id }</span><br>
			
			
			
			<input type="submit" value="Modifier">
			<span class="${ empty messageErreurs ? 'succes' : 'erreur'}">${ statusMessage }</span>
		</fieldset>
	</form>
</body>
</html>
