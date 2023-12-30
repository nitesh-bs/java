<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student List</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<h2 class="text-center">Student Table</h2>
<hr/>
<br/>



<div class="container-fluid">

<input type="button" value="Add Student" class="btn btn-primary" onclick="window.location.href='student-form.jsp';return false;">
<br/>
<br/>
 <form action="StudentController" method="GET">
        
                <input type="hidden" class="form-control" name="command" value="search" />
            
                Search student: <input type="text" name="searchName" />
                
                <input type="submit" value="Search" class="btn btn-primary"   />
            
            </form>
<br/>
<br/>

<table class="table table-striped table-bordered">
<thead>
<tr>
<th>First Name</th>
<th>Last Name</th>
<th>Email</th>
<th>Action</th>
</tr>
</thead>

<tbody>


<c:forEach var="tempStud" items="${stud_list}">

<c:url value="StudentController" var="tempLink">
<c:param name="command" value="edit" />
<c:param name="studId" value="${tempStud.studId}"></c:param>
</c:url>

<c:url value="StudentController" var="tempDelLink">
<c:param name="command" value="del" />
<c:param name="studId" value="${tempStud.studId}"></c:param>
</c:url>
<tr>
<td> ${tempStud.firstName}  </td>
<td> ${tempStud.lastName}  </td>
<td> ${tempStud.email}  </td>
<td> <a href="${tempLink}" class="btn btn-info"> Update</a>  <a href="${tempDelLink}"  onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false" class="btn btn-danger"> Delete</a>   </td>
</tr>

</c:forEach>
</tbody>
</table>
</div>
</body>
</html>