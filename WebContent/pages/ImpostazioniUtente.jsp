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
</html>