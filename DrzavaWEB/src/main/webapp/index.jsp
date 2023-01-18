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
	<form action="/DrzavaWEB/DrzavaServlet" method="get">
		<input type="text" id="code" name="code">
		<input type="submit" value="ajde">
	</form>

	<c:if test="${!empty response}">
	<h1>${response}</h1>
	</c:if>
</body>
</html>