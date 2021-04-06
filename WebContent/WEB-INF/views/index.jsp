<%@include file="headCommon.jsp"%>
<%@ page import="com.WatchlistAndTracker.entities.User"%>
<style>
body {
	font-family: 'ABeeZee', sans-serif;
}
</style>

<title>Home</title>
<%@include file="headCommonEnd.jsp"%>
<%@include file="navbar.jsp"%>
<%
	if(session.getAttribute("user") == null){
		
		request.setAttribute("loginAgainMessage", "Please sign in!");
		response.sendRedirect("login");
	}
%>
	<div class="container">
		<div class="row">
			<div class="col">
				<p>Hello, ${user.getUsername()}</p>
			</div>
			<div class="col"></div>
			<div class="col"></div>
		</div>
	</div>



<%@include file="footCommon.jsp"%>
