<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Adventurer's Kits</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Colo Shop Template">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/bootstrap4/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/plugins/jquery-ui-1.12.1.custom/jquery-ui.css">

<%@ include file="custom_styles.jsp" %>
</head>

<body>
<div class="super_container"> <!-- Inizio del contenitore principale (termina nel footer) -->
	<!-- Inizio dell' header -->

	<header class="header trans_300">

		<!-- Top Nav -->

		<div class="top_nav">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="top_nav_left">Spedizioni gratuite con ordine superiore a 50&euro; (Offerta Scaduta)</div>
					</div>
					<div class="col-md-6 text-right">
						<div class="top_nav_right">
							<ul class="top_nav_menu">

								
							<% if (session.getAttribute("username") != null) { 
							  String username = (String) session.getAttribute("username");
							  String spacing = "&nbsp;&nbsp;&nbsp;";
							  
							  if (username.length() <= 5) {
								  spacing += "&nbsp;&nbsp;";
							  } 
							
							%>
							
								<li class="account">
								<a href="${pageContext.request.contextPath}/Account">
									<%=spacing%><%=username%><%=spacing%>
									<i class="fa fa-angle-down"></i>
								</a>
								<ul class="account_selection">
								<% String uri = request.getRequestURI(); %>	
								<li><a href="${pageContext.request.contextPath}/Ordini"><i class="fa fa-shopping-cart" aria-hidden="true"></i>Ordini</a></li>
									<li><a href="${pageContext.request.contextPath}/Account"><i class="fa fa-user" aria-hidden="true"></i>Account</a></li>
									<li><a href="${pageContext.request.contextPath}/Logout"><i class="fa fa-sign-in" aria-hidden="true"></i>Esci</a></li>
								</ul>
							</li>
								
					   <% } else { %>
								<li class="account">
									<a href="#">
										&nbsp;&nbsp;&nbsp;Account&nbsp;&nbsp;&nbsp;
										<i class="fa fa-angle-down"></i>
									</a>
									<ul class="account_selection">
									<% String uri = request.getRequestURI(); %>	
									<li><a href="${pageContext.request.contextPath}/login?forward=<%= uri.substring(uri.lastIndexOf("/") + 1) %>"><i class="fa fa-sign-in" aria-hidden="true"></i>Entra</a></li>
										<li><a href="${pageContext.request.contextPath}/register"><i class="fa fa-user-plus" aria-hidden="true"></i>Registrati</a></li>
									</ul>
								</li>
								<% } %>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		

		<div class="main_nav_container" style="background-color:rgb(229,214,194)">
			<div class="container" style="background-color:rgb(229,214,194)">
				<div class="row" style="background-color:rgb(229,214,194)">
					<div class="col-lg-12 text-right" style="background-color:rgb(229,214,194)">
						<div class="logo_container">
							<img style="max-width: 80px; height: auto; " src="${pageContext.request.contextPath}/resources/static/images/Kits.jpg" alt="Kits'logo">
						</div>
						<nav class="navbar">
							<ul class="navbar_menu">
								<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
								<li><a href="${pageContext.request.contextPath}/Catalogo?category=Arma">catalogo</a></li>
								
							</ul>
							<ul class="navbar_utente">
								<li><a href="#"><i class="fa fa-search" aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-user" aria-hidden="true"></i></a></li>
								<li class="checkout">
									<a href="${pageContext.request.contextPath}/Carrello">
										<i class="fa fa-shopping-cart" aria-hidden="true"></i>
										<span id="checkout_items" class="checkout_items">0</span>
										
									</a>
								</li>
							</ul>
							<div class="dropdown_container">
								<i class="fa fa-bars" aria-hidden="true"></i>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>

	</header>

	<div class="fs_menu_overlay"></div>

	<!-- Menu Dropdown -->

	<div class="dropdown_menu">
		<div class="dropdown_close"><i class="fa fa-times" aria-hidden="true"></i></div>
		<div class="dropdown_menu_content text-right">
			<ul class="menu_top_nav">
				<li class="menu_item has-children"> 
				<% if(session != null && session.getAttribute("username") != null) { %>
					<a href="${pageContext.request.contextPath}/Account">
						My Account
						<i class="fa fa-angle-down"></i>
					</a>
					<% } else { %>
					<a href="#">
						Account
						<i class="fa fa-angle-down"></i>
					</a>
					<% } %>	
					<ul class="menu_sel">
					<% if(session != null && session.getAttribute("username") != null) { %>
							<li><a href="${pageContext.request.contextPath}/Account"><i class="fa fa-user" aria-hidden="true"></i>Account</a></li>
							<li><a href="${pageContext.request.contextPath}/Ordini"><i class="fa fa-shopping-bag" aria-hidden="true"></i>Ordini</a></li>
							<li><a href="${pageContext.request.contextPath}/Logout"><i class="fa fa-sign-out" aria-hidden="true"></i>Esci</a></li>
					<%} else { %>
						<% String uri = request.getRequestURI(); %>	
						<li><a href="${pageContext.request.contextPath}/login?forward=<%= uri.substring(uri.lastIndexOf("/") + 1) %>"><i class="fa fa-sign-in" aria-hidden="true"></i>Entra</a></li> <!-- TODO gestione con la sessione utente -->
						<li><a href="${pageContext.request.contextPath}/register"><i class="fa fa-user-plus" aria-hidden="true"></i>Registrati</a></li>
						<% } %>
					</ul>
				</li>
				<li class="menu_item"><a href="${pageContext.request.contextPath}/index.jsp">home</a></li>
				<li class="menu_item"><a href="${pageContext.request.contextPath}/Catalogo?category=All">catalogo</a></li>
			</ul>
		</div>
	</div>
	
	