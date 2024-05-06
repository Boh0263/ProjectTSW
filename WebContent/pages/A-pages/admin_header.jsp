<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%
	if(session.getAttribute("username") == null)
	{
		response.sendRedirect("login.jsp"); 
	}
	%>
	<nav class="admin-navbar">
		<ul class="admin-navbar-nav">
			<li><a href="AdminHome.jsp">Home</a></li>
			<li><a href="addProduct.jsp">Add Product</a></li>
			<li><a href="viewProducts.jsp">View Products</a></li>
			<li><a href="searchProduct.jsp">Search Product</a></li>
			<li style="float:right;margin-right:10px"><a href="logout.jsp">Logout</a></li>
		</ul>
	</nav>
	<script src="./resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
</body>
</html>