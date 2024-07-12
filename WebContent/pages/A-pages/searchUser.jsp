<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Utenti</title>
<link rel="stylesheet" href="./resources/styles/adminsearch_style.css">
<script src="./resources/js/jquery-3.2.1.min.js"></script>
<script src="./resources/styles/bootstrap4/popper.js"></script>
<script src="./resources/styles/bootstrap4/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="admin_header.jsp" %>   

<div class="container">
	<div class="search-bar">
        <input type="text" id="search-input" oninput="filterUsers()" placeholder="Cerca per username...">
    </div>
    <div class="table-container">
        <table class="table table-striped table-bordered table-hover"  id="myTable">
            <thead class="thead-dark">
                <tr>
                    <th onclick="sortBy(0)">Username <i id="sortIcon0" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(1)">Nome <i id="sortIcon1" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(2)">Cognome <i id="sortIcon2" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(3)">CF <i id="sortIcon3" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(4)">Email <i id="sortIcon4" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(5)">Privilegi <i id="sortIcon5" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(6)">Data di Nascita <i id="sortIcon6" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(7)">N° telefono <i id="sortIcon7" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(8)">Indirizzo <i id="sortIcon8" class="fa fa-sort"></i></th>
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
            xhr.send("action=deleteUser&username=" + username );
            
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    location.reload();
                } else if (xhr.readyState == 4 && xhr.status != 200) {
                    alert("Errore nella rimozione dell'utente!");
                }
            }
        }
    }
    
    function OPUser(username) {
    	        if (confirm("Sei sicuro di voler dare i privilegi di OP all'utente " + username + "?" )) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "AdminControl", true);
            xhr.setRequestHeader("Content-type", "application/json");
          	
            xhr.send(JSON.stringify({
            	action: "editUser",
          		inneraction: "OP",
          		username: username
          		}
          	));
            
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    location.reload();
                } else if (xhr.readyState == 4 && xhr.status != 200) {
                    alert("Errore nell'assegnazione dei privilegi!"); // da rimpiazzare on banner ( ho scoperto che i banner di notifica sono spesso chiamati snackbar, ne ho trovato un fatto interamente in css).
                }
            }
        }
     }
    
    function DEOPUser(username) {
    	        if (confirm("Sei sicuro di voler rimuovere i privilegi di OP all'utente " + username + "?" )) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "AdminControl", true);
            xhr.setRequestHeader("Content-type", "application/json");
          	
            xhr.send(JSON.stringify({
            	action: "editUser",
          		inneraction: "DEOP",
          		username: username }
            ));
            
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    location.reload();
                } else if (xhr.readyState == 4 && xhr.status != 200) {
                    alert("Errore nella rimozione dei privilegi!"); // da rimpiazzare con un banner popup (snackbar, vedi sopra).
                }
            }
        }
     }
    
    
</script>

<script type="text/javascript">

function filterUsers() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("search-input");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");

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

<script type="text/javascript">

    var currentSortColumn = -1; 
    var currentSortOrder = {}; 
    
    function sortBy(column) {
        var table, rows, switching, i, x, y, shouldSwitch;
        table = document.getElementById("myTable");
        switching = true;
        
        if (!currentSortOrder[column]) {
            currentSortOrder[column] = 'asc';
        } else {
            currentSortOrder[column] = currentSortOrder[column] === 'asc' ? 'desc' : 'asc';
        }
        
        if (currentSortColumn !== column) {
            resetSortIcons();
        }
        
        toggleSortIcon(column, currentSortOrder[column]);
        
        currentSortColumn = column; 
        
        while (switching) {
            switching = false;
            rows = table.rows;

            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;

                x = rows[i].getElementsByTagName("td")[column];
                y = rows[i + 1].getElementsByTagName("td")[column];

                if (currentSortOrder[column] === 'asc') {
                    if (x.textContent.toLowerCase() > y.textContent.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (currentSortOrder[column] === 'desc') {
                    if (x.textContent.toLowerCase() < y.textContent.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
            }
        }
    }
    
    function resetSortIcons() {
        for (var key in currentSortOrder) {
            if (currentSortOrder.hasOwnProperty(key)) {
                document.getElementById('sortIcon' + key).className = 'fa fa-sort';
            }
        }
    }
    
    function toggleSortIcon(column, sortOrder) {
        var icon = document.getElementById('sortIcon' + column);
        
        icon.classList.remove('fa-sort', 'fa-sort-up', 'fa-sort-down');
        if (sortOrder === 'asc') {
            icon.classList.add('fa-sort-up');
        } else if (sortOrder === 'desc') {
            icon.classList.add('fa-sort-down');
        } else {
            icon.classList.add('fa-sort');
        }
    }
</script>




<%@ include file="admin_footer.jsp" %>
</body>
</html>

