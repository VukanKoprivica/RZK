<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>\
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pretrazi</title>
</head>
<body>
	<form action="/BlogWEB/BlogoviServlet" method="post" > 
		<input type="text" name="pretraga" id="pretraga"> 
		<input type="submit" value="Submit">
	
	
		<c:if test="${!empty blogovi}">
			<table border="1">
				<tr>
					<th>Tekst:</th>
					<th>Datum:</th>
					<th>Like:</th>
					<th>Autor:</th>
				</tr>
				
					<c:forEach items="${blogovi}" var="blog">
						<tr>
							<td>${blog.text}</td>
							<td>${blog.datum}</td>
							<td>${blog.likeNo}</td>
							<td>${blog.blogKorisnik.ime}</td>
						</tr>
					</c:forEach>
			</table>
		</c:if>
	</form>
</body>
</html>