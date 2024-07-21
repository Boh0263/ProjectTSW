<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/registration_style.css">
</head>
<body>
    <div class="container">
      <form id="form" action="./login" method="POST" class="form">
        <h2>Login</h2>
       
        <div class="form-control">
          <div class="input-field">
            <i class="fas fa-user"></i>
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Inserisci l'username" required autofocus/>
          </div>
        </div>
        <div class="form-control">
          <div class="input-field">
            <i class="fas fa-lock"></i>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Inserisci la password" required />
          </div>
        </div>
        
        <small>Non hai un account? <a href="./register">Registrati</a></small>
        <input type="hidden" name="ctoken" value="<%=session.getAttribute("ctoken")%>"/>
        <input type="hidden" id="cart" name="cart" />
        <button type="submit" value="login" class="actionBtn">Login</button>
      </form>
    </div>

    <div class="container" id="alert-container" style="display: none;">
        <div class="alert alert-danger" id="alert-message" role="alert">
            <% if (request.getAttribute("Messaggio") != null) { %>
                <%= (String) request.getAttribute("Messaggio") %>
            <% } %>
        </div>
    </div>

    <script src="./resources/js/jquery-3.2.1.min.js"></script>
    <script src="./resources/js/validation.js"></script>
     <script type="text/javascript">
        function validateForm() {
            let username = document.getElementById('username').value;
            let password = document.getElementById('password').value;


            if (false) {
                document.getElementById('alert-container').style.display = "block";
                document.getElementById('alert-message').innerHTML = "Username e/o password non validi";
                return false;
            } else {
                document.getElementById('alert-message').innerHTML = "";
                document.getElementById('alert-container').style.display = "none";
                return true;
            }
        }

        $(document).ready(function() {
            var cart = localStorage.getItem('prodotti');
            if (cart != null && cart != "") {
                document.getElementById('cart').value = cart;
            }

            $('#form').submit(function(e) {
                e.preventDefault();
                if (validateForm()) {
                    $('#form')[0].submit();
                    localStorage.removeItem('prodotti');
                }
            });
        });
    </script>
</body>
</html>
