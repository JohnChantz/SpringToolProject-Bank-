<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
	input[type=text]:focus{border: 3px solid #33ff33;}
	input[type=submit]{background-color: #4CAF50;cursor:pointer;border-radius:5px;text-align:center;}
	input{font-size:20px;}
	form{font-size:20px;}
	body{background-color:#adebad;font-size:30px;}
	table.customerInfo{font-size:20px;text-align:left;}
	th,td{border-bottom:1px solid #ddd;}
	tr:hover{background-color:#f5f5f5;}
	button{font-size:20px;background-color:#4CAF50;}
</style>
<script>
function myFoo(){
	var mField = document.getElementById("salaryField");
	var salary = mField.value;
	document.getElementById("salary").value= salary;
	document.getElementById("annualSalary").value= (salary * 12);
	var creditLimit;
	if(salary<600)
		creditLimit=0;
	else if(salary>=600 && salary<1500)
		creditLimit=1000
	else if(salary>=1500 && salary<3000)
		creditLimit=3000;
	else if(salary>=3000 && salary<5000)
		creditLimit=10000;
	else if(salary>=5000)
		creditLimit=10000;
	document.getElementById("creditLimit").value=creditLimit;
}
</script>
	<title>Review Request</title>
</head>
<body>
<table class="customerInfo">
	<tr><th>Customer's Information:</th></tr>
	<tr><th>CustomerID:</th> <td>${customer.id}</td></tr>
	<tr><th>First Name: </th> <td> ${customer.fname}</td></tr>
	<tr><th>Last Name: </th> <td> ${customer.lname}</td></tr>
	<tr><th>Customer's AFM: </th> <td> ${customer.afm}</td></tr>
	<tr><th>Customer's ADT: </th> <td> ${customer.adt}</td></tr>
	<tr><th>Customer's TEL: </th> <td> ${customer.tel}</td></tr>
	<tr><th>Customer's payment account: </th> <td> ${customer.paymentAccount}</td></tr>
</table>
	<h2>Fill the following field and submit request:</h2>
	<div class="requestFormData">
		<form:form method="POST" modelAttribute="req" action="/dist_syst_proj/updateRequest">
			Request ID:	<form:input path="id" readonly="true"/><br><br>
			Credit Limit: <form:input id="creditLimit" path="creditLimit"/><br><br>
			Payment Selection: <form:select path="paymentSelection">
									<form:option value=" " label="---Select Payment Method---"/>
									<form:option value="Cash">Cash</form:option>
									<form:option value="Account">Account</form:option>
								</form:select><br><br>
			Employer Name: <form:input path="empName"/><br><br>
			Employer Address: <form:input path="empAddress"/><br><br>
			Salary: <form:input id="salary" path="salary"/><br><br>
			Annual Salary: <form:input id="annualSalary" path="annualSalary"/><br><br>
			Rate: <form:select path="rate">
						<form:option value=" " label="---Select Rate---"/>
						<form:option value="12%">12%</form:option>
						<form:option value="18%">18%</form:option>
				  </form:select><br><br>
			Customer's ID: <form:input path="customerId"/><br><br>
			Description: <form:textarea path="description"/>
			<input type="submit" class="btn btn-success" value="Update Request">
		</form:form>
	</div>
	<div class="calculator">
		<h2>Calculate customer's Credit Limit</h2>
		Salary: <input type="text" id="salaryField"/>
		<button id="calcButton" class="btn btn-success" onclick="myFoo()">Calculate!</button>
	</div><br>
	${message}
	<a href="/dist_syst_proj/employeePage" class="btn btn-success" role="button">Return to Search Page!</a><br>
</body>
</html>