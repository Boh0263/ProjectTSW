<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/registration_style.css">
</head>
<body>
    <div class="container">
      <form  id="form" action="./login" method="POST" class="form">
        <h2>Login</h2>
       
        <div class="form-control">
          <div class="input-field">
          <i class="fas fa-user"></i>
          <label for="username">Username</label>
          <input type="text" id="username" name="username" placeholder="Inserisci l'username"  required autofocus/>
          </div>
        </div>
        <div class="form-control">
        <div class="input-field">
          <i class="fas fa-lock"></i>
          <label for="password">Password</label>
          <input type="password" id="password" name="password" placeholder="Inserisci la password" required />
        </div>
        </div>
        <button type="submit" value="login" class="actionBtn">Login</button>
      </form>
    </div>

	<% if(request.getAttribute("Messaggio") != null) { %>
	<div class="container" style="display: none;">
		<div class="alert alert-danger" role="alert">
			<%=(String) request.getAttribute("Messaggio")%>
		</div>
	</div>
	<% } %>	
	<script src="./resources/js/validation.js"></script>
</body>
</html>