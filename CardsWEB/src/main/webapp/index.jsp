<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="/CardsWEB/CardsServlet" method="post">
		<input type="text" name="to" id ="to">
		<input type="text" name="from" id ="from">
		<input type="text" name="email" id ="email">
		<input type="submit" value="Posalji">
	</form>

</body>
</html>