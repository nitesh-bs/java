<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>

<h1>Welcome to Home Page</h1> 
<hr>
<form:form action="${pageContext.request.contextPath}/logout" method="post">

<input type="submit" value="Logout" />

</form:form>
</body>
</html>