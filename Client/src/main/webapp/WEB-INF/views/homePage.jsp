<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<style>
body{
	background-color:#ccccb3;
	font-size:32px;
}
input{
	width: 100%;
	padding: 0px;
	margin: 5px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	font-size:32px;
}
input[type=submit]{
	width: 35%;
   	background-color: #4CAF50;
    color: white;
    padding: 0px;
	margin-left:auto;
	margin-right:auto;
	display:block;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size:30px;
}
div.myForm{
	border-radius: 15px;
	background-color: #f2f2f2;
	padding:10px;
	margin-right:5px;
	margin-bottom:5px;
	font-size:22px;
}
p.message{
	color:red;
}
button.newRequestButton{
   	background-color: #4CAF50;
    color: white;
    padding: 0px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size:30px;
	margin-left:auto;
	margin-right:auto;
	display:block;
}
</style>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Home</title>
</head>
<body>
<div class="container">
  <div class="jumbotron">
    <h1>Bank of Greece official Website</h1>
    <p>Welcome to the bank of Greece, number #1 bank in greece...</p> 
  </div>
  <div class="row">
    <div class="col-md-6">
      <h3>Social News</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec luctus nisi erat, a rutrum felis imperdiet eget. Aenean volutpat,
		nibh id cursus volutpat, magna urna consequat nunc, volutpat congue nunc lorem nec mauris. Nunc malesuada, mi quis bibendum efficitur,
		arcu libero scelerisque odio, eu auctor arcu massa vel leo. Sed efficitur augue in nunc hendrerit vulputate.
		Donec fringilla volutpat erat, eget consectetur lectus fermentum eget. Pellentesque hendrerit feugiat dapibus.
		Cras lorem quam, mattis vel enim eu, molestie finibus lectus. Sed eu maximus lectus. Proin congue vel felis sed egestas.
		Fusce accumsan, quam id scelerisque tristique, dui tortor tempus lorem, et efficitur tortor lacus non ipsum. Vestibulum ac magna erat.
		Fusce et diam id eros feugiat tempus a in nunc.Pellentesque lorem nisi, facilisis a tincidunt eget,
		viverra a neque. Pellentesque sollicitudin tempor pellentesque. Duis ante velit, semper vehicula libero vitae, hendrerit tempus urna.
		Suspendisse potenti. Duis tellus leo, faucibus eu suscipit et, imperdiet quis ipsum. Proin non lorem et magna rhoncus lobortis in ultrices dui.
		Suspendisse potenti. Nullam mattis, tellus sed ornare tristique, mauris sapien imperdiet nunc, non interdum metus nibh at massa.
	</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
      <button type="button" class="btn btn-info">See social news</button>
    </div>
    <div class="col-md-6">
      <h3>Bank Departments</h3>
      <p>Phasellus maximus ligula risus, at dapibus erat luctus eget. Fusce eu turpis dui. Maecenas id ipsum lorem. Phasellus nulla sapien,
		cursus sed leo vitae, varius fringilla ante. Pellentesque semper ultricies euismod. Vestibulum aliquam enim ligula, ut bibendum eros
		condimentum et.Aliquam erat volutpat. Donec quis magna quis urna congue facilisis. Sed pharetra sapien et ligula vulputate iaculis.
		Nullam eget neque dolor. Quisque non viverra justo. Vestibulum ut mauris lacus. Ut nec diam sodales ante eleifend efficitur sit amet sed ante.
		Sed blandit ante nisl, ut tempus eros aliquam sed. Morbi semper molestie lacus vitae interdum. Aliquam quis fringilla risus. Nam faucibus fermentum
		turpis sit amet pulvinar.Nullam semper mattis mi ac lobortis. Ut sit amet luctus arcu, sed sodales augue.
		Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse aliquam sem eget ipsum
		auctor dapibus. Duis condimentum turpis vel libero efficitur, vel rutrum odio ullamcorper. Integer nec ligula eget dui faucibus dictum id semper arcu.
		Donec pellentesque, elit at egestas semper, sapien.
	</p>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>
      <button type="button" class="btn btn-info">See bank departments</button>
    </div>
  </div>
  <br>
  <div class="row">
  	<div class="col-md-8">
  		<div class="myForm">
			<h3 style="text-align:center;">Login</h3>
			<form:form method="POST" modelAttribute="acc" action="/client/login">
				<form:label path="username">Username:</form:label>
				<form:input path="username" /><br>
				<form:label path="password">Password:</form:label>
				<form:password path="password"/><br>
				<input type="submit" value="Log In"/>
			</form:form>
		</div>
		${message}
  	</div>
  	<div class="col-md-4">
  		<h3>Newsletter</h3>
  		<form role="form" action="/client/submitMail" method="POST">
  			<div class="form-group">
    			<label for="email">Email address:</label>
    			<input type="email" class="form-control" id="email" name="email">
  			</div>
  			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	${response}
  </div>
  </div>
</div>
</body>
</html>
