<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Form</title>
</head>
<body>

	<form:form action="processForm" modelAttribute="student">

First Name : <form:input path="firstName" />
		<br>
		<br>
Last Name : <form:input path="lastName" />
		<br>
		<br>
Country : <form:select path="country">
			<%-- <form:option value="India" label="India" />
<form:option value="US" label="US" />
<form:option value="Australia" label="Australia" />
<form:option value="Garmany" label="Garmany" /> --%>

<%-- 
Properties Parsing
<form:options items="${theCountryOptions}"/> --%>
			<form:options items="${student.countryOptions}" />
			
		</form:select>

		<br>
		<br>
		Favorite Language : 
		
	<%-- 	Java <form:radiobutton path="favLang" value="Java"/>
		C# <form:radiobutton path="favLang" value="C#"/>
		PHP <form:radiobutton path="favLang" value="PHP"/>
		Ruby <form:radiobutton path="favLang" value="Ruby"/>
	 --%>	
		<form:radiobuttons path="favLang" items="${student.favoriteLanguageOptions}"  />

		
		<br>
		<br>
		
		Operating System :
		Linux <form:checkbox path="operatingSystem" value="Linux"/>
		Mac <form:checkbox path="operatingSystem" value="Mac"/>
		Windows <form:checkbox path="operatingSystem" value="Windows"/>
		
		<br>
		<br>
		
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>