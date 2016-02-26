<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
body{
	background-color:#ccccb3;
	font-size:40px;
}
input{
	width: 100%;
	padding: auto;
	margin: auto;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
}
input[type=submit]{
	width: 100%;
   	background-color: #4CAF50;
    color: white;
    padding: auto;
	margin: auto;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size:40px;
}
input{
	font-size:30px;
}
div.myForm{
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 30px;
	margin: 50px 200px 50px 200px;
	font-size:40px;
}
</style>
	<title>Login Home Page</title>
</head>
<body>
<div class="container">
	<div class="jumbotron">
		<h1>Bank of Greece</h1> 
		<p>Welcome to the Intranet application developed for our employees...</p> 
		<p>Bellow insert your account data (username & password) and proceed to login.</p>
	</div>
</div>
<div class="myForm">
	<h3 style="text-align:center;font-size:40px;"><strong>Login</strong></h3>
	<form:form method="POST" modelAttribute="loggedEmp" action="/dist_syst_proj/checkUser">
		<form:label path="acc_username">Username:</form:label>
		<form:input path="acc_username" />
		<br>
		<form:label path="acc_password">Password:</form:label>
		<form:password path="acc_password"/>
		<br><br>
		<input type="submit" value="Log In"/>
	</form:form>
	<p>${message}</p>
</div>
</body>
</html>
