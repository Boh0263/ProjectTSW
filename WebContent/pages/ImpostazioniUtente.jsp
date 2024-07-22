<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Modifica Profilo</title>
    
</head>
<%
    Utente user = (Utente) request.getAttribute("user");
    Indirizzo indirizzo = user.getIndirizzo();
    if (user != null && session.getAttribute("username") != null && session.getAttribute("username").equals(user.getUsername())) {
%>
    <%@include file="generic_header.jsp" %> 

    <body>
     <div class="container" style="padding: 50px; margin-top: 175px;">
        <div class="card" style="border-style: none !important;">
            <form id="updateForm" action="Account" method="post" >
                <div class="form-group">
                    <label for="Email">Email:</label>
                    <input type="email" id="Email" name="Email" class="form-control" value="<%=user.getEmail()%>" required>
                    <small id="emailError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="Password">Password:</label>
                    <input type="password" id="Password" name="Password" class="form-control" placeholder="Inserire la nuova password qui..">
                    <small id="passwordError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="Nome">Nome:</label>
                    <input type="text" id="Nome" name="Nome" class="form-control" value="<%=user.getNome()%>" required>
                    <small id="nomeError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="Cognome">Cognome:</label>
                    <input type="text" id="Cognome" name="Cognome" class="form-control" value="<%=user.getCognome()%>" required>
                    <small id="cognomeError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="CF">CF:</label>
                    <input type="text" id="CF" name="CF" class="form-control" value="<%=user.getCF()%>" required>
                    <small id="cfError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="Telefono">Telefono:</label>
                    <input type="text" id="Telefono" name="Telefono" class="form-control" value="<%=user.getTelefono()%>">
                    <small id="telefonoError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="Data_Nascita">Data di nascita:</label>
                    <input type="text" id="Data_Nascita" name="Data_Nascita" class="form-control" value="<%=user.getDataNascita()%>" required>
                    <small id="dataNascitaError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="Indirizzo_breve">Indirizzo:</label>
                    <input type="text" id="Indirizzo_breve" name="Indirizzo_breve" class="form-control" value="<%=indirizzo.getVia()%>" required>
                    <small id="indirizzoError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="Cap">Cap:</label>
                    <input type="text" id="Cap" name="Cap" class="form-control" value="<%=indirizzo.getCAP()%>" required>
                    <small id="capError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="Citta">Città:</label>
                    <input type="text" id="Citta" name="Citta" class="form-control" value="<%=indirizzo.getCitta()%>" required>
                    <small id="cittaError" class="form-text text-danger"></small>
                </div>

                <div class="form-group">
                    <label for="Provincia">Provincia:</label>
                    <input type="text" id="Provincia" name="Provincia" class="form-control" value="<%=indirizzo.getProvincia()%>" required>
                    <small id="provinciaError" class="form-text text-danger"></small>
                </div>

                <input type="hidden" name="ctoken" value="<%=session.getAttribute("ctoken")%>"/>
               <div class="text-center"> 
                <button type="submit" id="Submit" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Aggiorna&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
                </div>
            </form>

            <form id="deleteForm" class="text-center" style="margin-top: 10px;" action="./Account?action=delete" method="post" onsubmit="return confirmDelete();">
                <input type="hidden" name="ctoken" value="<%=session.getAttribute("ctoken")%>"/>
                <input type="hidden" name="username" value="<%=user.getUsername()%>"/>
                <input type="submit" value="Elimina Account" class="btn btn-danger"/>
            </form>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="./resources/js/validation.js" type="text/javascript"></script>
	<script>
	
	$('#deleteForm').on('submit', function(event) {
        event.preventDefault(); 
		var form = $(this);
		var action = $(this).attr('action');
		var data = form.serialize();
        $.ajax({
            url: action,
            data: data,
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',

            success: function(response) {
                if (response.success) {
                    alert('Account eliminato con successo!');
                    window.location.href = '/ProjectTSW/login';
                } else {
                    alert('Errore durante l\'eliminazione dell\'account: ' + response.message);
                }
            },
            error: function(xhr, status, error) {
                alert('Errore AJAX: ' + error);
            }
        });
    });
	
	
	</script>
	<script>
	
	var initialEmail = '<%= user.getEmail() %>';
    var initialPassword = ''; 
    var initialNome = '<%= user.getNome() %>';
    var initialCognome = '<%= user.getCognome() %>';
    var initialCF = '<%= user.getCF() %>';
    var initialTelefono = '<%= user.getTelefono() %>';
    var initialDataNascita = '<%= user.getDataNascita() %>';
    var initialIndirizzo = '<%= indirizzo.getVia() %>';
    var initialCap = '<%= indirizzo.getCAP() %>';
    var initialCitta = '<%= indirizzo.getCitta() %>';
    var initialProvincia = '<%= indirizzo.getProvincia() %>';
	
	
	
	$('#updateForm').on('submit', function(event) {
        event.preventDefault(); 

        let hasErrors = false;
        var form = $(this);
        var action = $(this).attr('action');

        // Clear errori precedenti.
        $('.form-text.text-danger').text('');
        
         const email = $('#Email').val();
        const password = $('#Password').val();
        const nome = $('#Nome').val();
        const cognome = $('#Cognome').val();
        const cf = $('#CF').val();
        const telefono = $('#Telefono').val();
        const dataNascita = $('#Data_Nascita').val();
        const indirizzo = $('#Indirizzo_breve').val();
        const cap = $('#Cap').val();
        const citta = $('#Citta').val();
        const provincia = $('#Provincia').val();
        
        if (email !== initialEmail && !validateEmail(email)) {
            $("#emailError").text("Email non valida");
            hasErrors = true;
        }

        if (password != '' && password.length < 6) {
            $("#passwordError").text("La password deve essere di almeno 6 caratteri");
            hasErrors = true;
        }

        if (nome !== initialNome && !nome) {
            $("#nomeError").text("Nome è richiesto");
            hasErrors = true;
        }

        if (cognome !== initialCognome && !cognome) {
            $("#cognomeError").text("Cognome è richiesto");
            hasErrors = true;
        }

        if (cf !== initialCF && !cf) {
            $("#cfError").text("CF è richiesto");
            hasErrors = true;
        }

        if (telefono !== initialTelefono && !telefono) {
            $("#telefonoError").text("Telefono è richiesto");
            hasErrors = true;
        }

        if (dataNascita !== initialDataNascita && !dataNascita) {
            $("#dataNascitaError").text("Data di nascita è richiesta");
            hasErrors = true;
        }

        if (indirizzo !== initialIndirizzo && !indirizzo) {
            $("#indirizzoError").text("Indirizzo è richiesto");
            hasErrors = true;
        }

        if (cap !== initialCap && !cap) {
            $("#capError").text("CAP è richiesto");
            hasErrors = true;
        }

        if (citta !== initialCitta && !citta) {
            $("#cittaError").text("Città è richiesta");
            hasErrors = true;
        }

        if (provincia !== initialProvincia && !provincia) {
            $("#provinciaError").text("Provincia è richiesta");
            hasErrors = true;
        }

        if (hasErrors) {
            alert("Perfavore modifica i campi errati e riprova.");
            return false;
        }

        
       

       
 

        // Se non ci sono errori, preparo i dati da inviare al server in formato JSON (aggiungendo solo i campi modificati rispetto ai valori iniziali).
        
        let formData = {};
        formData.userName = '<%= user.getUsername() %>';
        if (email !== initialEmail) formData.userEmail = email;
        if (password !== '' && password !== initialPassword) formData.userNPassword = password;
        if (nome !== initialNome) formData.userNome = nome;
        if (cognome !== initialCognome) formData.userCognome = cognome;
        if (cf !== initialCF) formData.userCF = cf;
        if (telefono !== initialTelefono) formData.userTelefono = telefono;
        if (dataNascita !== initialDataNascita) formData.userDataNascita = dataNascita;
        if (indirizzo !== initialIndirizzo) formData.userVia = indirizzo;
        if (cap !== initialCap) formData.userCAP = cap;
        if (citta !== initialCitta) formData.userCitta = citta;
        if (provincia !== initialProvincia) formData.userProvincia = provincia;
        formData.action = 'update';
        formData.ctoken = '<%= session.getAttribute("ctoken") %>';

        $.ajax({
            url: 'Account',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                if (response.success) {
                    alert('Profilo aggiornato!');
                } else {
                    alert('Errore nell\' aggiornamento: ' + response.message);
                }
            },
            error: function(xhr, status, error) {
                alert('Errore AJAX: ' + error.message + ' ' + status);
            }
        });
    });


