<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Products</title>
</head>
<body>
<%@ include file="admin_header.jsp" %>  

	<table class="productTable">
		<thead>
			<tr>
				<th>Nome Prodotto</th>
				<th>Descrizione</th>
				<th>Categoria</th>
				<th>Prezzo (&#8364;)</th>  
				<th>Giacenza</th>
				<th>Img 1</th>
				<th>Img 2</th>
				<th>Img 3</th>
				<th colspan="2">Actions</th>
			</tr> 
		</thead>
		<%
			Collection<?> productList = (Collection<?>) request.getAttribute("Prodotto");
			
			if (productList == null || productList.isEmpty()) { %>
				<tr><td colspan='10'>No products found!</td></tr>
	<% } else {
			Iterator<?> it = productList.iterator();
			while (it.hasNext()) {
				Prodotto p = (Prodotto) it.next();
		%>
		<tr>
			<td><%=p.getNome()%></td>
			<td><%=p.getDescrizione()%></td>
			<td><%=p.getPrezzo()%></td>
			<td><%=p.getGiacenza()%></td>
			<td><img src="<%=p.getImg1()%>" alt="Image 1" width="50" height="50"></td>
			<td><img src="<%=p.getImg2()%>" alt="Image 2" width="50" height="50"></td>
			<td><img src="<%=p.getImg3()%>" alt="Image 3" width="50" height="50"></td>
			<td><%=p.getClass().getSimpleName()%></td>
			<td><button class="actionBtn" onclick="location.href = 'editProduct.jsp?prodNome=<%= p.getNome()%>';">Edit</button></td>
			<td><button class="actionBtn" onclick="location.href = 'processDeleteProduct.jsp?prodNome=<%= p.getNome()%>';">Delete</button></td>
		</tr>
		<% } } %>
	</table>

</body>
</html>
