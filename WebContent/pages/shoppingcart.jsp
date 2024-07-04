<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*, model.Prodotto, model.Carrello, model.ProdottoDAOImp, java.lang.Integer" %>
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

<div class="main_slider cart-slider" style="background-image:url(./resources/static/images/slider_1_empty.jpg)">
    <div class="cart">
        <div class="row cart_row">
            <div class="col-md-8 cart_content">
                <div class="cart_title">
                    <div class="row cart_row">
                        <div class="col cart_col"><h4><b>Shopping Cart</b></h4></div>
                        <% if (isLoggedIn) { %>
                            <fmt:formatNumber value="<%=cart.getTotalItems()%>" type="number" />
                        <% } %>
                    </div>
                </div>
            </div>
            <% if (isLoggedIn) { %>
                <% 
                    Map<Prodotto, Integer> prodotti = cart.getProdotti();
                    if (prodotti != null && !prodotti.isEmpty()) {
                        for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) { 
                            counter++;
                %>
                            <div class="row cart_row border-top border-bottom">
                                <div class="row cart_row main cart_main align-items-center">
                                    <div class="col-2 cart_col"><img class="img-fluid cart_img" src="<%= entry.getKey().getImg1() %>"></div>
                                    <div class="col cart_col">
                                        <div class="row cart_row text-muted"><%= entry.getKey().getClass().getSimpleName() %></div>
                                        <div class="row cart_row"><%= entry.getKey().getNome() %></div>
                                    </div>
                                    <div class="col cart_col">
                                        <a href="#">-</a><a href="#" class="border"><%= entry.getValue() %></a><a href="#">+</a>
                                    </div>
                                    <div class="col cart_col">
                                        &euro; <fmt:formatNumber value="<%= entry.getKey().getPrezzo() %>" type="currency" currencySymbol="" /> <span class="close cart_close">&#10005;</span>
                                    </div>
                                </div>
                            </div>
                        <% } %>
                        <div class="back-to-shop"><a href="./Catalogo.jsp?category=All">&leftarrow;</a><span class="text-muted">Ritorna al Catalogo</span></div>
                    <% } else { %>
                        <p class="empty-cart">Il carrello è vuoto</p>
                    <% } %>
                </div>
                <div class="col-md-4 summary cart_summary">
                    <div><h5><b>Summary</b></h5></div>
                    <hr>
                    <div class="row">
                        <div class="col" style="padding-left:0;">ITEMS <%= cart.getTotalItems() %></div>
                        <div class="col text-right">&euro; <%= cart.getTotalPrice() %></div>
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
                            <div class="col text-right">&euro; <%= cart.getTotalPrice() + 5.00 %></div>
                        </div>
                        <button class="btn cart_btn" type="submit" name="action" value="proceedToCheckout">CHECKOUT</button>
                    </form>
                </div>
            <% } else if (!isLoggedIn) { %>
            	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
                <script type="text/javascript" src="./resources/js/cart-custom.js"></script>
                <script type="text/javascript">
                $(document).ready(function() {
                    var cartData = localStorage.getItem('prodotti');
                    console.log('Retrieved cartData from localStorage:', cartData); // Debug log

                    if (cartData && cartData.length > 0) {
                        $.ajax({
                            url: './Prodotto',
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
                                            <div class="row cart_row border-top border-bottom">
                                                <div class="row cart_row main cart_main align-items-center"> 
                                                    <div class="col-2 cart_col"><img class="img-fluid cart_img" src=` + prodotto.img +  `></div>
                                                    <div class="col cart_col">
                                                        <div class="row cart_row text-muted">` + prodotto.categoria +  `</div>
                                                        <div class="row cart_row">` + prodotto.Nome +  `</div>
                                                    </div>
                                                    <div class="col cart_col">
                                                        <a href="#">-</a><a href="#" class="border">` + prodotto.quantita + `</a><a href="#">+</a>
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
                                    $('.summary .row .col.text-right').text('€ ' + totalPrice.toFixed(2));
                                    $('.summary .row .col#totalWithShipping').text('€ ' + (totalPrice + 5.00).toFixed(2));
                                    $('.cart').append(`
                                        <div class="col-md-4 summary cart_summary">
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
                                                    <option class="text-muted">Express-Delivery- &euro;10.00</option>
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
                                    $('.cart_content').html('<p class="empty-cart text-center">Il carrello è vuoto</p>');
                                }
                            },
                            error: function() {
                                $('.cart_content').html('<p class="empty-cart text-center">Si è verificato un errore, riprovare più tardi.</p>');
                            }
                        });
                    } else {
                        $('.cart_content').html('<p class="empty-cart text-center">Il carrello è vuoto</p>');
                    }
                });
                </script>
               
            <% } %>
        </div>
    </div>
</div>

<%@ include file="./generic_footer.jsp" %>