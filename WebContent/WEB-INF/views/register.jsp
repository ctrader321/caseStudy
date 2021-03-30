<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.WatchlistAndTracker.entities.User" %>
<%@include file="headCommon.jsp" %>

<title>Registration</title>
<%@include file="headCommonEnd.jsp" %>

	<div class="container" style="padding-top:10%">
	  <div class="row">
	    <div class="col">
	    </div>
	    <div class="col-5">
	    	<h1>Register</h1>
	    	<form method="post" style="display:flex; flex-direction:column">
				Username: <input type="text" name="username"><br>
				Password: <input type="password" name="password"><br>
				Verify Password: <input type="password" name="verifyPassword"><br>
				<input type="submit" name="register" value="Register" style="width:50%">
			</form>
	    </div>
	    <div class="col">
	    </div>
	  </div>
	</div>
<%@include file="footCommon.jsp"%>