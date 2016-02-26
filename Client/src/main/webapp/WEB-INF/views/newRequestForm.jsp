<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		body{background-color:#adebad;}
		button{font-size:20px;}
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
	<title>New Request Form</title>
</head>
<body>
<h2>Fill this form with your personal information to make submit a request!</h2>

<div class="requestFormData">
	<form:form method="POST" modelAttribute="req" action="/client/addRequest">
		Credit Limit: <form:input id="creditLimit" path="creditLimit" required="true"/><br><br>
		Payment Selection: <form:select path="paymentSelection" required="true">
								<form:option value="" label="---Select Payment Method---"/>
								<form:option value="Cash">Cash</form:option>
								<form:option value="Account">Account</form:option>
							</form:select><br><br>
		Employer Name: <form:input path="empName" required="true"/><br><br>
		Employer Address: <form:input path="empAddress" required="true"/><br><br>
		Salary: <form:input id="salary" path="salary" required="true"/><br><br>
		Annual Salary: <form:input id="annualSalary" path="annualSalary"/><br><br>
		Rate: <form:select path="rate">
					<form:option value="" label="---Select Rate---"/>
					<form:option value="12%">12%</form:option>
					<form:option value="18%">18%</form:option>
			  </form:select><br><br>
		<input type="submit" class="btn btn-success" value="Submit new Request">
	</form:form>
</div>
<div class="calculator">
	<h2>Calculate customer's Credit Limit</h2>
	Salary: <input type="text" id="salaryField"/>
	<button id="calcButton" class="btn btn-success" onclick="myFoo()">Calculate!</button>
</div>
<br>
${message}
<br>
<button class="btn btn-success" type="button" onclick="location.href='/client/userPage';">Cancel</button>
</body>
</html>