<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@page import="java.util.*, model.Prodotto, model.Carrello" %>
	<%@include file="./generic_header.jsp"%>

	<%  
		Carrello cart = (Carrello) request.getAttribute("carrello");
		int counter = 0;
		if (cart != null) {
		Map<Prodotto,Integer> prodotti = cart.getProdotti();
		if (prodotti != null && !prodotti.isEmpty()) {
		counter = 0;	
	%>
	<style>
					.empty-cart {
    					color: black;
    					font-family: Arial, sans-serif;
    					text-align: center;
    					font-size: 2em; /* You can adjust this value to suit your needs */
						}
					@media screen and (max-width: 600px) {
    				.empty-cart {
        				font-size: 1.5em; /* Adjust this value for smaller screens */
				    		}
						}
	</style>
	<div class="main_slider cart-slider" style="background-image:url(./resources/static/images/slider_1_empty.jpg)">
		<div class="cart">
			<div class="row cart_row">
                <div class="col-md-8 cart_content">
                    <div class="cart_title">
                        <div class="row cart_row">
                            <div class="col cart_col"><h4><b>Shopping Cart</b></h4></div>
                            <fmt:formatNumber value="${cart.getTotalItems()}" type="number" />
            </div>
          </div>
        </div>    
        <%for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) { %>
        <div class="row cart_row border-top border-bottom">
            <div class="row cart_row main cart_main align-items-center">
              <div class="col-2 cart_col"><img class="img-fluid cart_img" src="<%= entry.getKey().getImg1() %>"></div>
              <div class="col cart_col">
                <div class="row cart_row text-muted"><%= entry.getKey().getClass().getSimpleName() %></div> <div class="row cart_row">[entry.key.nome]</div> </div>
              <div class="col cart_col">
                <a href="#">-</a><a href="#" class="border"><%= entry.getKey().getNome() %></a><a href="#">+</a>
              </div>
              <div class="col cart_col">
                &euro; <fmt:formatNumber value="<%= entry.getKey().getPrezzo()%>" type="currency" currencySymbol="" /> <span class="close cart_close">&#10005;</span>
              </div>
            </div>
        <%  counter+=1; } %> 
                    <div class="back-to-shop"><a href="./Catalogo.jsp?category=All">&leftarrow;</a><span class="text-muted">Ritorna al Catalogo</span></div>
                </div>
                <div class="col-md-4 summary cart_summary">
                    <div><h5><b>Summary</b></h5></div>
                    <hr>
                    <div class="row">
                        <div class="col" style="padding-left:0;">ITEMS <%= cart.getTotalItems() %></div>
                        <div class="col text-right">&euro; <%=cart.getTotalPrice() %></div>
                    </div>
                    <form method="post" action="./Carrello">
                        <p>SHIPPING</p>
                        <select>
                        <option class="text-muted" selected>Standard-Delivery- &euro;5.00</option>
                        <option class="text-muted">Express-Delivery- &euro;10.00</option>
                        </select>
                        <p>GIVE CODE</p>
                        <input id="code" placeholder="Enter your code">
                    
                    <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                        <div class="col">TOTAL PRICE</div>
                        <div class="col text-right">&euro; <%=cart.getTotalPrice() + 5.00 %></div>
                    </div>
					<button class="btn cart_btn" type="submit" name="action" value="proceedToCheckout">CHECKOUT</button>
                    </form>
                </div>
            </div>
            <% } else {
            	
            	%>
            	<div class="cart">
            	<p class="empty-cart">Il carrello è vuoto</p>
            	<div class="back-to-shop" ><a onclick="window.history.go(-1);">&leftarrow;</a><span class="text-muted">Ritorna al Catalogo</span></div>
            	</div>
            	<% }} else { %>
            	<div class ="cart">
            	<p class="empty-cart">Si è verificato un errore, riprovare più tardi.</p> <!-- TODO Stylesheet per empty-cart -->
             <div class="back-to-shop"><a onclick="window.history.go(-1);">&leftarrow;</a><span class="text-muted">Ritorna al Catalogo</span></div>
             </div>
             <% } %>
        </div>
	</div>
	<% if(cart != null) { %>
	<script type="text/javascript">
	document.getElementById('standardDelivery').addEventListener('change', updateTotalPrice);
	document.getElementById('expressDelivery').addEventListener('change', updateTotalPrice); 
	// Update the total price when the user adds or removes a product from the cart using the +/- buttons
	document.querySelectorAll('.plus').forEach(function(button) {
        button.addEventListener('click', function() {
            // use ajax to update the product quantity in the cart and then update the total price on the page
 
        });
    });

	function updateTotalPrice() {
	    var basePrice = cart.getTotalPrice();
	    var standardDeliveryCost = parseFloat(document.getElementById('standardDelivery').value);
	    var expressDeliveryCost = parseFloat(document.getElementById('expressDelivery').value);
	    var totalPrice = basePrice + standardDeliveryCost + expressDeliveryCost;
	    document.getElementById('totalPrice').textContent = '€ ' + totalPrice.toFixed(2);
	}
	</script>	
	<% } %>
		<%@ include file="./generic_footer.jsp" %>