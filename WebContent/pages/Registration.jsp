<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href="../resources/styles/registration_style.css">
    <title>Registrazione</title>
  </head>
  <body>
    <div class="container">
    <form id="form" action="./registration" method="post" class="form">
        <div class="form-control">
          <label for="username">Username</label>
          <input type="text" id="username" name="username" placeholder="Enter username" />
          <small id="usernameError">Error message</small>
        </div>
        <div class="form-control">
          <label for="email">Email</label>
          <input type="text" id="email" name="email" placeholder="Enter email" />
          <small id="emailError">Error message</small>
        </div>
        <div class="form-control">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter password" /> <small
					id="passwordError">Error message</small>
			</div>
        <div class="form-control">
				<label for="confirmPassword">Confirm Password</label> <input
					type="password" id="confirmPassword" name="confirmPassword"
					placeholder="Confirm password" /> <small id="confirmPasswordError">Error
					message</small>
			</div>
		<div class="form-control">
			<label for="firstName">First Name</label> <input type="text"
				id="Nome" name="Nome" placeholder="Enter first name" /> <small
				id="firstNameError">Error message</small>
				</div>
				<div class="form-control">
			<label for="Cognome">Last Name</label> <input type="text"
				id="Cognome" name="Cognome" placeholder="Enter last name" /> <small
				id="lastNameError">Error message</small>
				</div>
				<div class="form-control">
				<label for="Address">Address</label> <input type="text"
				id="Indirizzo" name="address" placeholder="Enter address" /> <small
				id="addressError">Error message</small>
				</div>
				<div class="form-control">
		        <label for="Codice Fiscale">Codice Fiscale</label> <input type="text"
		            id="CF" name="CF" placeholder="Enter codice fiscale" /> <small
		            id="codiceFiscaleError">Error message</small>
		            </div>
		<div class="form-control">
			<label for="dataNascita">Data di Nascita</label> <input type="text"
				id="dataNascita" name="dataNascita"
				placeholder="Enter data di nascita" /> <small id="dataNascitaError">Error
				message</small>
		</div>
		<div class="form-control">
			<label for="telefono">Telefono</label> <input type="text"
				id="telefono" name="telefono" placeholder="Enter telefono" /> <small
				id="telefonoError">Error message</small>
		    </div>
			<button type="button" id="Submit" class="actionBtn">Register</button>
			</form>
		</div>
		</body>
		<script>
		
			document.getElementById("username").addEventListener("input", function() {
                var username = document.getElementById("username").value;
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "./verifyUsername?username=" + username, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        var response = JSON.parse(xhr.responseText);
                        if (response.available == false) {
                            document.getElementById("usernameError").textContent = "Username non disponibile";
                        } else {
                            document.getElementById("usernameError").textContent = "";
                        }
                    }
                }
                xhr.send();
            });
            document.getElementById("email").addEventListener("input", function() {
            	//i need to check the email after being in a valid format
			var email = document.getElementById("email").value;
            	//check if the email is in a valid format first
                	if (/*email evaluation pietro*/){
								var xhr = new XMLHttpRequest();
								xhr.open("GET", "./verifyEmail?email=" + email,
										true);
								xhr.onreadystatechange = function() {
									if (xhr.readyState == 4
											&& xhr.status == 200) {
										var response = JSON
												.parse(xhr.responseText);
										if (response.available == false) {
											document.getElementById("emailError").textContent = "Email già registrata";
										} else {
											document.getElementById("emailError").textContent = "";
										}
									}
								}
								xhr.send();
                	}else{
                		document.getElementById("emailError").textContent = "Email non valida";
                	}
			});
            
            document.getElementById("password").addEventListener("input", function() {
                var password = document.getElementById("password").value;
                if (/*valuation pietro*/) {
                    document.getElementById("passwordError").textContent = "La password deve: 1. contenere almeno 8 caratteri, 2. contenere almeno una lettera maiuscola, 3. contenere almeno un numero, 4. contenere almeno un carattere speciale";
                } else {
                    document.getElementById("passwordError").textContent = "OK";
                }
            });
            
            }
			
	    </script>
</html>

