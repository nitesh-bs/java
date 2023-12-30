<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student List</title>
</head>
<body>
<h2>Student Table</h2>
<hr/>
<br/>
<table border="1">
<tr>
<th>First Name</th>
<th>Last Name</th>
<th>Email</th>
</tr>


<c:forEach var="tempStud" items="${stud_list}">
<tr>
<td> ${tempStud.firstName}  </td>
<td> ${tempStud.lastName}  </td>
<td> ${tempStud.email}  </td>
</tr>

</c:forEach>

</table>
</body>
</html>