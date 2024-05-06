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

<div align="center">
<h2>Product Management System</h2>
<label>Welcome <%= userName %></label>
</div>

</body>
</html>