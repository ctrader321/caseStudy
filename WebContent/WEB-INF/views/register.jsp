<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

		if(request.getParameter("register") != null){
			User userBean = new User();
			userBean.setUsername(request.getParameter("username"));
			userBean.setUserPassword(request.getParameter("verifyPassword"));
			String verifyPass = request.getParameter("verifyPassword");
			if(userBean.getUserPassword() == verifyPass){%>
				<jsp:useBean id="user" scope="request" class="com.WatchlistAndTracker.entities.User">
					<jsp:setProperty property="username" name="username" value="${user.getUsername()}"/>
					<jsp:setProperty property="userPassword" name="userPass" value="${user.getUserPassword()}"/>
				</jsp:useBean>
			<%	response.sendRedirect("login.jsp");
			
			}
		}; %>
	
		




	<div class="container" style="padding-top:10%">
	  <div class="row">
	    <div class="col">
	    </div>
	    <div class="col-5" style="background-color:#d3d3d3; border-radius:25px;">
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