<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <h5>Ordine Confermato!</h5>
        <h6>Grazie per aver acquistato da Kits!</h6>
            <div class="col">
                <div class="main_slider_content" style="margin-top: 175px;">
                    
                    <section class="order">
                        <h1 class="order__title no-margin">Order summary</h1>
                        <svg id="svg-summary" width="24" height="24" viewBox="0 0 24 24"></svg>
                        <section class="order__sub-sections order__subtotal clearfix ">
                            <h2 class="order__subtitles no-margin">Subtotal</h2>
                            <table id="values" class="order__subtotal__table">
                                <tbody>
                                    <% for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) { %>
                                        <tr>
                                            <td class="first-row"><%= entry.getKey().getNome() %> (<%= entry.getValue() %> item<%= (entry.getValue() > 1 ? "s" : "") %>)</td>
                                            <td class="first-row" align="right">R$ <%= entry.getKey().getPrezzo() * entry.getValue() %></td>
                                        </tr>
                                    <% } %>
                                    <tr>
                                        <td>Spedizione</td>
                                        <td align="right">5.00</td>
                                    </tr>
                                    <tr class="values--discounts">
                                        <td>Sconto:</td>
                                        <td align="right">-R$ <%= scontoCoupon %></td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td class="values__total">Totale</td>
                                        <td class="values--total-price">R$ <%= totale + 5.00 %></td>
                                    </tr>
                                   
                                </tfoot>
                            </table>
                        </section>
                        <section class="order__sub-sections order__address">
                            <h2 class="order__subtitles no-margin">Delivery address</h2>
                            <p class="address--client no-margin"><%= ragioneSociale %></p>
                            <p class="address--street no-margin"><%= address.getVia() %></p>
                            <p class="address--region no-margin"><%= address.getCitta() %> - <%= address.getProvincia() %> IT</p>
                            <p class="address--zipcode no-margin">ZipCode: <%= address.getCAP() %></p>
                        </section> 
                    </section>
                </div>
            </div>
        </div>
    </div>


	<%@ include file="./generic_footer.jsp" %>
	
</html>
	
	
	
	
	
	
	
