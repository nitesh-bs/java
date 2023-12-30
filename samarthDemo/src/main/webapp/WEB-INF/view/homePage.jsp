<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
<h2>Spring MVC Demo - Home Page</h2>
<br/><hr/>

<a href="hello/showForm">Hello World Form</a>

<img src="${pageContext.request.contextPath}/resources/images/images.jpeg">
<br/><br/>
<a href="student/showForm">Student Form</a>
<br/><br/>
<a href="customer/showForm">Customer Form</a>
</body>
</html>