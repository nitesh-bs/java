<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Scriptlete Test</h3>
<%
for(int i=0;i<=5;i++){
	out.println("<br/>Line number is : "+i);
}
%>
</body>
</html>