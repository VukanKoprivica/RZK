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
	<form action="/ConfWEB/ConfServlet" method="post">
		<input type="text" id = "name" name="name"><br>
		<input type="text" id = "country" name="country"><br>
		<input type="text" id = "city" name="city"><br>
		<input type="date" id = "toDate" name="toDate"><br>
		<input type="date" id = "fromDate" name="fromDate"><br>
		<input type="text" id = "field" name="field"><br>
		<input type="submit" value="Add">
	</form>
	
		<h3>${msg}</h3>
	

</body>
</html>