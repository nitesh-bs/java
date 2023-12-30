<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
<p>Welcome User <security:authentication property="principal.username"/>
<br><br>

Role is : <security:authentication property="principal.authorities"/>
</p>

<hr>

<security:authorize access="hasRole('Manager')">

<a href="${pageContext.request.contextPath}/leaders">Leader Manager</a>

</security:authorize>

<security:authorize access="hasRole('Admin')">
<a href="${pageContext.request.contextPath}/system">Leader Admin</a>
</security:authorize>
<hr>



<form:form action="${pageContext.request.contextPath}/logout" method="post">


<input type="submit" value="Logout" />

</form:form>
</body>
</html>