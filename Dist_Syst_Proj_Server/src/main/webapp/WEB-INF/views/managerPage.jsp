<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<style>
	body{font-size:20px;background-color:#f2f2f2;}
	input[type=text]:focus{backgound-color:lightblue;}
	input[type=submit]{cursor:pointer;border-radius:5px;text-align:center;}
	input{font-size:20px;}
	select{font-size:20px;}
	table{width:100%;text-align:center;}
	th,td{border-bottom:1px solid #000000;text-align:center;}
	th{background-color:#00b33c;}
	</style>
	<title>Manager Page</title>
	</head>
	<body>
	<h1>Welcome Back ${sessionScope.username}!</h1>
	<h2> Requests pending for evaluation:</h2>
	<table>
	        <tr>
	        <th> ID </th>
	        <th> Credit Limit </th>
	        <th> Payment Selection </th>
	        <th> Salary </th>
	        <th> Annual Salary </th>
	        <th> Rate </th>
	        <th> Customer ID </th>
	        <th>Description</th>
	        </tr>
	<c:forEach items="${reqList}" var="req">
	  <tr>
	    <td>${req.id}</td>
	    <td>${req.creditLimit}</td>
	    <td>${req.paymentSelection}</td>
	    <td>${req.salary}</td>
	    <td>${req.annualSalary}</td>
	    <td>${req.rate}</td>
	    <td>${req.customerId}</td>
	    <td>${req.description}</td>
	  </tr>
	</c:forEach>
	</table>
	<h2>Change request's credit limit or rate:</h2>
	<form:form method="POST" modelAttribute="request" action="/dist_syst_proj/editRequest">
	Request ID: <form:input path="id" required="true"/><br><br>
	Request Credit Limit: <form:input path="creditLimit"/><br><br>
	Request Rate: <form:select path="rate">
						<form:option value="" label="---Select Rate---"/>
						<form:option value="12%" label="12%"/>
						<form:option value="18%" label="18%"/>
					</form:select><br><br>
	Request Status: <form:select path="requestStatus">
						<form:option value="Pending" label="---Select Status---"/>
						<form:option value="Accepted" label="Accepted"/>
						<form:option value="Rejected" label="Rejected"/>
					</form:select><br><br>
	Description: <form:textarea path="description"/>
	<input type="submit" class="btn btn-success" value="Change">
	</form:form><br>
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">${message}</div>
	</div>
	
	<a href="/dist_syst_proj/logOut" class="btn btn-success" role="button">Log Out</a>
</body>
</html>