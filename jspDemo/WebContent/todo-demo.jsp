<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Demo</title>
</head>
<body>
	<br />
	<form action="todo-demo.jsp">
		Add new Item <input type="text" name="myItem"> <input
			type="submit" value="Submit">
	</form>
	<br />
	<hr />
	<br /> Item entered :
	<%
	List<String> items = (List<String>) session.getAttribute("myToDoList");
	if (items == null) {
		items = new ArrayList<String>();
		session.setAttribute("myToDoList", items);
		
	}
	String myItem = request.getParameter("myItem");
	/* if(myItem != null && !myItem.trim().equals("")){ */
	
	boolean isItemNotEmpty = myItem != null && myItem.trim().length() > 0;
	boolean isItemNotDuplicate = myItem != null && !items.contains(myItem.trim());

	if (isItemNotEmpty && isItemNotDuplicate) {
		items.add(myItem.trim());
	}

	/* } */
	%>

	<ol>
		<%
		for (String temp : items) {
			out.println("<li>" + temp + "</li>");

		}
		
		%>

	</ol>
</body>
</html>