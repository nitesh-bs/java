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

	<h2 class="text-center">Update Student</h2>
	<hr />
	<br />



	<div class="container-fluid">

		<div class="row">
			<div class="col-lg-3">
				

					<form action="StudentController" name="studentForm" onSubmit="return validateForm()" method="get">

						<input type="hidden" name="command" value="update">
						<input type="hidden" name="studId" value="${the_student.studId}">
						<div class="form-group">
							<label for="firstName">First Name :</label> <input type="text"
								id="firstName" value="${the_student.firstName}" name="firstName"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="lastName">Last Name :</label> <input type="text"
								id="lastName" name="lastName" value="${the_student.lastName}"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="email">Email :</label> <input type="email" id="email"
								name="email" value="${the_student.email}" class="form-control">
						</div>
						<div class="form-group">
							<input type="submit" value="Update Student"
								class="form-control btn btn-primary">
						</div>
					</form>
				<br> <a href="StudentController">Back to Home</a>
			</div>
		</div>
	</div>

</body>
</html>