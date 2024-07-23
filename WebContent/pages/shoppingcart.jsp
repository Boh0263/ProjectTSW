<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*, model.SanitizeInput, model.Prodotto, model.Carrello, model.ProdottoDAOImp, java.lang.Integer" %>
<%@ include file="./generic_header.jsp" %>

<%
    Carrello cart = (Carrello) session.getAttribute("carrello");
    boolean isLoggedIn = (cart != null);
    int counter = 0;
%>

<style>
    .empty-cart {
        color: black;
        font-family: Arial, sans-serif;
        text-align: center;
        font-size: 2em;
    }
    @media screen and (max-width: 600px) {
        .empty-cart {
            font-size: 1.5em;
        }
    }
</style>

<div class="main-slider cart-slider" style="background-image:url(./resources/static/images/slider_1_empty.jpg)">
	<div class="row " style="margin-top: 175px;">			
    <div class="cart">
        <div class="row cart_row m_row">
            <div class="col_md_8 cart_content">
                <div class="cart_title">
                    <div class="row cart_row border-top">
                        <div class="col cart_col"><h4><b>Shopping Cart</b></h4></div>
                        <% if (isLoggedIn) { %>
                            <fmt:formatNumber value="<%=cart.getTotalItems()%>" type="number" />
                        <% } %>
                    </div>
                </div>
            <% if (isLoggedIn) {
       
                    Map<Prodotto, Integer> prodotti = cart.getProdotti();
                    if (prodotti != null && !prodotti.isEmpty()) {
                        for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) { 
                            counter++;
                %>
                            <div class="row cart_row border-top border-bottom">
                                <div class="row cart_row main cart_main align-items-center">
                                    <div class="col-2 cart_col"><img class="img-fluid cart_img" src="./image?img-id=<%= entry.getKey().getImg1() %>"></div>
                                    <div class="col cart_col">
                                        <div class="row cart_row text-muted"><%= entry.getKey().getClass().getSimpleName() %></div>
                                        <div class="row cart_row"><%= entry.getKey().getNome() %></div>
                                    </div>
                                    <div class="col cart_col">
                                    
                                        <form action="./Carrello?action=removeProduct" method="post" class="remove-form" style="display: contents;">
                                        <input type="hidden" name="prodotto" value="<%= entry.getKey().getNome() %>" />
                                        <a href="#">-</a>
                                        </form>
                                        <a href="#" class="border"><%= entry.getValue() %></a>
                                        <form action="./Carrello?action=addProduct" method="post" class="add-form" style="display: contents;">
                                        <input type="hidden" name="prodotto" value="<%=entry.getKey().getNome()%>" /> 
                                        <a href="#">+</a>
                                        </form>
                                    </div>
                                    <div class="col cart_col">
                                        &euro; <fmt:formatNumber value="<%= entry.getKey().getPrezzo() %>" type="currency" currencySymbol="" /> <form action="./Carrello?action=removeProduct" method="post" class="remove-form" style="display: contents;"><input type="hidden" name="prodotto" value="<%= entry.getKey().getNome() %>" /><span class="close cart_close" >&#10005;</span></form>
                                    </div>
                                </div>
                            </div>
                        <% } %>
                        <div class="back-to-shop"><a href="./Catalogo?category=All">&leftarrow;</a><span class="text-muted">Ritorna al Catalogo</span></div>
                        </div> 
                        <div class="col-md-4 summary cart_summary">
                    <div><h5><b>Summary</b></h5></div>
                    <hr>
                    <div class="row">
                        <div class="col" style="padding-left:0;">ITEMS <%= cart.getTotalItems() %></div>
                        <div class="col text-right">&euro; <%= cart.getTotalPrice() %></div>
                    </div>
             
                        <p>SHIPPING</p>
                        <select>
                            <option class="text-muted" selected>Standard-Delivery- &euro;5.00</option>
                        </select>
                        <p>GIVE CODE</p>
                        <input id="code" placeholder="Enter your code">
                        <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                            <div class="col">TOTAL PRICE</div>
                            <div class="col text-right">&euro; <%= cart.getTotalPrice() + 5.00 %></div>
                        </div>
                        <div id="paypal-button-container">
                        <form id="paypal-form" method="post" action="./checkout">
                        <input type="hidden" name="dettagli" id="dettagli" />
                        </form>
                        </div>
                </div>
                <script src="https://www.paypal.com/sdk/js?client-id=ARuxkGGMcp-DnzCdEDC5BM08qw8Pl9D-rkFDJNGSsZ0mtzj8IevxrwyLl3RbKcXGKOuw-tbIn4I3iKm1&components=buttons&currency=EUR"></script>
   <script type="text/javascript">
    paypal.Buttons({
        createOrder: function(data, actions) {
           
        	<%
        	Carrello finalcart = (Carrello) request.getSession(false).getAttribute("carrello");
        	
        	if (cart == null || cart.getProdotti().isEmpty()) {
    			response.getWriter().write("Cart is empty");
    			return;
    		}
     
        	%>
			var total = <%= finalcart.getTotalPrice() %>;
			var totalWithShipping = <%= finalcart.getTotalPrice() + 5.00 %>;
            // Costruisci l'oggetto di creazione dell'ordine da passare a actions.order.create
            return actions.order.create({
                purchase_units: [{
                    amount: {
                        value: totalWithShipping.toFixed(2),
                        currency_code: "EUR",
                        breakdown: {
                            item_total: {
                                currency_code: "EUR",
                                value: total.toFixed(2)
                            },
                            
                            shipping: {
                                  currency_code: "EUR",
                                  value: "5.00"
                             }
                        }
                    },
                    items: [
                        <% 
                       
                        Prodotto temp = null;
                        Integer quantity;
        				for(Map.Entry<Prodotto, Integer> entry : cart.getProdotti().entrySet()) {
        	 			temp = entry.getKey();
                        quantity = entry.getValue();
                        String nomeProdottoEscaped = SanitizeInput.sanitize(temp.getNome());
                        
                        %> 
                         {
                            name: '<%= nomeProdottoEscaped %>',
                            unit_amount: {
                                currency_code: "EUR",
                                value: <%=temp.getPrezzo()%>
                            },
                            quantity: <%= quantity %> 
                            },
                         
                        <% } %>
        
               ] }]
            
            });
     
        },

        onApprove: function(data, actions) {
            return actions.order.capture().then(function(details) {
                // Imposta i dettagli dell'ordine nel campo nascosto del form
                document.getElementById('dettagli').value = JSON.stringify(details);
                document.getElementById('paypal-form').submit();
            });
        }
    }).render('#paypal-button-container');
</script>
                 
    <% } else { %>
                        <p class="empty-cart">Il carrello è vuoto</p>
                        </div>
                        </div>
                        </div>
                        </div>
                    <% } %>
               
                  
                   
                <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
                <script type="text/javascript" src="./resources/js/cart-custom.js"></script>
                <script>
                $(document).on('click', '.cart_close', function(){ 
        	        var form = $(this).closest('form');
        	        var url = form.attr('action');
        	        var data = form.serialize() + "&ctoken=" + encodeURIComponent('<%=session.getAttribute("ctoken")%>');
        	        console.log('Removing product from cart:', data); // Debug log
        	        $.ajax({
        	            type: "POST",
        	            url: url,
        	            data: data,
        	            success: function(response) {
        	                showBanner('Prodotto rimosso dal carrello', 'success');
        	                getCartCounterFromSession();
        	               
        	                location.reload();
        	                
        	            },
        	            error: function(xhr, status, error) {
        	                showBanner('Errore, prodotto non rimosso', 'error');
        	                console.error("Error: " + status + " " + error);
        	            }
        	        });
        	    });
                
                $(document).on('click', '.remove-form a', function(){ 
        	        var form = $(this).closest('form');
        	        var url = form.attr('action');
        	        var data = form.serialize() + "&ctoken=" + encodeURIComponent('<%=session.getAttribute("ctoken")%>');
        	        console.log('Removing product from cart:', data); // Debug log
        	        $.ajax({
        	            type: "POST",
        	            url: url,
        	            data: data,
        	            success: function(response) {
        	                showBanner('Prodotto rimosso dal carrello', 'success');
        	                getCartCounterFromSession();
        	               
        	                location.reload();
        	                
        	            },
        	            error: function(xhr, status, error) {
        	                showBanner('Errore, prodotto non rimosso', 'error');
        	                console.error("Error: " + status + " " + error);
        	            }
        	        });
        	    });
                
                $(document).on('click', '.add-form a', function(){
                	var form = $(this).closest('form');
        	        var url = form.attr('action');
        	        var data = form.serialize() + "&ctoken=" + encodeURIComponent('<%=session.getAttribute("ctoken")%>');
        	        $.ajax({
        	            type: "POST",
        	            url: url,
        	            data: data,
        	            success: function(response) {
        	                showBanner('Prodotto aggiunto al carrello', 'success');
        	                getCartCounterFromSession();
        	                // Ricarica la pagina.
        	                location.reload();
        	                
        	            },
        	            error: function(xhr, status, error) {
        	                showBanner('Errore, prodotto non aggiunto', 'error');
        	                console.error("Error: " + status + " " + error);
        	            }
        	        });
        	    });
                	
                	
                	
                	
                	
                	
                	
       
                
                function getCartCounterFromSession() {
            		
            		$.ajax({
            						type : "GET",
            						url : "./ProdottoCount",
            						success : function(response) {
            							
            							localStorage.setItem('CartSCounter', response);
            							document.getElementById('checkout_items').innerHTML = localStorage.getItem('CartSCounter');
            							
            							if(localStorage.getItem('CartSCounter') == 0 || localStorage.getItem('CartSCounter') == null) {
            								document.getElementById('checkout_items').style.display = "none";
            						    } else {
            								document.getElementById('checkout_items').style.display = "block";
            							  }
            						},
            						error : function(xhr, status, error) {
            							console.error("Error: " + status + " " + error);
            						}
            					});
                }
                </script>
                
                
            <% } else if (!isLoggedIn) { %>
            	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="./resources/js/cart-custom.js"></script>
                <script type="text/javascript">
                $(document).ready(function() {
                	
                	function showCarrello() {
                    var cartData = localStorage.getItem('prodotti');
                    console.log('Retrieved cartData from localStorage:', cartData); // Debug log

                    if (cartData != null && cartData.length > 0) {
                        $.ajax({
                            url: 'Prodotto',
                            method: 'POST',
                            data: { keys: cartData },
                            success: function(prodotti) {
                                console.log('Received prodotti from server:', prodotti); // Debug log

                                if (prodotti && Object.keys(prodotti).length > 0) {
                                    var cartHtml = '';
                                    var totalItems = 0;
                                    var totalPrice = 0.0;
                                    Object.entries(prodotti).forEach(function([key, prodotto]) {
                                        console.log('Processing prodotto:', prodotto); // Debug log
                                        console.log(prodotto.quantita + " " + prodotto.Prezzo);

                                        totalItems += prodotto.quantita;
                                        totalPrice += prodotto.Prezzo * prodotto.quantita;
                                        
                                        console.log(totalPrice);

                                        cartHtml += `
                                            <div class="row cart_row border-top border-bottom prodotto-cart">
                                                <div class="row cart_row main cart_main align-items-center"> 
                                                    <div class="col-2 cart_col"><img class="img-fluid cart_img"` + `src=` + "./image?img-id=" + prodotto.img1 +  `></div>
                                                    <div class="col cart_col">
                                                        <div class="row cart_row text-muted">` + prodotto.categoria +  `</div>
                                                        <div class="row cart_row prod_nome">` + prodotto.Nome +  `</div>
                                                    </div>
                                                    <div class="col cart_col">
                                                        <a href="#" class="minus">-</a><a href="#" class="border">` + prodotto.quantita + `</a><a href="#" class="plus">+</a>
                                                    </div>
                                                    <div class="col cart_col">
                                                        &euro; ` + prodotto.Prezzo.toFixed(2) + `<span class="close cart_close">&#10005;</span>
                                                    </div>
                                                </div>
                                            </div>
                                        `;
                                      });

                                    $('.cart_content').html(cartHtml);
                                    $('.cart_title .cart_col').append(`<span>` + totalItems + `</span>`);
                                    $('.summary .row  .col .text-right').text('€ ' + totalPrice.toFixed(2));
                                    $('.summary .row .col #totalWithShipping').text('€ ' + (totalPrice + 5.00).toFixed(2));
                                    $('.cart_summary').remove();
                                    $('.row.cart_row.m_row').append(`
                                        <div class="col-md-4 cart_summary summary ">
                                            <div><h5><b>Summary</b></h5></div>
                                            <hr>
                                            <div class="row">
                                                <div class="col" style="padding-left:0;">ITEMS ` + totalItems + `</div>
                                                <div class="col text-right">&euro; ` + totalPrice.toFixed(2) + `</div>
                                            </div>
                                            <form method="post" action="./Carrello">
                                                <p>SHIPPING</p>
                                                <select>
                                                    <option class="text-muted" selected>Standard-Delivery- &euro;5.00</option>
                                                </select>
                                                <p>GIVE CODE</p>
                                                <input id="code" placeholder="Enter your code">
                                                <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                                                    <div class="col">TOTAL PRICE</div>
                                                    <div class="col text-right">&euro;` +  (totalPrice + 5.00).toFixed(2) + `</div>
                                                </div>
                                                <button class="btn cart_btn" type="submit" name="action" value="proceedToCheckout">CHECKOUT</button>
                                            </form>
                                        </div>
                                    `);
                                } else {
                                    $('.cart_content').html('<p class="empty-cart text-center">Il carrello è vuoto</p></div></div></div></div>');
                                }
                            },
                            error: function() {
                                $('.cart_content').html('<p class="empty-cart text-center">Si è verificato un errore, riprovare più tardi.</p></div></div></div></div>');
                            }
                        });
                    } else {
                        $('.cart_content').html('<p class="empty-cart text-center">Il carrello è vuoto</p></div></div></div></div>');
                    }
                }
                	showCarrello();
                    
                 //rimuovere il prodotto dal carrello correlato
                    $(document).on('click', '.cart_close', function() {
   
                    	//trova il piu vicino elemento cart_main e prendi il testo del prodotto (flaggato con classe prod_nome).
                    	var prodotto = $(this).closest(".cart_main").find(".prod_nome").text();
                    	console.log("Prodotto da rimuovere: " + prodotto); // Debug log
                    	
                    	//rimuovi il prodotto dal carrello locale (localStorage)
                    	var carrello = new Carrello();
                    	carrello.removeProduct(prodotto);
                    	//aggiorna il carrello.
                    	showCarrello();
                    
                    });
                    
                    
                    
                });
                </script>
                </div>
                </div>
                </div>
                </div>
               <div id="notification-container"></div>
            <% } %>
    </div>
    </div>
    </div>
    </div>
    
<div id="notification-container"></div>
    	

<%@ include file="./generic_footer.jsp" %>