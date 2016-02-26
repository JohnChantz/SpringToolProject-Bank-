<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>

</style>
<title>Review Request</title>
</head>
<body>
<div class="container">
	<div class="jumbotron">
		<h1>Bank of Greece</h1>
		<p>Customer's request review page...</p> 
	</div>
	<div class="row">
		<div class="col-md-6">
			<h3><strong>Customer Info</strong></h3>
			<table class="table table-hover">
			<tr>
				<th>ID</th>
				<td>${cust.id}</td>
			</tr>
			<tr>
				<th>FirstName</th>
				<td>${cust.fname}</td>
			</tr>
			<tr>
			<tr>
				<th>LastName</th>
				<td>${cust.lname}</td>
			</tr>
			<tr>
				<th>AFM</th>
				<td>${cust.afm}</td>
			</tr>
			<tr>
			<tr>
				<th>ADT</th>
				<td>${cust.adt}</td>
			</tr>
			<tr>
				<th>Telephone</th>
				<td>${cust.tel}</td>
			</tr>
			<tr>
				<th>Payment Account</th>
				<td>${cust.paymentAccount}</td>
			</tr>
			</table>
		</div>
		<div class="col-md-6">
			<h3><strong>Request Info</strong></h3>
			<table class="table table-hover">
			<tr>
				<th>ID</th>
				<td>${req.id}</td>
			</tr>
			<tr>
				<th>Credit Limit</th>
				<td>${req.creditLimit}</td>
			</tr>
			<tr>
			<tr>
				<th>Payment Selection</th>
				<td>${req.paymentSelection}</td>
			</tr>
			<tr>
				<th>Employer Name</th>
				<td>${req.empName}</td>
			</tr>
			<tr>
			<tr>
				<th>Employer Address</th>
				<td>${req.empAddress}</td>
			</tr>
			<tr>
				<th>Salary</th>
				<td>${req.salary}</td>
			</tr>
			<tr>
				<th>Annual Salary</th>
				<td>${req.annualSalary}</td>
			</tr>
			<tr>
			<tr>
				<th>Rate</th>
				<td>${req.rate}</td>
			</tr>
			<tr>
				<th>Customer ID</th>
				<td>${req.customerId}</td>
			</tr>
			<tr>
				<th>Request Status</th>
				<td>${req.requestStatus}</td>
			</tr>
			<tr>
				<th>Description</th>
				<td>${req.description}</td>
			</tr>
			</table>
		</div>
	<div class="row">
		<div class="col-md-6">
			${message}
		</div>
		<div class="col-md-6">
			<a style="float:right;" href="/client/userPage" class="btn btn-success" role="button">Return</a>
		</div>
	</div>
	</div>
</div>
</body>
</html>