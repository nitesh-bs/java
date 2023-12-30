<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
String[] cities = {"Mumbai","Surat","Singapore","Rajkot"};
pageContext.setAttribute("cities", cities);

%>
<body>

<c:set var="dt" value="<%= new Date() %>" />

Today ${dt}
<br/>

<c:forEach var="tempCity" items="${cities}">
${tempCity} <br/>
</c:forEach>

</body>
</html>