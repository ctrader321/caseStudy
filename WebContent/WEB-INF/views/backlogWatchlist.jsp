<%@include file="headCommon.jsp"%>
<%@ page import="com.WatchlistAndTracker.entities.User"%>
<style>
.tg {
	border-collapse: collapse;
	border-color: #C44D58;
	border-spacing: 0;
	border-style: solid;
	border-width: 1px;
}

.tg td {
	background-color: #F9CDAD;
	border-color: #C44D58;
	border-style: solid;
	border-width: 0px;
	color: #002b36;
	font-family: Arial, sans-serif;
	font-size: 14px;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg th {
	background-color: #FE4365;
	border-color: #C44D58;
	border-style: solid;
	border-width: 0px;
	color: #fdf6e3;
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg .tg-0pky {
	border-color: inherit;
	text-align: right;
	vertical-align: top
}

.tg .tg-brdm {
	background-color: #FFA4A0;
	border-color: inherit;
	text-align: right;
	vertical-align: top
}
</style>
<title>Backlog Watchlist</title>

<%@include file="headCommonEnd.jsp"%>
<%@include file="navbar.jsp"%>

<%
	if(session.getAttribute("user") == null){
		
		request.setAttribute("loginAgainMessage", "Please sign in!");
		response.sendRedirect("login");
	}
%>
<div class="container">
	<div class="row" style="padding-top:5%">
		
		<div class="col">
			
			<h1>Backlog Watchlist: </h1>
			
			<table class = "tg" style="width:75%">
				<tr>
					<th class="tg-0pky" style="text-align: left">
						Show Name:
					</th>

					<th class="tg-0pky" style="text-align: left">
						Total Episodes:
					</th>
				</tr>
				<tbody>
					<c:forEach items="${backlogList}" var="i" begin="0">
						<tr>
							<td width="10%" class="tg-0pky">
								<c:out value="${i.getShowName()}"></c:out>
							</td>
							<td width="10%" class="tg-0pky">
								<c:out value="${i.getTotalEpisodes()}"></c:out>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col">
			<p> Backlog empty? Try adding a show from our collection below!
			<form style="width:50%" action="addToBacklog" method="post">
				<select name="showName" style="width:50%">
					<c:forEach items="${showList}" var="show" begin="0">
						<option value="${show.showName}">${show.showName}</option>
					</c:forEach>
				</select>
				<br>
				<input type="submit" name="removeFromBacklog" value="Add">	
			</form>
			<br>
			<p>Don't want to eventually watch that show? Remove it here!</p>
			<form style="width: 50%" action="removeFromBacklog" method="post">
				<select name="showName" style="width:50%">
					<c:forEach items="${backlogList}" var="show" begin="0">
						<option value="${show.showName}">${show.showName}</option>
					</c:forEach>
				</select> 
				<br>
				<input type="submit" name="removeFromBacklog" value="Remove">
			</form>
			<br>
			<p>Want to move a show to your current watchlist? Select which one from the dropdown below!</p>
			<form style="width:50%" action="moveFromBackToCurrent" method="post">
				<select name="showName" style="width:50%">
					<c:forEach items="${backlogList}" var="show" begin="0">
						<option value="${show.showName}">${show.showName}</option>
					</c:forEach>
				</select>
				<br>
				<input type="submit" name="moveToCurrent" value="Move">
			</form>
		</div>
	</div>
</div>





<%@include file="footCommon.jsp"%>