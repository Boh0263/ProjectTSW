<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Modifica Profilo</title>
</head>
<body>
	<form action="UserController?action=update" method="post">
		<label for="email">Email:</label>
		<input type="email" name="email" value="${user.email}"><br>
		
		<label for="password">Password:</label>
		<input type="password" name="password" value="${user.password}"><br>
		
		<label for="billingAddress">Indirizzo di Fatturazione:</label>
		<input type="text" name="billingAddress" value="${user.billingAddress}"><br>
		
		<label for="shippingAddress">Indirizzo di Spedizione:</label>
		<input type="text" name="shippingAddress" value="${user.shippingAddress}"><br>
		
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