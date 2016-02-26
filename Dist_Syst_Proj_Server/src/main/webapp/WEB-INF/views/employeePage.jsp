<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
body{background-color: #f2f2f2;font-size:25px;}
input[type=submit]{font-size:20px;}
input[type=text]{width: 350px;padding: 10px;margin: 10px;font-size:25px;}
input[type=text]:focus{backgound-color:lightblue;}
a:hover{color:blue;}
table{padding:5px;width:100%;}
th,td{border-bottom: 1px solid #ddd;padding:5px;text-align:center;}
th{background-color:#00b33c;}
</style>

<title>Employees Page</title>
</head>
<body>
<p style="font-size:40px;"><b>Welcome Back,</b> ${sessionScope.username}</p>
<div>
<p>Create new credit card request for customer</p>
<p>In the form below insert customer's ID or ADT number or AFM number to find customer in the database.</p>
</div>
<div class="form">
	<form action="/dist_syst_proj/checkCustomer" method="POST">
		Customer's ID:
		<input type="text" value="0" name="id"><br>
		Customer's ADT:
		<input type="text" value="" name="adt"><br>
		Customer's AFM:
		<input type="text" value="0" name="afm"><br>
		<input type="submit" class="btn btn-success" value="Search">
	</form>
</div>
<br>
${message1}
<h3>Review requests submitted via the online service</h3>
<p>Choose one of the following request IDs to review the request form submitted by the customer.</p>
<table>
        <tr>
	        <th> Request ID </th>
	        <th> Credit Limit </th>
	        <th> Payment Selection </th>
	        <th> Customer's Salary </th>
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
    <td>${req.rate}</td>
    <td>${req.customerId}</td>
    <td>${req.description}</td>
  </tr>
</c:forEach>
</table>
<div class="form">
	<form action="/dist_syst_proj/displayRequest" method="POST">
		Select Request ID:
		<input type="text" name="id">
		<input type="submit" class="btn btn-success" value="Edit Request">
	</form>
</div>
<br>
${message2}
<a href="/dist_syst_proj/logOut" class="btn btn-success" role="button">Log Out</a>
<br>
</body>
</html>