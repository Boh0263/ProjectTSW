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
</html>

<%-- COALESCE restituisce il primo valore non nullo di una lista --%>
public void updateUser(User user) {
        String sql = "UPDATE users SET email = COALESCE(?, email), password = COALESCE(?, password),
						billing_address = COALESCE(?, billing_address), shipping_address = COALESCE(?, shipping_address) WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getBillingAddress());
            stmt.setString(4, user.getShippingAddress());
            stmt.setInt(5, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

<%-- confirm() apre una finestra di dialogo con due bottoni, OK e Cancel --%>
<script type="text/javascript">
	function confirmDelete() {
		return confirm("Sei sicuro di voler eliminare il tuo account? Questa azione è irreversibile.");
	}
</script>