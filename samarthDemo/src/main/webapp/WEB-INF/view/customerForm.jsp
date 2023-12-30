<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Form</title>
<style type="text/css">
.error {
color: red;
font-weight: bold;
}
</style>
</head>
<body>

<form:form action="processForm" modelAttribute="customer">
	First Name  : <form:input path="firstName"/>
	<br><br>
	Last Name : <form:input path="lastName"/>
	<form:errors path="lastName" cssClass="error" />
	<br><br>
	Free Pass : <form:input path="freePass"/>
	<form:errors path="freePass" cssClass="error"/>
	<br><br>
	Post Code : <form:input path="postCode"/>
	<form:errors path="postCode" cssClass="error"/>
	<br><br>
	Course Code : <form:input path="courceCode"/>
	<form:errors path="courceCode" cssClass="error"/>
	<br><br>
	<input type="submit" />
</form:form>
</body>
</html>