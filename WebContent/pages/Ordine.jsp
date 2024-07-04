<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	List<Ordine> orders = (List<Ordine>) session.getAttribute("ordine");
    boolean hasOrders = (orders != null && !orders.isEmpty());
%>


<div class="main_slider">


    <h2 class="order-title">I Tuoi Ordini</h2>
    <c:if test="${hasOrders}">
        <table class="order-table">
            <thead>
                <tr>
                    <th>ID Ordine</th>
                    <th>Data</th>
                    <th>Stato</th>
                    <th>Prezzo Totale</th>
                    <th>Dettagli</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${ordine}">
                    <tr>
                        <td>${order.id}</td>
                        <td><fmt:formatDate value="${order.date}" pattern="dd-MM-yyyy" /></td>
                        <td>${order.status}</td>
                        <td>&euro; <fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="" /></td>
                        <td>
                            <button type="button" onclick="toggleOrderDetails('${order.id}')">Mostra Dettagli</button>
                        </td>
                    </tr>
                    <tr id="order-details-${order.id}" style="display: none;">
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
                                    <c:forEach var="item" items="${order.items}">
                                        <tr>
                                            <td>${item.productName}</td>
                                            <td>${item.quantity}</td>
                                            <td>&euro; <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${!hasOrders}">
        <p class="empty-orders">Non hai ordini.</p>
    </c:if>
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
</script>

<%@ include file="./generic_footer.jsp" %>