<%@include file="headCommon.jsp"%>
<%@ page import="com.WatchlistAndTracker.entities.User"%>
<%@ page import="com.WatchlistAndTracker.entities.UserCurrentShow"%>
<%@ page import="com.WatchlistAndTracker.services.UserCurrentShowServices"%>
<%@ page import="com.WatchlistAndTracker.services.ShowServices"%>
<%@ page import="java.util.List" %>
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
<title>Currently Watching</title>
<%@include file="headCommonEnd.jsp"%>
<%@include file="navbar.jsp"%>
 <%
	if(session.getAttribute("user") == null){
		
		request.setAttribute("loginAgainMessage", "Please sign in!");
		response.sendRedirect("login");
	}
%>
	<div class="container">
		<div class="row" style = "padding-top:5%">
			<div class = "col">
				<form action="addToCurrent" method= "get" style="display:flex; flex-direction:column; justify-content:space-between; width:50%">
					<span>Select a show to add (Total episode count)</span>
					<select name="showName">
						<c:forEach items="${showList}" var="show" begin="0">
							<option value="${show.showName}">${show.showName} == ${show.totalEpisodes}</option>
						</c:forEach>
					</select>
					<label>Which episode are you on?</label>
					<input style="width:50%" type="text">
					<p style="color:red">${alreadyInList}</p>
					<input style="width:50%" type="submit" name="addToCurrent" value="Add"> 				
				</form>
				<br>
				<form action="editInCurrent" method="get" style="display: flex; flex-direction: column; justify-content: space-between; width: 50%">
					<span>Select a show to edit</span> 
					<select	name="showName">
						<c:forEach items="${currentShows}" var="show" begin="0">
							<option value="${show.showName}">${show.showName} == ${show.totalEpisodes}</option>
						</c:forEach>
					</select>
					<label>Which episode did you most recently finish?</label> 
					<input style="width: 50%" type="text" name="episodeNumberToSet">
					<p style="color: red"></p>
					<input style="width: 50%" type="submit" name="addToCurrent" value="Add">
				</form>
		</div>
			<div class = "col-md">
			
				<h1>Current Watchlist:</h1>
			
				<table class="tg">
					<tr>
						<th class="tg-0pky" style="text-align: left">
							Show Name:
						</th>
						<th class="tg-0pky" style="text-align: left">
							Current Episode:
						</th>
						<th class="tg-0pky" style="text-align: left">
							Total Episodes:
						</th>
						<th class="tg-0pky" style="text-align: left">
							Completion Percentage:
						</th>
					</tr>
					<tbody id="currentlyWatching">
						<c:forEach items="${userList}" var="i" begin="0">
							<tr>
								<td width = "10%" class="tg-0pky">
									<c:out value="${i.getShowName()}"></c:out>
								</td>
								<td width = "10%" class="tg-brdm">
									<c:out value="${i.getCurrentEpisode()}"></c:out>
								</td>
								<td width = "10%" class="tg-0pky">
									<c:out value="${i.getTotalEpisodes()}"></c:out>
								</td>
								<td width = "10%" class="tg-brdm">
									<c:out value="${i.getCompletionPercentage()}"></c:out>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<%@include file="footCommon.jsp"%>