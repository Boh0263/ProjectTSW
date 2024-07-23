<%@include file="./generic_header.jsp" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ page import="java.util.*,model.Prodotto" %>
	<%
		String categoria = (String) request.getParameter("category");
		boolean test_session = (session != null & session.getAttribute("username") != null);
		Collection<?> products = (Collection<?>) request.getAttribute("Prodotto");
		if (products == null){
			response.sendRedirect("./pages/home.jsp");
			return;
		}
	%>
	
	<input type="hidden" name="_csrf" value="<%=session.getAttribute("ctoken")%>" />
	<div class="container product_section_container">
		<div class="row">
			<div class="col product_section clearfix">

				<!-- Breadcrumbs -->

				<div class="breadcrumbs d-flex flex-row align-items-center">
					<ul>
						<li><a href="./">Home</a></li>
						
						<li><a href="./Catalogo?category=<%= categoria %>"><i class="fa fa-angle-right" aria-hidden="true"></i><%= categoria %></a></li> <!-- ARMA-ARMATURA-ABBIGLIAMENTO-ACCESSORIO -->
					</ul>
				</div>

				<!-- Sidebar -->

				<div class="sidebar">
					<div class="sidebar_section">
						<div class="sidebar_title">
							<h5>Categoria prodotto</h5>
						</div>
							<ul class="sidebar_categories">
							
							<%if (categoria.equalsIgnoreCase("Arma")){ %>
							<li class="active"><a href="#"><span><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>Armi</a></li><%} else { %>
							<li><a href="./Catalogo?category=Arma">Armi</a></li> <% } %>
							
							<%if (categoria.equalsIgnoreCase("Armatura")){ %>
							<li class="active"><a href="#"><span><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>Armature</a></li><%} else { %>
							<li><a href="./Catalogo?category=Armatura">Armature</a></li> <% } %>
							
							<%if (categoria.equalsIgnoreCase("Accessorio")){ %>
							<li class="active"><a href="#"><span><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>Accessori</a></li><%} else { %>
							<li><a href="./Catalogo?category=Accessorio">Accessori</a></li> <% } %>
							
							<%if (categoria.equalsIgnoreCase("Abbigliamento")){ %>
							<li class="active"><a href="#"><span><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>Abbigliamenti</a></li><%} else { %>
							<li><a href="./Catalogo?category=Abbigliamento">Abbigliamento</a></li> <% } %>
						
						    </ul>
					</div>

					<!-- Price Range Filtering -->
					<div class="sidebar_section">
						<div class="sidebar_title">
							<h5>Filtra per Prezzo</h5>
						</div>
						<p>
							<input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
						</p>
						<div id="slider-range"></div>
						<div class="filter_button"><span>Filtra</span></div>
					</div>

					
					

				</div>

				<!-- Main Content -->

				<div class="main_content">

					<!-- Products -->

					<div class="products_iso">
						<div class="row">
							<div class="col">

								<!-- Product Sorting -->

								<div class="product_sorting_container product_sorting_container_top">
									<ul class="product_sorting">
										<li>
											<span class="type_sorting_text">Default Sorting</span>
											<i class="fa fa-angle-down"></i>
											<ul class="sorting_type">
												<li class="type_sorting_btn" data-isotope-option='{ "sortBy": "original-order" }'><span>Default</span></li>
												<li class="type_sorting_btn" data-isotope-option='{ "sortBy": "price" }'><span>Prezzo</span></li>
												<li class="type_sorting_btn" data-isotope-option='{ "sortBy": "name" }'><span>Nome</span></li>
											</ul>
										</li>
										<li>
											<span>Mostra</span>
											<span class="num_sorting_text">6</span>
											<i class="fa fa-angle-down"></i>
											<ul class="sorting_num">
												<li class="num_sorting_btn"><span>6</span></li>
												<li class="num_sorting_btn"><span>12</span></li>
												<li class="num_sorting_btn"><span>24</span></li>
											</ul>
										</li>
									</ul>
									

								</div>

								<!-- Product Grid -->
								<div class="product-grid">

							<% 
							if (products != null) {
							Iterator<?> product = products.iterator();
							 
								while(product.hasNext()) {
									Prodotto prod = (Prodotto) product.next();
									%>

									<div class="product-item <%=request.getParameter("category")%>" data-product-id="<%=prod.getNome()%>">
										<div class="product discount product_filter">
											<div class="product_image">	
					                        
											<img src="./image?img-id=<%=prod.getImg1()%>" alt="<%=prod.getNome()%>">
											</div>
											<div class="favorite favorite_left"></div>
											<div class="product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center"><span>-<fmt:formatNumber value="<%= (prod.getPrezzo()/100)*30%>" type="number" pattern="0.0"/>$</span></div>
											<div class="product_info">
												<h6 class="product_name"><a href="./Prodotto?Nome=<%=prod.getNome()%>"><%=prod.getNome()%></a></h6>
												<div class="product_price"><fmt:formatNumber value="<%=prod.getPrezzo()%>" type="number" pattern="0.00" groupingUsed="false"/>&euro;<span><fmt:formatNumber value="<%=((prod.getPrezzo()/100)*130)%>" type="number" pattern="0.00"/>$</span></div> <!--  Possibili problemi di formatting con il filtro per prezzo --> 
											</div>
										</div>
										<% 
											if (test_session) { %>
																															
										<form action="./Carrello?action=addProduct" method="post" class="add_to_cart">
										<%}%>
            								<div class="red_button carrello_button">
              									<input type="hidden" name="prodotto" value="<%=prod.getNome()%>" /> 
              								    <button <% if(test_session){ %>type="submit" <% } else {%> id="cartb" <% } %> style="display: transparent;">Aggiungi al Carrello</button>
            								</div>
            							<% if (test_session) { %>
            							</form>
            							<%}%>
									</div> 
								<% }} %>
							</div>
	
								<div class="product_sorting_container product_sorting_container_bottom clearfix">
									

								</div>
								
								

							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	  </div>
	</div>
	<div id="notification-container"></div>
	<script src="./resources/js/jquery-3.2.1.min.js"></script>
    <script src="./resources/js/cart-custom.js"></script>
    
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
	                showBanner('Prodotto aggiunto al carrello', 'success');
	                getCartCounterFromSession();
	                
	            },
	            error: function(xhr, status, error) {
	                showBanner('Errore, prodotto non aggiunto', 'error');
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
    } 
    
