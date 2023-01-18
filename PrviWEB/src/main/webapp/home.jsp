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
	<form action="/PrviWEB/UnosServlet" method="get">
		<c:if test="${!empty tipovi}">
		<select id="tipov" name="tipov">
			<c:forEach items="${tipovi}" var="ti">
				<option value="${ti.id}">${ti.name}</option>
			</c:forEach>
		</select>
		<input type="text" name="desc" id="desc">
		<input type="datetime" name="fromDate" id="fromDate">
		<input type="datetime" name="toDate" id="toDate">
		<input type="submit" value="Unesi">

		</c:if>
	</form>
	
	<form action="/PrviWEB/UnosServlet" method="post">
		<input type="datetime" name="fromDatee" id="fromDatee">
		<input type="submit" value="Unesi">
	</form>
	<c:if test="${!empty eventovi}">
		<c:forEach items="${eventovi}" var="eve">
			${eve.description} ${eve.fromDate} ${eve.user.email}<br>
		</c:forEach>
	</c:if>
	

</body>
</html>