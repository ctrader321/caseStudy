
<%@ page import="com.WatchlistAndTracker.entities.User" %>
<%@include file="headCommon.jsp" %>
<style>
.col-5{
	padding:2em;
}
input{
	border-radius: 25px;
}
</style>
<title>Registration</title>
<%@include file="headCommonEnd.jsp" %>
<%
	String message = null;
	

%>
	<div class="container" style="padding-top:10%">
	  <div class="row">
	    <div class="col">
	    </div>
	    <div class="col-5" style="background-color:#d3d3d3; border-radius:25px;">
	    	<h1>Register an Account!</h1>
	    	<form action="registerSubmit" method="post" style="display:flex; flex-direction:column">
				Username: <input type="text" name="username"><br>
				Password: <input type="password" name="userPassword"><br>
				Verify Password: <input type="password" name="verifyPassword"><br>
				<input type="submit" name="register" value="Register" style="width:50%">
			</form>
	    </div>
	    <div class="col">
	    </div>
	  </div>
	</div>
	

	
<%@include file="footCommon.jsp"%>