<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Declaration - test</h3>
<%!
	String makeItUpper(String data){
	
	return data.toUpperCase();
}
%>


Upper Case letter : <%= makeItUpper("Hello World") %>
</body>
</html>