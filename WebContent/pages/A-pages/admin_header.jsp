<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="./resources/styles/bootstrap4/bootstrap.min.css">
 <link rel="stylesheet" type="text/css" href="./resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
 
 <%@ include file="../custom_styles.jsp" %>
</head>
<body>
	
	<nav class="admin-navbar navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="AdminControl">HOME</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="productsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Prodotti
                </a>
                <div class="dropdown-menu" aria-labelledby="productsDropdown">
                    <a class="dropdown-item nav-link" href="./AdminControl?action=addProduct">Aggiungi Prodotto</a>
                    <a class="dropdown-item nav-link" href="./AdminControl?action=searchProduct">Cerca/Modifica Prodotti</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="ordersDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Ordini
                </a>
                <div class="dropdown-menu" aria-labelledby="ordersDropdown">
                    <a class="dropdown-item nav-link" href="./AdminControl?action=searchOrder">Cerca/Modifica Ordine</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="usersDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Utenti
                </a>
                <div class="dropdown-menu" aria-labelledby="usersDropdown">
                    <a class="dropdown-item nav-link" href="./AdminControl?action=addUser">Aggiungi Utente</a>
                    <a class="dropdown-item nav-link" href="./AdminControl?action=searchUser">Cerca/Modifica Utenti</a>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="Logout">Logout</a>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>