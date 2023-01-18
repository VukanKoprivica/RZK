<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert new conference</title>
</head>
<body>
	<form action="/ConfInfoWeb/SaveConference" method="post">
		City:<input type="text" name="city"><br><br>
		Country:<input type="text" name="country"><br><br>
		Date from:<input type="date" name="dateFrom"><br><br>
		Date to:<input type="date" name="dateTo"><br><br>
		Field:<input type="text" name="field"><br><br>
		Title:<input type="text" name="title"><br><br>
		<input type="submit" value="Save">
	</form>
	${msg}
</body>
</html>