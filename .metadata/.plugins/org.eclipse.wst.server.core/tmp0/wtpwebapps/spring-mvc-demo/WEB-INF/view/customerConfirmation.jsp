<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Confirmation</title>
</head>
<body>
<h2>Customer Confirmation is done :</h2>

Customer Name is ${customer.firstName} ${customer.lastName}
<br>
Free Pass : ${customer.freePass}

<br>
Post Code : ${customer.postCode}

<br>
Course Code : ${customer.courseCode}
</body>
</html>