<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.nitesh.springdemo.util.SortUtils"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
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



	<div class="container-fluid">

		<h3>List of Customers</h3>
		<input type="button" value="Add Customer" class="btn btn-primary"
			onclick="window.location.href='customerAdd';return false;"> <br />
		<br />
		<form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />

			<input type="submit" value="Search" class="btn btn-primary" />
		</form:form>
		<br /> <br />


		<c:url var="sortLinkFirstName" value="/customer/list">
			<c:param name="sort"
				value="<%=Integer.toString(SortUtils.FIRST_NAME)%>" />
		</c:url>

		<c:url var="sortLinkLastName" value="/customer/list">
			<c:param name="sort"
				value="<%=Integer.toString(SortUtils.LAST_NAME)%>" />
		</c:url>

		<c:url var="sortLinkEmail" value="/customer/list">
			<c:param name="sort" value="<%=Integer.toString(SortUtils.EMAIL)%>" />
		</c:url>

		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					
					<th><a href="${sortLinkFirstName}">First Name</a></th>
					<th><a href="${sortLinkLastName}">Last Name</a></th>
					<th><a href="${sortLinkEmail}">Email</a></th>
					<th>Action</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="tempCust" items="${customers}">

					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCust.id}"></c:param>
					</c:url>

					<c:url var="deleteLink" value="/customer/deleteCustomer">
						<c:param name="customerId" value="${tempCust.id}"></c:param>
					</c:url>
					<tr>
						<td>${tempCust.firstName}</td>
						<td>${tempCust.lastName}</td>
						<td>${tempCust.email}</td>
						<td><a href="${updateLink}" class="btn btn-primary">Update</a>
							<a href="${deleteLink}"
							onclick="if( !(confirm('Are you sure want to delete this customer?'))) return false"
							class="btn btn-primary">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>


</body>
</html>