<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Form</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	
</head>
<body>

	<h2 class="text-center">Customer Form</h2>
	<hr />
	<br />



	<div class="container-fluid">

<div class="row">
<div class="col-lg-3">

<form:form action="saveCustomer" modelAttribute="customer" method="post">

<form:hidden path="id"/>
		
			<div class="form-group">
				<label for="firstName">First Name :</label>
				<form:input path="firstName" class="form-control"/> 
			</div>
			<div class="form-group">
				<label for="lastName">Last Name :</label> 
				<form:input path="lastName" class="form-control"/>
				</div>
			<div class="form-group">
				<label for="email">Email :</label> 
				<form:input path="email" class="form-control"/>
			</div>
			<div class="form-group">
				<input type="submit"
					value="Save Customer" class="form-control btn btn-primary">
			</div>
		</form:form>
		
		<br>
		<a href="${pageContext.request.contextPath}/customer/list" >Back to Home</a>
	</div>
	</div>
	</div>

</body>
</html>