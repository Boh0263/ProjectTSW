<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.*, model.Prodotto, model.Ordine, model.OrdineDAOImp, model.ProdottoDAOImp, java.lang.Integer" %>

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

<%@ include file="./generic_header.jsp" %>

<%
	Collection<Ordine> orders = (Collection<Ordine>) session.getAttribute("ordini");
    boolean hasOrders = (orders != null && !orders.isEmpty());
%>

<div class="main_slider">

    <h2 class="order-title">I Tuoi Ordini</h2>
    <% if (hasOrders) { %>
        <table class="order-table">
            <thead>
                <tr>
                    <th>Numero Ordine</th>
                    <th>Data</th>
                    <th>Prezzo Totale</th>
                    <th>Stato</th>
                    <th>Dettagli</th>
                    <th>Scarica PDF</th>
                </tr>
            </thead>
            <tbody>
                <%	
                	
                    for (Ordine order : orders) {
                    Map<Prodotto, Integer> prodotti = order.getProdotti();
                %>
                    <tr>
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
                            <table class="order-table">
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
                %>
            </tbody>
        </table>
    <% } else { %>
        <p class="empty-orders">Non hai ordini.</p>
    <% } %>
</div>

<script>
function toggleOrderDetails(orderId) {
    var detailsRow = document.getElementById('order-details-' + orderId);
    if (detailsRow.style.display === 'none') {
        detailsRow.style.display = 'table-row';
    } else {
        detailsRow.style.display = 'none';
    }
}

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
            alert('Error generating PDF: ' + xhr.status);
        }
    };

    xhr.send('ID=' + encodeURIComponent(ID));
}

</script>

<%@ include file="./generic_footer.jsp" %>