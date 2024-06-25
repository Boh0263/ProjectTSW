<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<%@ include file="admin_header.jsp" %>

<%
String userName = (String)session.getAttribute("username");
%>

<div class="container mt-4 text-center">
<h2>Kiitz Management System </h2>
<label>Benvenuto <%= userName %>!</label>
</div>

</body>
<%@ include file="admin_footer.jsp" %>
</html>