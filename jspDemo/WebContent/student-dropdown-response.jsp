<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Student DropDown :</h3>${param.firstName}
	${param.lastName} Country : ${param.country}
	<br />
	<br /> Favorite Language Radio: ${param.favLang}
	<br />
	<br /> Favorite Language checkBox
	<br />
	<br />
	<%
	String[] lang = request.getParameterValues("myFavLang");
	if (lang != null) {
		for (String tempLang : lang) {
			out.print("<br/> " + tempLang);
		}
	}
	%>
</body>
</html>