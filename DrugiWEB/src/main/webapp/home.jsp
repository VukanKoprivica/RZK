<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="/DrugiWEB/LoginServlet">dodajOglas</a>

	<c:if test="${!empty oglasi}">
		<form action="/DrugiWEB/DrugiServlet" method="get">
			<select id="ogla" name="ogla">
				<c:forEach items="${oglasi}" var="ogla">
				<option value="${ogla.idOglas}">${ogla.text} ${ogla.brojPregleda} ${ogla.oglasKorisnik.nickname}</option>
				</c:forEach>
			</select>
			<input type="text" name="text" id="text">
			<input type="submit" value="Javi se">		
		</form>
	</c:if>
	<br>
	<br>
	<br>
	<c:if test="${!empty dodaj}">
		<form action="/DrugiWEB/DrugiServlet" method="post">
			<input type="text" name="tekst" id="tekst">
			<input type="submit" value="Dodaj">
		</form>
	</c:if>	
	

</body>
</html>