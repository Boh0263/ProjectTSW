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
<%@ include file="admin_header.jsp" %>  
<style>
    .order-table {
        width: 100%;
        border-collapse: collapse;
    }
    .order-table th, .order-table td {
        padding: 10px;
        border: 1px solid #ddd;
    }
    .order-table th {
        background-color: #f4f4f4;
    }
    .order-title {
        text-align: center;
        margin: 20px 0;
    }
    .empty-orders {
        text-align: center;
        font-size: 1.5em;
    }
    
</style>
<div class="container">
	<div class="search-bar">
        <input type="text" id="search-input" oninput="filterOrders()" placeholder="Cerca per ID Ordine...">
    </div>
    <div class="table-container">
        <table class="order-table table table-striped table-bordered table-hover" id="myTable">
            <thead>
                <tr>
                    <th onclick="sortBy(0)">ID Ordine <i id="sortIcon0" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(1)">Data <i id="sortIcon1" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(2)">Prezzo Totale <i id="sortIcon2" class="fa fa-sort"></i></th>
                    <th onclick="sortBy(3)">Stato <i id="sortIcon3" class="fa fa-sort"></i></th>
                    <th>Dettagli</th>
                    <th>Scarica PDF</th>
                </tr>
            </thead>
            <tbody>
                <%	
                	Collection<Ordine> orders = (Collection<Ordine>) request.getAttribute("ordAll");	
                if(orders != null && !orders.isEmpty()) { 
                    for (Ordine order : orders) {
                    Map<Prodotto, Integer> prodotti = order.getProdotti();
                %>
                    <tr class="order-row">
             			<td><%= order.getID() %></td>
                        <td><%= order.getData_Ordine() %></td>
                        <td>&euro; <%= new java.text.DecimalFormat("#,##0.00").format(order.getTotalPrice()) %></td>
                        <td><%= order.getStato() %></td>
                        <td>
                            <button type="button" onclick="toggleOrderDetails('<%= order.getID() %>')">Mostra Dettagli</button>
                        </td>
						<td>
						<button type="button" onclick="downloadPDF('<%=order.getID()%>')">Scarica PDF</button>
						</td>
			</tr>
                    <tr id="order-details-<%= order.getID() %>" style="display: none;">
                        <td colspan="5">
                            <table class="order-table table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Prodotto</th>
                                        <th>Quantità</th>
                                        <th>Prezzo</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
                                         Prodotto prodotto = entry.getKey();
                                         int quantity = entry.getValue();
                                    %>
                                        <tr>
                                            <td><%= prodotto.getNome() %></td>
                                            <td><%= quantity %></td>
                                            <td>&euro; <%= new java.text.DecimalFormat("#,##0.00").format(prodotto.getPrezzo()) %></td>
                                        </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </td>
                    </tr>
					<%
                    }
                } else {
                	%>
					<tr>
						<td colspan='6' class="empty-orders">Nessun ordine trovato!</td>
					</tr>
					<%}
                %>
            </tbody>
        </table>
    </div>
</div>


<script type="text/javascript">

function filterOrders() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("search-input");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByClassName("order-row");


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
        
        toggleAllOrderDetailsOff(); 
        
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
    
    function toggleOrderDetails(orderId) {
    	toggleAllOrderDetailsOff(orderId);
    var detailsRow = document.getElementById('order-details-' + orderId);
    if (detailsRow.style.display === 'none') {
        detailsRow.style.display = 'table-row';
    } else {
        detailsRow.style.display = 'none';
    }
}
    
    function toggleAllOrderDetailsOff(exceptOrderId) {
        var detailsRows = document.querySelectorAll('[id^="order-details-"]');
        detailsRows.forEach(function(row) {
            if (!exceptOrderId || row.id !== 'order-details-' + exceptOrderId) {
                row.style.display = 'none';
            }
        });
    }
    

</script>

<script>

function downloadPDF(ID) {
    

    const xhr = new XMLHttpRequest();
    xhr.open('POST', 'PDF', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.responseType = 'blob';

    xhr.onload = function () {
        if (xhr.status === 200) {
            const blob = new Blob([xhr.response], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = 'order-' + ID + '.pdf';
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
        } else {
            alert('Errore nella generazione del PDF: ' + xhr.status);
        }
    };

    xhr.send('ID=' + encodeURIComponent(ID));
}

</script>


<%@ include file="admin_footer.jsp" %>
</body>
</html>

