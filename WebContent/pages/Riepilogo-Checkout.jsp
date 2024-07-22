<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="model.*, java.util.*" %>    
<!DOCTYPE html>
<html>

<%@include file="./generic_header.jsp"%>

<%

Ordine ordine = (Ordine) request.getAttribute("ordine");
double subTotal = ordine.getSubTotalPrice();
double scontoCoupon = ordine.getScontoCoupon();
double totale = ordine.getTotalPrice();
String ragioneSociale = ordine.getRagione_Sociale();
Indirizzo address = ordine.getAddress();

Map<Prodotto, Integer> prodotti = ordine.getProdotti();


%>

    <div class="container fill_height">
        <div class="row align-items-center fill_height">
        
            <div class="col">
                <div class="main_slider_content" style="margin-top: 175px;">
                    <h2>Ordine Confermato!</h2>
        			<h4>Grazie per aver acquistato su Kits!</h4>
                    <section class="order justify-center">
                        <div class="order__title no-margin" style="font-weight: bold;">Order summary</div>
                        <svg id="svg-summary" width="24" height="24" viewBox="0 0 24 24"></svg>
                        <section class="order__sub-sections order__subtotal clearfix ">
                            <div class="order__subtitles no-margin">Subtotal</div>
                            <table id="values" class="order__subtotal__table">
                                <tbody>
                                    <% for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) { %>
                                        <tr>
                                            <td class="first-row"><%= entry.getKey().getNome() %> (<%= entry.getValue() %> item<%= (entry.getValue() > 1 ? "s" : "") %>)</td>
                                            <td class="first-row" align="right"><fmt:formatNumber value="<%= entry.getKey().getPrezzo() * entry.getValue() %>" type="number" pattern="0.00" groupingUsed="false"/> &euro;</td>
                                        </tr>
                                    <% } %>
                                    <tr>
                                        <td>Spedizione</td>
                                        <td align="right"><fmt:formatNumber value="5.00" type="number" pattern="0.00" groupingUsed="false"/> &euro;</td>
                                    </tr>
                                    <tr class="values--discounts">
                                        <td>Sconto:</td>
                                        <td align="right">-<fmt:formatNumber value="<%= scontoCoupon %>" type="number" pattern="0.00" groupingUsed="false"/> &euro;</td>
                                    </tr>
                                    <tr>
                                        <td class="values__total">Totale</td>
                                        <td class="values--total-price" style="font-weight: bold;"><fmt:formatNumber value="<%= totale + 5.00 - scontoCoupon %>" type="number" pattern="0.00" groupingUsed="false"/> &euro;</td>
                                    </tr>
                                    
                                </tbody>
                        
                            </table>
                        </section>
                        <section class="order__sub-sections order__address">
                            <div class="order__subtitles no-margin">Indirizzo di Spedizione</div>
                            <p class="address--client no-margin"><%= ragioneSociale %></p>
                            <p class="address--street no-margin"><%= address.getVia() %></p>
                            <p class="address--region no-margin"><%= address.getCitta() %> - <%= address.getProvincia() %> IT</p>
                            <p class="address--zipcode no-margin">CAP: <%= address.getCAP() %></p>
                        </section> 
                    </section>
                </div>
            </div>
        </div>
    </div>


	<%@ include file="./generic_footer.jsp" %>
	
</html>
	
	
	
	
	
	
	
