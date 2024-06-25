<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Prodotti</title>
<link rel="stylesheet" href="./resources/styles/adminsearch_style.css">
</head>
<body>
<%@ include file="admin_header.jsp" %>  

<div class="container">
    <div class="table-container">
        <table class="table table-striped table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Nome Prodotto</th>
                    <th>Descrizione</th>
                    <th>Giacenza</th>
                    <th>Prezzo (&#8364;)</th>
                    <th>Img 1</th>
                    <th>Img 2</th>
                    <th>Img 3</th>
                    <th>Categoria</th>
                    <th colspan="2">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% Collection<?> productList = (Collection<?>) request.getAttribute("prodAll");
                if (productList == null || productList.isEmpty()) { %>
                    <tr><td colspan='10'>Nessun prodotto trovato!</td></tr>
                <% } else {
                    Iterator<?> it = productList.iterator();
                    while (it.hasNext()) {
                        Prodotto p = (Prodotto) it.next();
                %>
                <tr>
                    <td><%= p.getNome() %></td>
                    <td><%= p.getDescrizione() %></td>
                    <td><%= p.getGiacenza() %></td>
                    <td><%= p.getPrezzo() %></td>
                    <td><img src="<%= p.getImg1() %>" alt="Image 1" width="50" height="50"></td>
                    <td><img src="<%= p.getImg2() %>" alt="Image 2" width="50" height="50"></td>
                    <td><img src="<%= p.getImg3() %>" alt="Image 3" width="50" height="50"></td>
                    <td><%= p.getClass().getSimpleName() %></td>
                    <td><button class="btn btn-primary actionBtn" onclick="location.href = 'AdminControl?action=editProduct&prodName=<%= p.getNome() %>';">Modifica</button></td>
                    <td><button class="btn btn-danger actionBtn" onclick="removeProduct('<%= p.getNome() %>');">Rimuovi</button></td>
                </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function removeProduct(prodName) {
        if (confirm("Sei sicuro di voler rimuovere il prodotto?")) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "AdminControl", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send("action=deleteProduct&prodName=" + prodName);
            
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    location.reload();
                } else if (xhr.readyState == 4 && xhr.status != 200) {
                    alert("Errore nella rimozione del prodotto!");
                }
            }
        }
    }
</script>

<%@ include file="admin_footer.jsp" %>
</body>
</html>

