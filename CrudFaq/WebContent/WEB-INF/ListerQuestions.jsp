
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
	<c:import url="inc/menu.jsp"/>
	<c:choose>
		<c:when test="${ empty listFaq }">
			<p>La liste des question est vide</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="0">
				<tr>
					<th>Question</th>
					<th>Reponse</th>
					<th>user id</th>
					<th>category id</th>
					<th>id</th>
				</tr>
				<c:forEach items="${ listFaq}" var="faq">
					<tr>
						<td><c:out value="${ faq.question }"/></td>
						<td><c:out value="${ faq.reponse }"/></td>
						<td><c:out value="${ faq.user_id }"/></td>
						<td><c:out value="${ faq.category_id }"/></td>
						<td><c:out value="${ faq.idqa}"/></td>
						<td><a href="<c:url value='/Faq/update?idqa=${faq.idqa }'/>">Modifier</a></td>
						<td><a href="<c:url value='/Faq/delete?idqa=${faq.idqa }'/>">Supprimer</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>
</body>
</html>