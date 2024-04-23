<%@include file="./generic_header.jsp" %>
	
	<%@ page import="java.util.*,model.Prodotto" %>
	<%
		String categoria = (String) request.getParameter("category");
		Collection<?> products = (Collection<?>) request.getAttribute("Prodotto");
		if (products == null){
			response.sendRedirect("./pages/home.jsp");
			return;
		}
	%>
	

	<div class="container product_section_container">
		<div class="row">
			<div class="col product_section clearfix">

				<!-- Breadcrumbs -->

				<div class="breadcrumbs d-flex flex-row align-items-center">
					<ul>
						<li><a href="./pages/home.jsp">Home</a></li>
						
						<li><a href="categories - Copia.html"><i class="fa fa-angle-right" aria-hidden="true"></i><%= categoria %></a></li> <!-- ARMA-ARMATURA-ABBIGLIAMENTO-ACCESSORIO -->
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
							<li><a href="./Prodotti?category=Arma">Armi</a></li> <% } %>
							
							<%if (categoria.equalsIgnoreCase("Armatura")){ %>
							<li class="active"><a href="#"><span><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>Armature</a></li><%} else { %>
							<li><a href="./Prodotti?category=Armatura">Armature</a></li> <% } %>
							
							<%if (categoria.equalsIgnoreCase("Accessorio")){ %>
							<li class="active"><a href="#"><span><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>Accessori</a></li><%} else { %>
							<li><a href="./Prodotti?category=Accessorio">Accessori</a></li> <% } %>
							
							<%if (categoria.equalsIgnoreCase("Abbigliamento")){ %>
							<li class="active"><a href="#"><span><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>Abbigliamenti</a></li><%} else { %>
							<li><a href="./Prodotti?category=Abbigliamento">Abbigliamenti</a></li> <% } %>
						
						    </ul>
					</div>

					<!-- Price Range Filtering -->
					<div class="sidebar_section">
						<div class="sidebar_title">
							<h5>Filter by Price</h5>
						</div>
						<p>
							<input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
						</p>
						<div id="slider-range"></div>
						<div class="filter_button"><span>Filtra</span></div>
					</div>

					<!-- Taglie -->
					<%if (categoria.equalsIgnoreCase("Abbigliamento")) { %>
					<div class="sidebar_section">
						<div class="sidebar_title">
							<h5>Taglie</h5>
						</div>
						<ul class="checkboxes">
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>S</span></li>
							<li class="active"><i class="fa fa-square" aria-hidden="true"></i><span>M</span></li> <!-- CLASSE INTERSCAMBIABILE DELLA CATEGORIA SELEZIONATA -->
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>L</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>XL</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>XXL</span></li>
						</ul>
					</div> 

					<!-- Color -->
					<div class="sidebar_section">
						<div class="sidebar_title">
							<h5>Color</h5>
						</div>
						<ul class="checkboxes">
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Black</span></li>
							<li class="active"><i class="fa fa-square" aria-hidden="true"></i><span>Pink</span></li> <!-- CLASSE INTERSCAMBIABILE DELLA CATEGORIA SELEZIONATA -->
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>White</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Blue</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Orange</span></li>
						</ul>
					</div>
					<% }  else if (categoria.equalsIgnoreCase("Arma")){%>
					
					<!-- Tipi -->
					<div class="sidebar_section">
						<div class="sidebar_title">
							<h5>Tipi</h5>
						</div>
						<ul class="checkboxes">
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Meelee</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Ranged</span></li> 
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Decorativa</span></li>
						</ul>
					</div>
				</div>
					<% } else if (categoria.equalsIgnoreCase("Armatura")) { %>
					<!-- Materials -->
					<div class="sidebar_section">
						<div class="sidebar_title">
							<h5>Materiali</h5>
						</div>
						<ul class="checkboxes">
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Cuoio</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Ferro</span></li> 
						</ul>
					</div>
					
					<!-- Pieces -->
					<div class="sidebar_section">
						<div class="sidebar_title">
							<h5>Pezzi</h5>
						</div>
						<ul class="checkboxes">
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Elmo</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Bracciali</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Pettorina</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Schinieri</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Spallacci</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Gorgiera</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Guanti</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Gambe</span></li>
							<li><i class="fa fa-square-o" aria-hidden="true"></i><span>Braccia</span></li> 
						</ul>
						<div class="show_more">
							<span><span>+</span>Mostra altro</span>
						</div>
					</div>
				</div>
					<% } %>

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
												<li class="type_sorting_btn" data-isotope-option='{ "sortBy": "original-order" }'><span>Default Sorting</span></li>
												<li class="type_sorting_btn" data-isotope-option='{ "sortBy": "price" }'><span>Price</span></li>
												<li class="type_sorting_btn" data-isotope-option='{ "sortBy": "name" }'><span>Product Name</span></li>
											</ul>
										</li>
										<li>
											<span>Show</span>
											<span class="num_sorting_text">6</span>
											<i class="fa fa-angle-down"></i>
											<ul class="sorting_num">
												<li class="num_sorting_btn"><span>6</span></li>
												<li class="num_sorting_btn"><span>12</span></li>
												<li class="num_sorting_btn"><span>24</span></li>
											</ul>
										</li>
									</ul>
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
										<div id="next_page" class="page_next"><a href="#"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></a></div>
									</div>

								</div>

								<!-- Product Grid -->

								<div class="product-grid">

							<% 
							if (products != null){
							Iterator<?> product = products.iterator();
							 
								while(product.hasNext()) {
									Prodotto prod = (Prodotto) product.next();
									%>

									<div class="product-item <%=request.getParameter("category")%>">
										<div class="product discount product_filter">
											<div class="product_image">
												<img src="<%=prod.getImg1()%>" alt="<%=prod.getNome()%>">
											</div>
											<div class="favorite favorite_left"></div>
											<div class="product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center"><span>-$<%= (prod.getPrezzo()/100)*30%></span></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html"><%=prod.getNome()%></a></h6>
												<div class="product_price"><%=prod.getPrezzo()%>$<span><%=((prod.getPrezzo()/100)*130)%>$</span></div> 
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div> 
								<% }
								} else { %> <div>Nessun prodotto disponibile, riprovare più tardi</div> <% } %>

									<!-- 

									<div class="product-item women">
										<div class="product product_filter">
											<div class="product_image">
												<img src="images/product_2.png" alt="">
											</div>
											<div class="favorite"></div>
											<div class="product_bubble product_bubble_left product_bubble_green d-flex flex-column align-items-center"><span>new</span></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">Samsung CF591 Series Curved 27-Inch FHD Monitor</a></h6>
												<div class="product_price">$610.00</div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>


									<div class="product-item women">
										<div class="product product_filter">
											<div class="product_image">
												<img src="images/product_3.png" alt="">
											</div>
											<div class="favorite"></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">Blue Yeti USB Microphone Blackout Edition</a></h6>
												<div class="product_price">$120.00</div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>


									<div class="product-item accessories">
										<div class="product product_filter">
											<div class="product_image">
												<img src="images/product_4.png" alt="">
											</div>
											<div class="product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center"><span>sale</span></div>
											<div class="favorite favorite_left"></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">DYMO LabelWriter 450 Turbo Thermal Label Printer</a></h6>
												<div class="product_price">$410.00</div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>


									<div class="product-item women men">
										<div class="product product_filter">
											<div class="product_image">
												<img src="images/product_5.png" alt="">
											</div>
											<div class="favorite"></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">Pryma Headphones, Rose Gold & Grey</a></h6>
												<div class="product_price">$180.00</div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>


									<div class="product-item accessories">
										<div class="product discount product_filter">
											<div class="product_image">
												<img src="images/product_6.png" alt="">
											</div>
											<div class="favorite favorite_left"></div>
											<div class="product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center"><span>-$20</span></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">Fujifilm X100T 16 MP Digital Camera (Silver)</a></h6>
												<div class="product_price">$520.00<span>$590.00</span></div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>

									

									<div class="product-item women">
										<div class="product product_filter">
											<div class="product_image">
												<img src="images/product_7.png" alt="">
											</div>
											<div class="favorite"></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">Samsung CF591 Series Curved 27-Inch FHD Monitor</a></h6>
												<div class="product_price">$610.00</div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>

									

									<div class="product-item accessories">
										<div class="product product_filter">
											<div class="product_image">
												<img src="images/product_8.png" alt="">
											</div>
											<div class="favorite"></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">Blue Yeti USB Microphone Blackout Edition</a></h6>
												<div class="product_price">$120.00</div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>

									

									<div class="product-item men">
										<div class="product product_filter">
											<div class="product_image">
												<img src="images/product_9.png" alt="">
											</div>
											<div class="product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center"><span>sale</span></div>
											<div class="favorite favorite_left"></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">DYMO LabelWriter 450 Turbo Thermal Label Printer</a></h6>
												<div class="product_price">$410.00</div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>

									

									<div class="product-item men">
										<div class="product product_filter">
											<div class="product_image">
												<img src="images/product_10.png" alt="">
											</div>
											<div class="favorite"></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">Pryma Headphones, Rose Gold & Grey</a></h6>
												<div class="product_price">$180.00</div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>

									

									<div class="product-item women men">
										<div class="product product_filter">
											<div class="product_image">
												<img src="images/product_5.png" alt="">
											</div>
											<div class="favorite"></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">Pryma Headphones, Rose Gold & Grey</a></h6>
												<div class="product_price">$180.00</div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>

									

									<div class="product-item accessories">
										<div class="product discount product_filter">
											<div class="product_image">
												<img src="images/product_6.png" alt="">
											</div>
											<div class="favorite favorite_left"></div>
											<div class="product_bubble product_bubble_right product_bubble_red d-flex flex-column align-items-center"><span>-$20</span></div>
											<div class="product_info">
												<h6 class="product_name"><a href="single.html">Fujifilm X100T 16 MP Digital Camera (Silver)</a></h6>
												<div class="product_price">$520.00<span>$590.00</span></div>
											</div>
										</div>
										<div class="red_button add_to_cart_button"><a href="#">add to cart</a></div>
									</div>
								</div> -->

								<div class="product_sorting_container product_sorting_container_bottom clearfix">
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

								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	  </div>
	</div>
	
	<%@include file="./generic_footer.jsp" %>