</script>
	
	

    <script>
        function confirmDelete() {
            return confirm("Sei sicuro di voler eliminare il tuo account? Questa azione è irreversibile.");
        }

        $(document).ready(function() {
         
            $('#updateForm').on('submit', function(event) {
                let hasErrors = false;

                
                
                if (hasErrors) {
                    event.preventDefault();
                    alert("Perfavore modifica i campi errati e riprova.");
                    return false;
                }
                
                
                return true;
            });

            $('#email').on('focusout', function() {
                var email = $(this).val();
                if (validateEmail(email)) {
                    $.ajax({
                        url: './verifyEmail',
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({ email: email }),
                        dataType: 'json',
                        success: function(response) {
                            if (!response.available) {
                                $("#emailError").text("Email non disponibile");
                            } else {
                                $("#emailError").text("OK");
                            }
                        },
                        error: function() {
                            $("#emailError").text("Errore nella verifica dell'email");
                        }
                    });
                } else {
                    $("#emailError").text("Email non valida");
                }
            });

            $('#username').on('focusout', function() {
                var username = $(this).val();
                if (validateUsername(username)) {
                    $.ajax({
                        url: './verifyUsername',
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({ username: username }),
                        dataType: 'json',
                        success: function(response) {
                            if (!response.available) {
                                $("#usernameError").text("Username non disponibile");
                            } else {
                                $("#usernameError").text("OK");
                            }
                        },
                        error: function() {
                            $("#usernameError").text("Errore nella verifica dello username");
                        }
                    });
                } else {
                    $("#usernameError").text("Username non valido");
                }
            });

            $('#Password, #ConfirmPassword').on('input', function() {
                var password = $('#Password').val();
                var confirmPassword = $('#ConfirmPassword').val();
                if (password !== confirmPassword) {
                    $("#confirmPasswordError").text("Le password non coincidono");
                } else {
                    $("#confirmPasswordError").text("OK");
                }
            });
        });
    </script>
    <% } %>
    <%@include file="generic_footer.jsp" %>
    </body>
</html>
