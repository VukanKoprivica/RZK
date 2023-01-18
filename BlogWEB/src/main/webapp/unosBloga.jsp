<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unos Bloga</title>
</head>
<body>
	<form action="/BlogWEB/UnesiServlet" method="post">
		<select name="kategorija">
			<c:forEach items="${kateg}" var="kategorija">
				<option value="${kategorija.id}">${kategorija.naziv}</option>
			</c:forEach>
		</select>
		<input type="text" name="tekst" id="tekst">
		<input type="submit" value="Sacuvaj">
	</form>

</body>
</html>