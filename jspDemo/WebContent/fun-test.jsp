<%@page import="com.nitesh.jsp.FunUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Fun Test</h3>

Upper case letter test : <%= FunUtils.makeItUpper("Hello World") %>

</body>
</html>