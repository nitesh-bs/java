<%@page import="java.util.ArrayList"%>
<%@page import="com.nitesh.tagDemo.*"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
List<Student> datas = new ArrayList();
datas.add(new Student("Nitesh", "Shekhada", true));
datas.add(new Student("Raj", "Patel", false));
datas.add(new Student("Tarun", "Shah", true));

pageContext.setAttribute("myStud", datas);

%>

<c:set var="str" value="Nitesh K Shekhada" />

<body>
	<c:set var="str1" value="This is first string" />
	<c:set var="str2" value="Hello" />
	<c:set  var="sn" value="${fn:split(str,' ')}" />
	Length of the String-1 is: ${fn:toUpperCase(str)}
	<br> Length of the String-2 is: ${fn:length(str2)}
	<br> Length of the String-3 is: ${fn:toLowerCase(str)}
	<br> Length of the String- is: 
	 <c:forEach var="temps" items="${sn}">
	 ${temps} <br>
	 </c:forEach>
	<br />
	<br />
	<table border="1">
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Joined</th>
		</tr>

		<c:forEach var="temp" items="${myStud}">
			<tr>
				<td>${temp.firstName}</td>
				<td>${temp.lastName}</td>
				<td><c:choose>
						<c:when test="${temp.joinCompany}">
					Special
				</c:when>

						<c:otherwise>
					---
				</c:otherwise>
					</c:choose></td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>