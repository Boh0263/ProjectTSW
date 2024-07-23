<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ page import="java.util.*, model.*" %>
	
<%@include file="./generic_header.jsp"%>
	
	<%
		

		boolean test_session = false;
		if (session.getAttribute("username") != null && !session.getAttribute("username").toString().isEmpty()) {
			test_session = true;
		}

		Prodotto product = null;
		if (request.getAttribute("prodotto") == null) {
		}
		product = (Prodotto) request.getAttribute("prodotto");
		%> 

    <div class="container single_product_container">
		<div class="row">
			<div class="col">

				<!-- Breadcrumbs -->

				<div class="breadcrumbs d-flex flex-row align-items-center">
					<ul>
						<li><a href="${pageContext.request.contextPath}/">Home</a></li>
						<li><a href="${pageContext.request.contextPath}/Catalogo?category=<%=product.getClass().getSimpleName()%>"><i class="fa fa-angle-right" ></i><%=product.getClass().getSimpleName()%></a></li> 
						<li class="active"><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i><%= product.getNome()%></a></li> 
					</ul>
				</div>

			</div>
		</div>

		<div class="row">
			<div class="col-lg-7">
				<div class="single_product_pics">
					<div class="row">
						<div class="col-lg-3 thumbnails_col order-lg-1 order-2">
							<div class="single_product_thumbnails">
								<ul>
									
									<li><img src="${pageContext.request.contextPath}/image?img-id=<%=product.getImg1()%>" data-image="${pageContext.request.contextPath}/image?img-id=<%=product.getImg1()%>" alt="<%=product.getNome()%>" ></li> <!-- NOMI DELLE FOTO 1-2-3 -->
									<li class="active"><img src="${pageContext.request.contextPath}/image?img-id=<%=product.getImg2()%>" data-image="${pageContext.request.contextPath}/image?img-id=<%=product.getImg2()%>" alt="<%=product.getNome()%>" ></li> <!-- NOMI DELLE FOTO 1-2-3 -->
									<li><img src="${pageContext.request.contextPath}/image?img-id=<%=product.getImg3()%>" data-image="${pageContext.request.contextPath}/image?img-id=<%=product.getImg2()%>" alt="<%=product.getNome()%>" ></li> <!-- NOMI DELLE FOTO 1-2-3 -->
								</ul>
							</div>
						</div>
						<div class="col-lg-9 image_col order-lg-2 order-1">
							<div class="single_product_image">
								<div class="single_product_image_background" style="background-image:url('${pageContext.request.contextPath}/image?img-id=<%=product.getImg2()%>')"></div> <!-- NOMI DELLA FOTO 2 DISPLAY DEFAULT -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-5">
				<div class="product_details">
					<div class="product_details_title">
						<h2><%=product.getNome()%></h2> 
					</div>
					<div class="original_price"><fmt:formatNumber value="<%=product.getPrezzo() + (product.getPrezzo() / 100 * 30)%>" type="number" pattern="0.00" groupingUsed="false"/>&euro;</div> 
					<div class="product_price"><fmt:formatNumber value="<%=product.getPrezzo()%>" type="number" pattern="0.00" groupingUsed="false"/>&euro;</div> 
					<ul class="star_rating"> 
						<li><i class="fa fa-star" aria-hidden="true"></i></li>
						<li><i class="fa fa-star" aria-hidden="true"></i></li>
						<li><i class="fa fa-star" aria-hidden="true"></i></li>
						<li><i class="fa fa-star" aria-hidden="true"></i></li>
						<li><i class="fa fa-star-o" aria-hidden="true"></i></li> 
					</ul> 
					<div class="quantity d-flex flex-column flex-sm-row align-items-sm-center">
					<div class="red_button carrello_button">
					<%if(test_session) { %>
					
					<form action="${pageContext.request.contextPath}/Carrello?action=addProduct" method="post" class="add_to_cart">
					<% } %>
						<button <% if(test_session){ %>type="submit" <% } else {%> id="cartb" <% } %>>Aggiungi al Carrello</button>
						<input type="hidden" name="prodotto" value="<%=product.getNome()%>">
						<%if(test_session) { %>
						</form>
						<% } %>
					</div>
						<div class="product_favorite d-flex flex-column align-items-center justify-content-center"></div> 
					</div>
					</div>
				</div>
			</div>
	</div>

	<!-- Tabs -->

	<div class="tabs_section_container">

		<div class="container">
			<div class="row">
				<div class="col">
					<div class="tabs_container">
						<ul class="tabs d-flex flex-sm-row flex-column align-items-left align-items-md-center justify-content-center">
							<li class="tab active" data-active-tab="tab_1"><span>Descrizione</span></li>
						
							<li class="tab" data-active-tab="tab_3"><span>Recensioni</span></li> <!-- RECENSIONI (DA CANCELLARE SE NON VOGLIAMO GESTIRLO ORA) -->
						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">


					<div id="tab_1" class="tab_container active">
						<div class="row">
							<div class="col-lg-5 desc_col">
								<div class="tab_title">
									<h4>Descrizione</h4>
								</div>
								<div class="tab_text_block">
									<h2><%=product.getNome()%></h2> <!-- STESSO NOME DEL PRODOTTO NEL TITOLO -->
									<p><%=product.getDescrizione()%></p> <!-- DESCRIZIONE DEFAULT PRODOTTO -->
								</div>
							</div>
							<div class="col-lg-5 offset-lg-2 desc_col">
								<div class="tab_image">
									<img src="${pageContext.request.contextPath}/image?img-id=<%=product.getImg2()%>" alt=""> 
								</div>
							</div>
						</div>
					</div>

					

					<div id="tab_3" class="tab_container">
						<div class="row">

							
							<div class="col-lg-6 reviews_col">
								<div class="tab_title reviews_title">
									<h4>Recensioni</h4>
								</div>

								<%
								Collection<Recensione> recensioni = request.getAttribute("recensioni") != null ? (Collection<Recensione>) request.getAttribute("recensioni") : new ArrayList<Recensione>();
	                            if (!recensioni.isEmpty()) {									
								for (Recensione recensione : recensioni) {
								
								%>

								<div class="user_review_container d-flex flex-column flex-sm-row">
									<div class="user">
										<div class="user_pic"></div>
										<div class="user_rating">
											<ul class="star_rating">
											<%for(int i = 1; i <= 5; i++) {
												if (i <= recensione.getVotazione()) { %>
											<li><i class="fa fa-star" aria-hidden="true"></i></li> 
											
										<% } else {	%><li><i class="fa fa-star-o" aria-hidden="true"></i></li>
												<% } } %>
								
											</ul>
										</div>
									</div>
									<div class="review">
										<div class="review_date"><%=recensione.getData_Recensione() %></div>
										<div class="user_name"><%=recensione.getEmail()%></div>
										<p><%=recensione.getCommento() %></p>
									</div>
								</div>
							<% } } %>
							</div>

							

							<div class="col-lg-6 add_review_col">

								<div class="add_review">
									<form id="review_form" action="post">
										<div>
											<h1>Aggiungi Recensione</h1>
											<input id="review_email" class="form_input input_email" type="email" name="email" placeholder="Email*" required="required" data-error="Inserisci una mail valida">
										</div>
										<div>
											<h1>Valutazione:</h1>
											<ul class="user_star_rating">
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
											</ul>
											<textarea id="review_message" class="input_review" name="message"  placeholder="Your Review" rows="4" required data-error="Perfavore, lasciaci una recensione."></textarea>
										</div>
										<div class="text-left text-sm-right">
											<button id="review_submit" type="submit" class="red_button review_submit_btn trans_300" value="Submit">Invia</button>
										</div>
									</form>
								</div>

							</div>

						</div>
					</div>

				</div>
			</div>
		</div>

	</div>
	<div id="notification-container"></div>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script> <!-- jQuery  normale --> 
	<script src="${pageContext.request.contextPath}/resources/plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script> <!-- jQuery UI -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/plugins/jquery-ui-1.12.1.custom/jquery-ui.css">
	<script src="${pageContext.request.contextPath}/resources/js/single_custom.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/cart-custom.js"></script>
    <script>
    
		function addToWishlist(productName) {

			var wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];

						if (!wishlist.includes(productName)) {
							wishlist.push(productName);
							console.log(wishlist);
							localStorage.setItem('wishlist', JSON.stringify(wishlist));

							console.log(productName + " aggiunto alla wishlist!");
						} else {

							console.log(productName+ " si trova già nella wishlist.");
						}
					}
		
		function removeFromWishlist(productName) {
			var wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];

            if (wishlist.includes(productName)) {
                wishlist = wishlist.filter(item => item !== productName);
                localStorage.setItem('wishlist', JSON.stringify(wishlist));

                console.log(productName + " rimosso dalla wishlist.");
            } else {

                console.log(productName + " non è presente nella wishlist.");
            }
		}
				</script>
				
