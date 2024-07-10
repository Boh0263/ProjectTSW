<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Prodotti</title>
<link rel="stylesheet" href="./resources/styles/adminsearch_style.css">
<script src="./resources/js/jquery-3.2.1.min.js"></script>
<script src="./resources/styles/bootstrap4/popper.js"></script>
<script src="./resources/styles/bootstrap4/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="admin_header.jsp" %>   <!--
	private String username;
    private String password;
    private String nome;
    private String cognome;
    private String CF;
    private String email;
    private String tipo;
    private String dataNascita;
    private String telefono;
    private Indirizzo indirizzo;*/ -->

<div class="container">
	<div class="search-bar">
        <input type="text" id="search-input" oninput="filterProducts()" placeholder="Cerca per nome prodotto...">
    </div>
    <div class="table-container">
        <table class="table table-striped table-bordered table-hover"  id="myTable">
            <thead class="thead-dark">
                <tr>
                    <th>Username</th>
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>CF</th>
                    <th>Email</th>
                    <th>Privilegi</th>
                    <th>Data di Nascita</th>
                    <th>N° telefono</th>
                    <th>Indirizzo</th>
                    <th colspan="3">Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
            Collection<?> userList = (Collection<?>) request.getAttribute("userAll");
            if(userList == null || userList.isEmpty()) { %>
                <tr><td colspan='10'>Nessun utente trovato!</td></tr>
            <%
            } else {
              Iterator<?> it = userList.iterator();
              while (it.hasNext()) {
                	Utente u = (Utente) it.next();
                %>
                <tr>
                    <td><%= u.getUsername() %></td>
                    <td><%= u.getNome() %></td>
                    <td><%= u.getCognome() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getCF() %></td>
                    <td><%= u.getTipo() %></td>
                    <td><%= u.getDataNascita() %></td>
                    <td><%= u.getTelefono() %></td>
                    <td><%= u.getIndirizzo().toString() %> </td>
                    <td><button class="btn btn-primary actionBtn" onclick="OPUser('<%= u.getUsername() %>')">OP</button></td>
                    <td><button class="btn btn-primary actionBtn" onclick="DEOPUser('<%= u.getUsername() %>')">DEOP</button></td>
                    <td><button class="btn btn-danger actionBtn" onclick="removeUser('<%= u.getUsername() %>');">Rimuovi</button></td>
                </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
</div>


<script type="text/javascript">
    function removeUser(username) {
        if (confirm("Sei sicuro di voler rimuovere l'utente " + username + "?" )) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "AdminControl", true);
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send("action=deleteUser&username=" + username);
            
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    location.reload();
                } else if (xhr.readyState == 4 && xhr.status != 200) {
                    alert("Errore nella rimozione dell'utente!");
                }
            }
        }
    }



<script type="text/javascript">
function filterUsers() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("search-input");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");

	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }
	  }
}

</script>


<%@ include file="admin_footer.jsp" %>
</body>
</html>

