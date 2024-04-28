<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Adventurer's Kits</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Colo Shop Template">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="./resources/styles/bootstrap4/bootstrap.min.css">
<link href="./resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="./resources/plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="./resources/plugins/jquery-ui-1.12.1.custom/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="./resources/styles/categories_styles.css">
<link rel="stylesheet" type="text/css" href="./resources/styles/categories_responsive.css">
</head>

<body>
<div class="super_container">
	<!-- Header -->

	<header class="header trans_300">

		<!-- Top Navigation -->

		<div class="top_nav">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="top_nav_left">Spedizioni gratuite con ordine superiore a 50$</div>
					</div>
					<div class="col-md-6 text-right">
						<div class="top_nav_right">
							<ul class="top_nav_menu">

								<!-- Currency / Language / My Account -->

								<li class="account">
									<a href="#">
										&nbsp;&nbsp;&nbsp;Account&nbsp;&nbsp;&nbsp;
										<i class="fa fa-angle-down"></i>
									</a>
									<ul class="account_selection">
										<li><a href="#"><i class="fa fa-sign-in" aria-hidden="true"></i>Entra</a></li>
										<li><a href="#"><i class="fa fa-user-plus" aria-hidden="true"></i>Registrati</a></li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Main Navigation -->

		<div class="main_nav_container" style="background-color:rgb(229,214,194)">
			<div class="container" style="background-color:rgb(229,214,194)">
				<div class="row" style="background-color:rgb(229,214,194)">
					<div class="col-lg-12 text-right" style="background-color:rgb(229,214,194)">
						<div class="logo_container">
							<img style="max-width: 80px; height: auto; " src="./resources/static/images/Kits.jpg" alt="">
						</div>
						<nav class="navbar">
							<ul class="navbar_menu">
								<li><a href="./index.jsp">Home</a></li>
								<li><a href="#">catalogo</a></li>
								<li><a href="#">promozioni</a></li> <!-- da implementare -->
								<li><a href="#">pagine</a></li>  <!-- da implementare -->
								<li><a href="contact.html">contatti</a></li>
							</ul>
							<ul class="navbar_utente">
								<li><a href="#"><i class="fa fa-search" aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-user" aria-hidden="true"></i></a></li>
								<li class="checkout">
									<a href="#">
										<i class="fa fa-shopping-cart" aria-hidden="true"></i>
										<span id="checkout_items" class="checkout_items">x</span>
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

	<!-- Hamburger Menu -->

	<div class="dropdown_menu">
		<div class="dropdown_close"><i class="fa fa-times" aria-hidden="true"></i></div>
		<div class="dropdown_menu_content text-right">
			<ul class="menu_top_nav">
				<li class="menu_item has-children">
					<a href="#">
						usd
						<i class="fa fa-angle-down"></i>
					</a>
					<ul class="menu_sel">
						<li><a href="#">cad</a></li>
						<li><a href="#">aud</a></li>
						<li><a href="#">eur</a></li>
						<li><a href="#">gbp</a></li>
					</ul>
				</li>
				<li class="menu_item has-children">
					<a href="#">
						English
						<i class="fa fa-angle-down"></i>
					</a>
					<ul class="menu_sel">
						<li><a href="#">French</a></li>
						<li><a href="#">Italian</a></li>
						<li><a href="#">German</a></li>
						<li><a href="#">Spanish</a></li>
					</ul>
				</li>
				<li class="menu_item has-children">
					<a href="#">
						My Account
						<i class="fa fa-angle-down"></i>
					</a>
					<ul class="menu_sel">
						<li><a href="#"><i class="fa fa-sign-in" aria-hidden="true"></i>Sign In</a></li>
						<li><a href="#"><i class="fa fa-user-plus" aria-hidden="true"></i>Register</a></li>
					</ul>
				</li>
				<li class="menu_item"><a href="./index.jsp">home</a></li>
				<li class="menu_item"><a href="#">catalogo</a></li>
				<li class="menu_item"><a href="#">promozioni</a></li>
				<li class="menu_item"><a href="#">pagine</a></li>
				<li class="menu_item"><a href="./pages/contact.html">contatti</a></li>
			</ul>
		</div>
	</div>
	
	