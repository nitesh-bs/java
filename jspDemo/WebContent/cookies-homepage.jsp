<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookies Home Page</title>
</head>
<body>
<%
String favLang = "Java";
	Cookie[] myCookies = request.getCookies();
	
	if(myCookies != null){
		for(Cookie tempCookie : myCookies){
	if(tempCookie.getName().equals("myFavLang"))
	{
		 // decode cookie data ... handle case of languages with spaces in them
                favLang = URLDecoder.decode(tempCookie.getValue(), "UTF-8");

		/*  favLang = tempCookie.getValue(); */
		break;
	}
		}
	}
%>
<h4>Programing language is <%= favLang %></h4><br/><br/>
<h4>Programing language is <%= favLang %></h4><br/><br/>
<h4>Programing language is <%= favLang %></h4><br/><br/>
<hr>
<a href="cookies-form.html">Personalize this Page</a>
</body>
</html>