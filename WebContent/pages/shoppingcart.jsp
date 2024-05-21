<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@page import="java.util.*, model.Prodotto, model.Carrello" %>
	<%@include file="./generic_header.jsp"%>
	<link rel="stylesheet" href="./resources/styles/sc_styles.css" />
	<%  
		Carrello cart = (Carrello) request.getAttribute("carrello");
		if (cart != null){
		Map<Prodotto,Integer> prodotti = cart.getProdotti();
		if (prodotti != null){
	%>
	
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
         <% }} else { //TODO %>
                    <div class="back-to-shop"><a href="./Catalogo.jsp?category=All">&leftarrow;</a><span class="text-muted">Back to shop</span></div>
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
                        <select><option class="text-muted">Standard-Delivery- &euro;5.00</option></select>
                        <p>GIVE CODE</p>
                        <input id="code" placeholder="Enter your code">
                    
                    <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                        <div class="col">TOTAL PRICE</div>
                        <div class="col text-right">&euro; <%=cart.getTotalPrice() + 5.00d %></div> <!-- TODO Costi di spedizione -->
                    </div>
					<button class="btn cart_btn" type="submit" name="action" value="proceedToCheckout">CHECKOUT</button>
                    </form>
                </div>
               <% }} %>
            </div>
            
        </div>
	</div>
		
		<%@ include file="./generic_footer.jsp" %>