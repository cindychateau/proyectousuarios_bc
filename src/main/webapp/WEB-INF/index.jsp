<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Boot</title>
</head>
<body>
	<h1>¡Bienvenidos a Spring con JSP! Bienvenid@ <c:out value="${nombre}" /></h1>
</body>
</html>