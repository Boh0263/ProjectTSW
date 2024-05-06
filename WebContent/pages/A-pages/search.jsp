<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.ProdottoDAOImp"%>
<%@page import="model.Prodotto"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Product</title>
</head>
<body>
<%@ include file="admin_header.jsp" %>  

<div align="center" style="padding-top:25px;">
	<form action="/AdminControl?action=search" method="post">
		<label>Enter Product Name: </label>
		<input type="text" name="prodNome" size="25" class="searchTextField"/>
		<button class="actionBtn">Search</button>
	</form>
</div> 
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
		   Prodotto p = (Prodotto) request.getAttribute("Prodotto");
		 	if (p != null) {
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
				<form action="/AdminControl?action=edit" method="post" style="display: none;">
      			<input type="hidden" name="prodNome" value="<%= p.getNome() %>">
      			</form>
      			<form action="/AdminControl?action=delete" method="post" style="display: none;">
      			<input type="hidden" name="prodNome" value="<%= p.getNome() %>">
      			</form>		
      		<td><button type="button" onclick="submitForm('edit')">Edit</button></td>
      		<td><button type="button" onclick="submitForm('delete')">Delete</button></td>
		</tr>
	    <%		}
				else
				{
		%>
			<tr>
				<td colspan="5">No record to display</td>
			</tr>
		<% } %>
	</table>

</body>
<script> //TODO Creare il file .js per il seguente script embedded:
  function submitForm(action) {
    var form = document.querySelector("form[action*='" + action + "']");
    form.style.display = "block";
    form.submit();
  }
</script>
</html>