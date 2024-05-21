<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css" href="../resources/styles/registration_style.css">
</head>
<body>
    <div class="container">
      <form  id="form" action="./login" method="post" class="form">
        <h2>Login</h2>
        <div class="form-control">
          <label for="username">Username</label>
          <input type="text" id="username" placeholder="Inserisci l'username" />
          <small>Error message</small>
        </div>
        <div class="form-control">
          <label for="password">Password</label>
          <input type="password" id="password" placeholder="Inserisci la password" />
          <small>Error message</small>
        </div>
        <button type="submit" value="login" class="actionBtn">Login</button>
      </form>
    </div>

	<% if(request.getAttribute("Messaggio") != null) { %>
	<div class="container" style="">
		<div class="alert alert-danger" role="alert">
			<%=(String) request.getAttribute("Messaggio")%>
		</div>
	</div>
	<% } %>	
</body>
</html>