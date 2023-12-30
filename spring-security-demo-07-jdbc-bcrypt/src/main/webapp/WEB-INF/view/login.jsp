<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom Login</title>
<style>
.failed{
color:red;
}

</style>
	 <link rel="stylesheet" type="text/css" href="css/demo.css">

</head>
<body>
	<h2>Custom Login</h2>

	<form:form action="${pageContext.request.contextPath}/authenticateUser"
		method="post">
		<c:if test="${param.error != null }">
			<h3 class="failed"> Invalid Username or Password!!!</h3>
		</c:if>
		<p>
			User name : <input type="text" name="username">
		</p>

		<p>
			Password : <input type="password" name="password">
		</p>

		<input type="submit" value="Login">
	</form:form>

</body>
</html>
