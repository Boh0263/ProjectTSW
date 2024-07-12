<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Modifica Profilo</title>
</head>



<body>
	<form action="UserController?action=update" method="post">
		<label for="Email">Email:</label>
		<input type="email" name="Email" value="${user.Email}"><br>
		
		<label for="Password">Password:</label>
		<input type="password" name="Password" value="${user.Password}"><br>
		
		<label for="Nome">Nome:</label>
		<input type="text" name="Nome" value="${user.Nome}"><br>
		
		<label for="Cognome">Cognome:</label>
		<input type="text" name="Cognome" value="${user.Cognome}"><br>
		
		<label for="CF">CF:</label>
		<input type="text" name="CF" value="${user.CF}"><br>
		
		<label for="Telefono">Telefono:</label>
		<input type="text" name="Telefono" value="${user.Telefono}"><br>
		
		<label for="Data_Nascita">Data di nascita:</label>
		<input type="text" name="Data_Nascita" value="${user.Data_Nascita}"><br>
		
		<%-- Questi stanno nella tabella indirizzo, come vanno presi? --%>
		<label for="Indirizzo_breve">Indirizzo:</label>
		<input type="text" name="Indirizzo_breve" value="${user.Indirizzo_breve}"><br>
		
		<label for="Cap">Cap:</label>
		<input type="text" name="Cap" value="${user.Cap}"><br>
		
		<label for="Citta">Città:</label>
		<input type="text" name="Citta" value="${user.Citta}"><br>
		
		<label for="Provincia">Provincia:</label>
		<input type="text" name="Provincia" value="${user.Provincia}"><br>
		
		<input type="hidden" name="ctoken" value="<%=session.getAttribute("ctoken")%>"/>
		
		<button type="submit" id="Submit" class="actionBtn">Aggiorna</button>
    </form>
	<form action="UserController?action=delete" method="post">
		<input type="submit" value="Elimina Account"/>
	</form>
</body>

<%-- confirm() apre una finestra di dialogo con due bottoni, OK e Cancel --%>
<script type="text/javascript">
	function confirmDelete() {
		return confirm("Sei sicuro di voler eliminare il tuo account? Questa azione è irreversibile.");
	}
</script>

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
		var username = document.getElementById("username").value;
		var error = document.getElementById("usernameError");
		if (validateUsername(username)) {
			$.ajax({
				url: './verifyUsername',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({ username: username }),
				dataType: 'json',
				success: function(response) {
					if (response.available === false) {
						document.getElementById("usernameError").textContent = "Username non disponibile";
						hasErrors = true;
					} else {
						hasErrors = false;
						document.getElementById("usernameError").textContent = "OK";
					}
				},
				error: function() {
					hasErrors = true;
					document.getElementById("usernameError").textContent = response.message;
				}
			});
		} else {
			hasErrors = true;
			document.getElementById("usernameError").textContent = username ? "Username non valido" : "";
		}
	});
	
	function validateEm() {
		var email = document.getElementById("email").value;
		if ( email  && !validateEmail(email)) {
			document.getElementById("emailError").textContent = "";
			hasErrors = false;
			$.ajax({
				url: './verifyEmail',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({ email: email }),
				dataType: 'json',
				success: function(response) {
					if (response === false) {
						document.getElementById("emailError").textContent = "Email non disponibile";
						hasErrors = true;
					} else {
						hasErrors = false;
						document.getElementById("emailError").textContent = "OK";
					}
				},
				error: function(response) {
					hasErrors = true;
					document.getElementById("emailError").textContent = response.message;
				}
			});
			} else {
				document.getElementById("emailError").textContent =  email ? "Email non valida" : "";
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
        const citta = $('#Citta').val();
        const CAP = $('#CAP').val();
        const provincia = $('#Provincia').val();
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
                citta: citta,
                CAP: CAP,
                provincia: provincia,
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