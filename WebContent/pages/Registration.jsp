<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/registration_style.css">
    <title>Registrazione</title>
  </head>
  <body>
    <div class="container">
    <form id="form" action="${pageContext.request.contextPath}/register" method="post" class="form">
     <fieldset>  
       <div class="row"> 
        <div class="form-control">
          <label for="username">Username</label>
          <input type="text" id="username" name="username" placeholder="Enter username" />
          <span id="usernameError"></span>
        </div>
        <div class="form-control">
          <label for="email">Email</label>
          <input type="text" id="email" name="email" placeholder="Enter email" />
          <span id="emailError"></span>
        </div>
        </div>
        <div class="row">
        <div class="form-control">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter password" />
					<span id="passwordError"></span>
			</div>
        <div class="form-control">
				<label for="confirmPassword">Conferma Password</label> <input
					type="password" id="confirmPassword" name="confirmPassword"
					placeholder="Confirm password" />
					<span id="confirmPasswordError"></span>
			</div>
			</div>
			
		<div class="row">
		<div class="form-control">
			<label for="firstName">Nome</label> <input type="text"
				id="Nome" name="Nome" placeholder="Enter first name" /> 
				<span id="firstNameError"></span>
				</div>
		<div class="form-control">
			<label for="Cognome">Cognome</label> <input type="text"
				id="Cognome" name="Cognome" placeholder="Enter last name" /> 
				<span id="lastNameError"></span>
				</div>
		</div>
		<div class="row">
		<div class="form-control">
				<label for="Address">Indirizzo</label> <input type="text"
				id="Indirizzo" name="address" placeholder="Enter address" /> 
				<span id="addressError" style=></span>
				</div>		
		<div class="form-control">
		        <label for="Codice Fiscale">Codice Fiscale</label> <input type="text"
		        id="CF" name="CF" placeholder="Enter codice fiscale" />
		        <span id="CFError"></span>
		        </div>
		        </div>
		<div class="row">
		<div class="form-control">
			<label for="dataNascita">Data di Nascita</label> <input type="date"
				id="dataNascita" name="dataNascita"
				placeholder="Enter data di nascita" />
				<span id="dataNascitaError"></span>
		</div>
		<div class="form-control">
			<label for="telefono">Telefono</label> <input type="tel"
				id="telefono" name="telefono" placeholder="Enter telefono" />
				<span id="telefonoError"></span>
		    </div>
		    </div>
		    </fieldset>
		    <input type="hidden" name="ctoken" value="<%=session.getAttribute("ctoken")%>" />
			<button type="submit" id="Submit" class="actionBtn">Registrati</button>
			</form>
		</div>
	<%	if(request.getAttribute("Messaggio") != null) { %>
	<div class="container" style="">
		<div class="alert alert-danger" role="alert">
			<%=(String) request.getAttribute("Messaggio")%>
		</div>
	</div>
	<% } %>
</body>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="./resources/js/validation.js" type="text/javascript"></script>
	
	
	<script>
	let hasErrors = false;
	document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('form').addEventListener('submit', function(event) {
            hasErrors = false;
            event.preventDefault();
        });
	});
	
	

            document.getElementById("username").addEventListener("focusout", function() {
            	//wait for the user to stop typing before making the request to the server to check if the username is available or not 
            	
         
            										
                var username = document.getElementById("username").value;
                var error = document.getElementById("usernameError");
                if (validateUsername(username)) {
                    $.ajax({
                        url: './verifyUsername&ctoken=' + $('#ctoken').val()',
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({ username: username }),
                        dataType: 'json',
                        success: function(response) {
                            if (response.available == false) {
                                document.getElementById("usernameError").textContent = "Username non disponibile";
                                hasErrors = true;
                            } else {
                                hasErrors = false;
                                document.getElementById("usernameError").textContent = "OK";
                            }
                        },
                        error: function() {
                            hasErrors = true;
                            document.getElementById("usernameError").textContent = "Errore di rete";
                        }
                    });
                
                } else { 
                    hasErrors = true; 
                    document.getElementById("usernameError").textContent = "Username non valido";
                }
                
            });
            
            function validateEm() {

                var email = document.getElementById("email").value;


                if (!validateEmail(email)) {
                	document.getElementById("emailError").textContent = "";
                	hasErrors = false;
                    
                	$.ajax({
                        url: './verifyEmail?ctoken=' + $('#ctoken').val(),
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({ email: email }),
                        dataType: 'json',
                        success: function(response) {
                            if (response.trim() === false) {
                                document.getElementById("emailError").textContent = "Email non disponibile";
                                hasErrors = true;
                            } else {
                                hasErrors = false;
                                document.getElementById("emailError").textContent = "OK";
                            }
                        },
                        error: function() {
                            hasErrors = true;
                            document.getElementById("emailError").textContent = "Errore di rete";
                        }
                    });
                	
                } else {
                	document.getElementById("emailError").textContent =  "Email non valida";
                    hasErrors = true; 
           
                }
            }
            
            document.getElementById("email").addEventListener("focusout", validateEm);
       

            document.getElementById("password").addEventListener("input", function() {
                var password = document.getElementById("password").value;
                if (validatePassword(password)) {
                    document.getElementById("passwordError").textContent = "La password deve: 1. contenere almeno 8 caratteri, 2. contenere almeno una lettera maiuscola, 3. contenere almeno un numero, 4. contenere almeno un carattere speciale";
                    hasErrors = true;
                    return;
                } else {
                    document.getElementById("passwordError").textContent = "OK";
                    hasErrors = false;
                }
            });

            document.getElementById("confirmPassword").addEventListener("input", function() {
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirmPassword").value;
                if (password != confirmPassword) {
                    document.getElementById("confirmPasswordError").textContent = "Le password non coincidono";
                    hasErrors = true;
                    return;
                } else {
                    document.getElementById("confirmPasswordError").textContent = "";
                    hasErrors = false;
                }
            });
            
            $('#Submit').on('click', function(event) {
                if (hasErrors) {
                    event.preventDefault(); 
                    window.location.href = "/ProjectTSW/register?Messaggio=Registrazione%20fallita:%20Campi%20non%20validi";
                    return;
                }

                const username = $('#username').val();
                const email = $('#email').val();
                const password = $('#password').val();
                const confirmPassword = $('#confirmPassword').val();
                const firstName = $('#Nome').val();
                const lastName = $('#Cognome').val();
                const address = $('#Indirizzo').val();
                const CF = $('#CF').val();
                const dataNascita = $('#dataNascita').val();
                const telefono = $('#telefono').val();
                
                let data = {
                	username: username,
                    email: email,
                    password: confirmPassword,
                    Nome: firstName,
                    Cognome: lastName,
                    Indirizzo: address,
                    CF: CF,
                    dataNascita: dataNascita,
                    telefono: telefono
                }

                $.ajax({
                    url: './register',
                    type: 'POST',
                    data: JSON.stringify(data),
                    dataType: 'json',
                    encode: true,
                    success: function(response) {
                        if (response.success) {
                            window.location.href = "/ProjectTSW/login?Messaggio=" + response.message;
                        } else {
                            window.location.href = "/ProjectTSW/register?Messaggio=" + response.message;
                            return;
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        window.location.href = "/ProjectTSW/register?Messaggio=" + textStatus + ": " + errorThrown;
                        console.error("Error submitting form:", textStatus, errorThrown);
                    }
                });
            });
	</script>	
</html>

