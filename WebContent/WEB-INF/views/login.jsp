<%@include file="headCommon.jsp"%>
<title>Login</title>
<style>
.col-5 {
	padding: 2em;
}
input{
	border-radius: 25px;
}
</style>
<%@include file="headCommonEnd.jsp"%>

<div class="container" style="padding-top: 10%">
	<div class="row">
		<div class="col"></div>
		<div class="col-5"
			style="background-color: #d3d3d3; border-radius: 25px;">
			<h1>Welcome to MyWatchlist!</h1>
			<form action="loginprocess" method="post"
				style="display: flex; flex-direction: column">
				Username: <input type="text" name="user"><br>
				Password: <input type="password" name="password"><br> 
				<input type="submit" name="login" value="Login" style="width: 50%">
			</form>
			<a href="register">Create an account!</a>
		</div>
		<div class="col"></div>
	</div>
</div>
<%@include file="footCommon.jsp"%>