<script>
    //SCRIPT PER LA GESTIONE DELLA WISHLIST (init fav, Aggiungi e Rimuovi dalla wishlist)
    $(document).ready(function() {
        var productName = '<%= product.getNome() %>';
        var wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];
        
        // Controllo del prodotto nella wishlist
        if (wishlist.includes(productName)) {
            $('.product_favorite').addClass('active');
        } 
        

        // Toggle
        $('.product_favorite').on('click', function() {
            $(this).toggleClass('active');
            var isActive = $(this).hasClass('active');
            if (isActive) {
                removeFromWishlist(productName);
                $(this).removeClass('active');
            } else {
                addToWishlist(productName);
                $(this).addClass('active');
            }
            
        });
      });
</script>
						
<script>
	//SCRIPT PER LA GESTIONE DELLA RECENSIONE.
	
	$(document).ready(function() {
    $('#review_form').on('submit', function(event) {
        event.preventDefault(); 

        var email = $('#review_email').val();

        var commento = $('#review_message').val();

        var Stelline = $('.user_star_rating li').length;
        var StellineVuote = $('.user_star_rating li .fa-star-o').length;
        var votazione = Stelline - StellineVuote;

        
        var data = {
            action: 'POST',
            Email: email,
            Commento: commento,
            Votazione: votazione,
            Prodotto: '<%=product.getNome()%>'
        };

        
        $.ajax({
            url: '${pageContext.request.contextPath}/review', 
            type: 'POST',
            data: $.param(data), // Un altro modo in jquery di passare i parametri da formato JSON a formato URL-encoded.
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            success: function(response) {
                
                console.log('Review submitted successfully');
                location.reload();
            },
            error: function(xhr, status, error) {
                console.log('Error submitting review: ' + error);
                
            }
        });
        
    });
});
</script>

