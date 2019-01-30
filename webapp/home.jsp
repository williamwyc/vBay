<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
	This is the Home page for Customer Representative
	This page contains various buttons to navigate the online auction house
	This page contains customer representative specific accessible buttons
-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		<title>Customer Home</title>
		<style type="text/css">
			*{
				margin:0;padding:0;
 				-webkit-box-sizing: border-box;
 				-moz-box-sizing: border-box;
   				box-sizing: border-box;
			}
			.container{
				margin:auto
				width:80%;
				overflow:hidden;
			}
			li{
				border-top:1px solid lightgrey;
				border-left:1px solid lightgrey;
				border-right:1px solid lightgrey;
				height:50px;
				line-height:50px;
				list-style:none;
				text-align:center;
				width:800px;
			}
			li:last-child{
				height:50px;border-right:1px solid lightgrey;
				border-bottom:1px solid lightgrey;
			}
			li.active {
				border-right:1px solid white;background:lightgrey;
			}
			li.normal {
				border-right:1px solid white;background:red;
			}
			#tab_content{
				float:right;
				width:600px;
				background:white;
				height:300px;
				overflow:hidden;
				border-top:1px solid lightgrey;
				border-right:1px solid lightgrey;
				border-bottom:1px solid lightgrey;
				border-left:0px solid lightgrey;
			}
			#tab_content .content{
 				padding: 15px;
 				-moz-border-radius: 5px;
				height:300px;
			}
		</style>
	</head>
	<body>
		<div align="center"><img src = "logo.jpg" width = "200" height = "100"></div>
		<div class="container">
			<h2>Customer Options:</h2>
			<%
			String email = (String)session.getAttribute("email");
			String role = (String)session.getAttribute("role");
			
			//redirect to appropriate home page if already logged in
			if(email != null) {
				if(role.equals("manager")) {
					response.sendRedirect("managerHome.jsp");
				}
				else if(role.equals("customerRepresentative")) {
					response.sendRedirect("customerRepresentativeHome.jsp");
				}
			}
			else {
				// redirect to log in if not alreaddy logged in
				response.sendRedirect("index.jsp");
			}
			%>
			<div id="tab_content">
 				<div id="a" class="content">
 					<div class="container">
						<form action="getAuctions">
							<input type="submit" value="Bid History" class="btn btn-success"/>
						</form>
					</div>
 				</div>
 				<div id="b" class="content">
 					<div class="container">
						<form action="getAuctionHistory">
							<input type="submit" value="All History" class="btn btn-primary"/>
						</form>
					</div>
 				</div>
 				<div id="c" class="content">
 					<div class="container">
						<form action="getSellers">
							<input type="submit" value="Seller Info" class="btn btn-primary"/>
						</form>
					</div>
 				</div>
 				<div id="d" class="content">
 					<div class="container">
							<form action="searchItems">
								<input type="submit" value="View Auctions" class="btn btn-success"/>
							</form>
						</div>
 				</div>
 				<div id="e" class="content">
 					<div class="container">
							<form action="getBestsellersForCustomer">
								<input type="submit" value="View Best Sellers" class="btn btn-success"/>
							</form>
						</div>
 				</div>
 				<div id="f" class="content">
 					<div class="container">
							<form action="personalizedSuggestions">
								<input type="submit" value="View Personalized Suggestions" class="btn btn-success"/>
							</form>
						</div>
 				</div>
			</div> 
			<div >
				<ul id='tabnav'>
				<li class='active'><a href="#a" ><h5 class="card-title">View Bid History</h5></a></li>
				<li><a href="#b" ><h5 class="card-title">History of all auctions participated in</h5></a></li>
				<li><a href="#c" ><h5 class="card-title">Items sold by Seller</h5></a></li>
				<li><a href="#d" ><h5 class="card-title">View Items in Auctions</h5></a></li>
				<li><a href="#e" ><h5 class="card-title">View Best Seller Items</h5></a></li>
				<li><a href="#f" ><h5 class="card-title">Personalized Item Suggestion List</h5></a></li>
				</ul>
			</div>
				
		</div>
		<div class="container">
			<form action="logout">
				<input type="submit" value="Logout" class="btn btn-danger"/>
			</form>
		</div>
		<script type="text/javascript">
			function changeStyle(){
 				this.onclick=function(){
	 				var list=this.parentNode.childNodes;
 					for(var i=0;i<list.length;i++){
  						if(1==list[i].nodeType && 'active'==list[i].className){
    						list[i].className="";
  						}
 					}
 					this.className='active';
 				}
			}
 			var tabs=document.getElementById('tabnav').childNodes;
 			for(var i=0;i<tabs.length;i++){
 				if(1==tabs[i].nodeType){
  					changeStyle.call(tabs[i]);
 				}
			}
		</script>
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>