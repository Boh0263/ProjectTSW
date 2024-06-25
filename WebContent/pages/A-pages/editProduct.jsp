<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifica Prodotto</title>
<link rel="stylesheet" href="./resources/styles/edit_style.css">
</head>
<body>
<%@ include file="admin_header.jsp" %>  

<%
    Prodotto product = (Prodotto) request.getAttribute("prodtbe");
    if (product != null) {
%>
<div class="container form-container">
    <div class="card">
        <div class="card-header">
            Modifica Prodotto
        </div>
        <div class="card-body">
            <form action="/AdminControl" method="post">
                <div class="form-group">
                    <label for="prodNome">Nome</label>
                    <input type="text" id="prodNome" name="prodNome" class="form-control" value="<%= product.getNome() %>">
                </div>
                <div class="form-group">
                    <label for="prodGiacenza">Giacenza</label>
                    <input type="text" id="prodGiacenza" name="prodGiacenza" class="form-control" value="<%= product.getGiacenza() %>">
                </div>
                <div class="form-group">
                    <label for="prodDescrizione">Descrizione</label>
                    <input type="text" id="prodDescrizione" name="prodDescrizione" class="form-control" value="<%= product.getDescrizione() %>">
                </div>
                <div class="form-group">
                    <label for="prodPrezzo">Prezzo</label>
                    <input type="text" id="prodPrezzo" name="prodPrezzo" class="form-control" value="<%= product.getPrezzo() %>">
                </div>
                <div class="form-group">
                    <label for="prodIVA">IVA</label>
                    <input type="text" id="prodIVA" name="prodIVA" class="form-control" value="<%= product.getIVA() %>">
                </div>
                <div class="form-group">
                    <label for="prodCategory">Categoria</label>
                    <select id="prodCategory" name="prodCategory" class="form-control" disabled>
                        <option value="Abbigliamento" <% if (product instanceof Abbigliamento) { %>selected<% } %>>Abbigliamento</option>
                        <option value="Armatura" <% if (product instanceof Armatura) { %>selected<% } %>>Armatura</option>
                        <option value="Arma" <% if (product instanceof Arma) { %>selected<% } %>>Arma</option>
                        <option value="Accessorio" <% if (product instanceof Accessorio) { %>selected<% } %>>Accessorio</option>
                    </select>
                </div>

                <% if (product instanceof Abbigliamento) { %>
                <div class="form-group">
                    <label for="prodTipo">Tipo</label>
                    <input type="text" id="prodTipo" name="prodTipo" class="form-control" value="<%= ((Abbigliamento) product).getTipo() %>">
                </div>
                <div class="form-group">
                    <label for="prodGenere">Genere</label>
                    <input type="text" id="prodGenere" name="prodGenere" class="form-control" value="<%= ((Abbigliamento) product).getGenere() %>">
                </div>
                <% } else if (product instanceof Armatura) { %>
                <div class="form-group">
                    <label for="prodMateriale">Materiale</label>
                    <input type="text" id="prodMateriale" name="prodMateriale" class="form-control" value="<%= ((Armatura) product).getMateriale() %>">
                </div>
                <div class="form-group">
                    <label for="prodPezzo">Pezzo</label>
                    <input type="text" id="prodPezzo" name="prodPezzo" class="form-control" value="<%= ((Armatura) product).getPezzo() %>">
                </div>
                <% } else if (product instanceof Arma) { %>
                <div class="form-group">
                    <label for="prodMateriale">Materiale</label>
                    <input type="text" id="prodMateriale" name="prodMateriale" class="form-control" value="<%= ((Arma) product).getMateriale() %>">
                </div>
                <div class="form-group">
                    <label for="prodTipo">Tipo</label>
                    <input type="text" id="prodTipo" name="prodTipo" class="form-control" value="<%= ((Arma) product).getTipo() %>">
                </div>
                <div class="form-group">
                    <label for="prodUtilizzo">Utilizzo</label>
                    <input type="text" id="prodUtilizzo" name="prodUtilizzo" class="form-control" value="<%= ((Arma) product).getUtilizzo() %>">
                </div>
                <% } %>
                <button type="submit" class="btn btn-primary btn-block">Save</button>
            </form>
        </div>
    </div>
</div>
<% } %>


<script src="./resources/js/jquery-3.2.1.min.js"></script>

<script type="text/javascript">
</script>
<%@ include file="admin_footer.jsp" %>
</body>

</html>
