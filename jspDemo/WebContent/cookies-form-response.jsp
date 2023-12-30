<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmation</title>
</head>
<body>
<%
	String favLang = request.getParameter("favLang");

	Cookie myCookie = new Cookie("myFavLang",favLang);
	
	myCookie.setMaxAge(60*60*24);
	
	response.addCookie(myCookie);

%>
your favorite language set as : ${param.favLang}

<br/><br/>

<a href="cookies-homepage.jsp">Return to Home Page.</a>
</body>
</html>