<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!--
	This is the login page
	This page contains a text field for username and another for password
	This page redirects to the Home page
-->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width:device-width, initial-scale=1">
		<link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" />
		<title>Login | Online Auction House</title>
	</head>
	<body>
		<div align="center"><img src = "logo.jpg" width = "200" height = "100"></div>
		<h1 align = "center" style = "font-size:200%">Welcome to vBay</h1>
		<h2 align = "center" style = "font-size:160%">The Online Auction House</h2>
		<div class="card" style = "width:40%;margin:auto" >
			<h2 align = "center">Sign in</h2>
			<%
				String email = (String)session.getAttribute("email");
				String role = (String)session.getAttribute("role");
				
				//redirect to home page if already logged in
				if(email != null) {
					if(role.equals("manager")) {
						response.sendRedirect("managerHome.jsp");
					}
					else if(role.equals("customerRepresentative")) {
						response.sendRedirect("customerRepresentativeHome.jsp");
					}
					else {
						response.sendRedirect("home.jsp");	
					}
				}
				
				String status = request.getParameter("status");
				if(status != null) {
					if(status.equals("false")) {
						out.print("Incorrect Login credentials!");
					}
					else {
						out.print("Some error occurred! Please try again.");
					}
				}
			%>
			<form action="login">
				<div class="form-group" align = "center">
					<input type="text" style = "width:60%" class="form-control" name="username" placeholder="Email" required>
				</div>
				<div class="form-group" align = "center">
	            	<input type="password" style = "width:60%" class="form-control" name="password" placeholder="Password" required>
	        	</div>
	        	<div class="form-group" align = "center">
					<input type="submit" value="Sign in" class="btn btn-success"/>
				</div>
			</form>
		</div>
		
		<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/4.1.3/bootstrap.min.js"></script>
	</body>
</html>