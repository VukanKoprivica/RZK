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

	<form action="/BlogWEB/LoginServlet" method="post">
	Korisnicko ime:<input type="text" 	name="user" id="user"><br>
	Password:<input type="password" 	name="pass" id="pass"><br>
	<input type="submit" 	value="Login">
	</form>>
</body>
</html>