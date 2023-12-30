<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Form</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="js/student-validation.js"></script>
</head>
<body>

	<h2 class="text-center">Student Table</h2>
	<hr />
	<br />



	<div class="container-fluid">

<div class="row">
<div class="col-lg-3">

		<form action="StudentController" name="studentForm" method="post" onSubmit="return validateForm()">

			<input type="hidden" name="command" value="add">
			<div class="form-group">
				<label for="firstName">First Name :</label> 
				<input type="text"
					id="firstName" name="firstName" class="form-control">
			</div>
			<div class="form-group">
				<label for="lastName">Last Name :</label> 
				<input type="text"
					id="lastName" name="lastName" class="form-control">
			</div>
			<div class="form-group">
				<label for="email">Email :</label> <input type="email"
					id="email" name="email" class="form-control">
			</div>
			<div class="form-group">
				<input type="submit"
					value="Add Student" class="form-control btn btn-primary">
			</div>
		</form>
		
		<br>
		<a href="StudentController" >Back to Home</a>
	</div>
	</div>
	</div>

</body>
</html>