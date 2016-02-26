<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<style>
		button.nButton{
		   	background-color: #4CAF50;
		    color: white;
		    padding: 5px;
			border: none;
			border-radius: 4px;
			cursor: pointer;
			font-size:30px;
			display:block;
		}
	</style>
	<title>Use Page</title>
</head>
<body>
<div class="container">
  <div class="jumbotron">
    <h1>Bank of Greece</h1>
    <p>User's personal page</p> 
  </div>
  <div class="row">
  	<div class="col-md-6">
  		<div class="userInfo">
  			<h3><strong>Online Services provided just for our customers</strong></h3>
	  		<p><b>Welcome back,</b> ${sessionScope.account.username}</p><br>
	  		<p><b>User account ID is,</b> ${sessionScope.account.accountId}</p><br>
	  		<p><b>Email:</b> ${sessionScope.account.email}</p><br>
	  		<p><b>Customer ID:</b> ${sessionScope.account.customerId}</p><br>
  		</div>
  	</div>
  	<div class="col-md-6">
  		<h3><strong>User options</strong></h3>
  		<button class="nButton" type="button" onclick="location.href='/client/newRequestForm';">Submit Request</button>
  		<br>
  		${message1}
  		<br>
  		<button class="nButton" type="button" onclick="location.href='/client/inspectRequest';">Inspect Request</button>
  		<br>
  		${message2}
  		<br>
  		<a href="/client/logout" class="btn btn-success" role="button">Log Out</a>
  	</div>
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
  </div>
  <br>
</body>
</html>