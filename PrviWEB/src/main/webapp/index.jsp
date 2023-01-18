<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Glavna</title>
</head>
<body>
	<form action="/PrviWEB/LoginServlet" method="post">
		<input type="text" id="email" name="email"> 
		<input type="password" id="pass" name="pass"> 
		<input type="submit" value="Submit"> 
	</form>
	<a href="/PrviWEB/RegisterServlet">Registracija</a>
	<c:if test="${registracija}">
	<form action="/PrviWEB/RegisterServlet" method="post">
		<input type="text" id="email" name="email"> 
		<input type="text" id="firstName" name="firstName"> 
		<input type="text" id="lastName" name="lastName"> 
		<input type="password" id="pass" name="pass"> 
		<input type="submit" value="Registruj"> 
	</form>
	
	
	</c:if>
	
</body>
</html>