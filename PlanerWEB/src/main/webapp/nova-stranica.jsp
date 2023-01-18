<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Uspesno!</h3>

	<form action="/PlanerWEB/PlanerServlet" method="get">
	Datum od<input type="date" name="datum1"><br>
	Datum do:<input type="date" name="datum2"><br>
	<select name="tipovi">
	<c:forEach items="${listCat}" var="category">
		<option value="${category.id}">${category.name}</option>>
	</c:forEach>
	</select>
	<input type="submit" value="sacuvaj"><br>
	
	<>
	
	
</form>

</body>
</html>