<% if(test_session) { %>

	<script type="text/javascript">
	
	$(document).ready(function () {
		
	    $(".add_to_cart").submit(function(e) {
	        e.preventDefault();
	        var form = $(this);
	        var url = form.attr('action');
	        var data = form.serialize() + "&ctoken=" + encodeURIComponent('<%=session.getAttribute("ctoken")%>');
	        
	        $.ajax({
	            type: "POST",
	            url: url,
	            data: data,
	            success: function(response) {
	                getCartCounterFromSession();
	                window.location.href = "${pageContext.request.contextPath}/Carrello";
	                
	            },
	            error: function(xhr, status, error) {
	                console.error("Error: " + status + " " + error);
	            }
	        });
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

<% } else { %>

<script type="text/javascript">

var carrello = new Carrello();
carrello.updateProductNumber();

function dosomethinglol() {
    carrello = new Carrello();
    carrello.addProduct(document.getElementsByName('prodotto')[0].value);
    document.getElementById('checkout_items').innerHTML = localStorage.getItem('CartCounter');
    document.getElementById('checkout_items').style.display = "block";	
    window.location.href = "${pageContext.request.contextPath}/Carrello";
    } 
    
document.getElementById('cartb').addEventListener('click', dosomethinglol);



</script>
<% } %>
	
	<%@include file="./generic_footer.jsp"%>