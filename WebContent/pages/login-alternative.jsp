<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/registration_style.css">
</head>
<body>
    <div class="container">
      <form  id="form" action="/login" method="post" class="form" onsubmit="return formVal()">
        <h2>Login</h2>
        <
        <div class="form-control">
          <div class="input-field">
          <i class="fas fa-user"></i>
          <label for="username">Username</label>
          <input type="text" id="username" placeholder="Inserisci l'username"  required autofocus/>
          </div>
        </div>
        <div class="form-control">
        <div class="input-field">
          <i class="fas fa-lock"></i>
          <label for="password">Password</label>
          <input type="password" id="password" placeholder="Inserisci la password" required />
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
	<script src="${pageContext.request.contextPath}/resources/scripts/validation.js"></script>
	<script>
		function formVal() {
		var username = document.getElementById('username').value;
    	var password = document.getElementById('password').value;
    	var alertDiv = document.querySelector('.alert');

    	if(validateUsername(username) && validatePassword(password)) {
        alertDiv.style.display = 'block';
        alertDiv.innerHTML = 'campi invalidi';
        return false;
    	}
    	alertDiv.style.display = 'none';
    	return true;
		}
	</script>
</body>
</html>