document.getElementById('cartb').addEventListener('click', dosomethinglol);

</script>

<script type="text/javascript">

window.onload = function() {
    var markerElement = document.querySelector('.products-iso');

    if (markerElement) {
        var sectionWrapper = document.createElement('div');
        sectionWrapper.classList.add('product_sorting_container', 'product_sorting_container_bottom', 'clearfix');

        sectionWrapper.innerHTML = `
        
            <ul class="product_sorting">
                <li>
                    <span>Show:</span>
                    <span class="num_sorting_text">04</span>
                    <i class="fa fa-angle-down"></i>
                    <ul class="sorting_num">
                        <li class="num_sorting_btn"><span>01</span></li>
                        <li class="num_sorting_btn"><span>02</span></li>
                        <li class="num_sorting_btn"><span>03</span></li>
                        <li class="num_sorting_btn"><span>04</span></li>
                    </ul>
                </li>
            </ul>
            <span class="showing_results">!!!Showing 1–3 of 12 results!!!</span>
            <div class="pages d-flex flex-row align-items-center">
                <div class="page_current">
                    <span>1</span>
                    <ul class="page_selection">
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                    </ul>
                </div>
                <div class="page_total"><span>of</span> 3</div>
                <div id="next_page_1" class="page_next"><a href="#"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a></div>
            </div>
            
        `;
        markerElement.appendChild(sectionWrapper);
    }
};

</script>

<% } %>
<script>
$(document).ready(function() {
 
    var wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];

   
    $('.product-item').each(function() {
        var productId = $(this).data('product-id'); 
        if (wishlist.includes(productId)) {
            $(this).find('.favorite').addClass('active');
        }
    });

   
    $('.favorite').on('click', function() {
        var $this = $(this);
        var productId = $this.closest('.product-item').data('product-id'); 
        var isActive = $this.hasClass('active');

        if (isActive) {
           
            removeFromWishlist(productId);
            $this.removeClass('active');
        } else {
       
            addToWishlist(productId);
            $this.addClass('active');
        }
    });

    function addToWishlist(productId) {
        var wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];
        if (!wishlist.includes(productId)) {
            wishlist.push(productId);
            localStorage.setItem('wishlist', JSON.stringify(wishlist));
        }
    }

    function removeFromWishlist(productId) {
        var wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];
        var index = wishlist.indexOf(productId);
        if (index !== -1) {
            wishlist.splice(index, 1);
            localStorage.setItem('wishlist', JSON.stringify(wishlist));
        }
    }
});

</script>


	
<%@include file="./generic_footer.jsp" %>

