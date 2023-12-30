<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Confirmation</title>
</head>
<body>

<h2>Student Confirmation</h2>
<br><hr>

Student First Name : ${student.firstName} ${student.lastName} ${student.country}

<br>
Favorite LAnguage : ${student.favLang}
<br>
Operating System : 
<ul>
<c:forEach var="temp" items="${student.operatingSystem}">
<li> ${temp} </li>
</c:forEach>
</ul>
</body>
</html>