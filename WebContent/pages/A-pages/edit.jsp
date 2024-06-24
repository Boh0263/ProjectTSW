<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Product</title>
</head>
<body>
	<%@ include file="admin_header.jsp" %>
	<%
		String productId = request.getParameter("prodId");  <%-- Assuming this is done in the servlet %>
	    ProdottoDAOImp dao = new ProdottoDAOImp();
		Prodotto product = dao.doRetrieveByKey(productId);
		
	%>
	<div align="center">
		<form action="/AdminControl" method="post">
			<table class="productTable">
				<thead>
					<tr>
						<th colspan="2">
							Dettagli del Prodotto
						</th>
					</tr>
				</thead>
				<tr>
					<td>Nome</td>
					<td><input type="text" name="prodNome" size="20"
						value="<%=product.getNome()%>" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Giacenza</td>
					<td><input type="text" name="prodGiacenza" size="20"
						value="<%=product.getGiacenza()%>" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Descrizione</td>
					<td><input type="text" name="prodDescrizione" size="20"
						value="<%=product.getDescrizione()%>" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Prezzo</td>
					<td><input type="text" name="prodPrezzo" size="20"
						value="<%=product.getPrezzo()%>" class="productTextField"/></td>
				</tr>
				<tr>
					<td>IVA</td>
					<td><input type="text" name="prodIVA" size="20"
						value="<%=product.getIVA()%>" class="productTextField"/></td>
				</tr>
				
				<tr>
				<td>Categoria</td>
				<td>
				<select name="prodCategory" disabled> <%-- Al momento il cambio di categoria non è gestito. --%>       
                <option value="Abbigliamento" selected="<%= product instanceof Abbigliamento %>">Abbigliamento</option>
                <option value="Armatura" selected="<%= product instanceof Armatura %>">Armatura</option>
                <option value="Arma" selected="<%= product instanceof Arma %>">Arma</option>
                <option value="Accessorio" selected="<%= product instanceof Accessorio %>">Accessorio</option>
        		</select>
        		</td>
				</tr>
				
				<% if (product instanceof Abbigliamento) {  %>
				<tr>
					<td>Tipo</td>
					<td><input type="text" name="prodTipo" size="20"
						value="<%= ((Abbigliamento) product).getTipo() %>" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Genere</td>
					<td><input type="text" name="prodGenere" size="20"
						value="<%= ((Abbigliamento) product).getGenere() %>" class="productTextField"/></td>
				</tr> 
				<% } else if (product instanceof Armatura) {   %>
				<tr>
					<td>Materiale</td>
					<td><input type="text" name="prodMateriale" size="20"
						value="<%= ((Armatura) product).getMateriale() %>" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Pezzo</td>
					<td><input type="text" name="prodPezzo" size="20"
						value="<%= ((Armatura) product).getPezzo() %>" class="productTextField"/></td>
				</tr>
				<% } else if (product instanceof Arma) {   %>
				<tr>
					<td>Materiale</td>
					<td><input type="text" name="prodMateriale" size="20"
						value="<%= ((Arma) product).getMateriale() %>" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Tipo</td>
					<td><input type="text" name="prodTipo" size="20"
						value="<%= ((Arma) product).getTipo() %>" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Utilizzo</td>
					<td><input type="text" name="prodUtilizzo" size="20"
						value="<%= ((Arma) product).getUtilizzo() %>" class="productTextField"/></td>
				</tr>
				<% } %>
			</table>
			<button class="actionBtn" style="margin-top:10px">Save</button>
		</form>
	</div>
</body>
</html>