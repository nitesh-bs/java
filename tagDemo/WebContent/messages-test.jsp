<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<c:set  var="myLocale" value="${not empty param.myLocale ? param.myLocale : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${myLocale}"/>

<fmt:setBundle basename="com.nitesh.tagDemo.i182.resource.mylabels"/>

<body>

<a href="messages-test.jsp?myLocale=en_US">English (US)</a>
|
<a href="messages-test.jsp?myLocale=es_ES">Spanish (ES)</a>
|
<a href="messages-test.jsp?myLocale=de_DE">German (DE)</a>
|
<hr />
<fmt:message  key="label.fristName" /> <br/><br/>

<fmt:message  key="label.lastName" /> <br/><br/>

<fmt:message  key="label.welcome" /> <br/><br/>

<hr/>
Selected Locale : ${myLocale}


</body>
</html>