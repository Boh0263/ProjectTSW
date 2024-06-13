<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="./generic_header.jsp"%>

    <div class="container single_product_container">
		<div class="row">
			<div class="col">

				<!-- Breadcrumbs -->

				<div class="breadcrumbs d-flex flex-row align-items-center">
					<ul>
						<li><a href="index.html">Home</a></li>
						<li><a href="!!!!categories.html!!!!"><i class="fa fa-angle-right" aria-hidden="true"></i>!!!!Men's!!!!</a></li> <!-- DA CAMBIARE IL LINK ALLA CATEGORIA DELL'OGGETTO, SE SPADA -> ARMA.HTML E ARMA AL POSTO DI MEN'S -->
						<li class="active"><a href="#"><i class="fa fa-angle-right" aria-hidden="true"></i>!!!!Single Product!!!!</a></li> <!-- STESSO NOME DEL PRODOTTO NEL TITOLO -->
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
									<li><img src="images/Error.png" alt="" data-image="images/Error.png"></li> <!-- NOMI DELLE FOTO 1-2-3 -->
									<li class="active"><img src="images/Error.png" alt="" data-image="images/Error.png"></li> <!-- NOMI DELLE FOTO 1-2-3 -->
									<li><img src="images/Error.png" alt="" data-image="images/Error.png"></li> <!-- NOMI DELLE FOTO 1-2-3 -->
								</ul>
							</div>
						</div>
						<div class="col-lg-9 image_col order-lg-2 order-1">
							<div class="single_product_image">
								<div class="single_product_image_background" style="background-image:url(images/Error.png)"></div> <!-- NOMI DELLA FOTO 2 DISPLAY DEFAULT -->
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-5">
				<div class="product_details">
					<div class="product_details_title">
						<h2>!!!!Pocket cotton sweatshirt!!!!</h2>  <!-- STESSO NOME DEL PRODOTTO NEL TITOLO -->
						<p>!!!!Nam tempus turpis at metus scelerisque placerat nulla deumantos solicitud felis. Pellentesque diam dolor, elementum etos lobortis des mollis ut...!!!!</p>  <!-- DESCRIZIONE BREVE -->
					</div>
					
					<div class="original_price">$629.99</div> <!-- PREZZO DEFAULT -->
					<div class="product_price">$495.00</div> <!-- PREZZO SCONTATO -->
					<ul class="star_rating"> <!-- RECENSIONI (DA CANCELLARE SE NON VOGLIAMO GESTIRLO ORA) -->
						<li><i class="fa fa-star" aria-hidden="true"></i></li>
						<li><i class="fa fa-star" aria-hidden="true"></i></li>
						<li><i class="fa fa-star" aria-hidden="true"></i></li>
						<li><i class="fa fa-star" aria-hidden="true"></i></li>
						<li><i class="fa fa-star-o" aria-hidden="true"></i></li> 
					</ul> 
					
					<div class="quantity d-flex flex-column flex-sm-row align-items-sm-center"> <!-- CARRELLO (DA CANCELLARE SE NON VOGLIAMO GESTIRLO ORA) -->
						<span>Quantity:</span> 
						<div class="quantity_selector">
							<span class="minus"><i class="fa fa-minus" aria-hidden="true"></i></span>
							<span id="quantity_value">1</span>
							<span class="plus"><i class="fa fa-plus" aria-hidden="true"></i></span>
						</div>
						<div class="red_button carrello_button"><a href="#">add to cart</a></div>
						<div class="product_favorite d-flex flex-column align-items-center justify-content-center"></div> <!-- PREFERITI (DA CANCELLARE SE NON VOGLIAMO GESTIRLO ORA) -->
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
							<li class="tab" data-active-tab="tab_2"><span>Informazioni Aggiuntive</span></li>
							<li class="tab" data-active-tab="tab_3"><span>Recensioni</span></li> <!-- RECENSIONI (DA CANCELLARE SE NON VOGLIAMO GESTIRLO ORA) -->
						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">

					<!-- Tab Description -->

					<div id="tab_1" class="tab_container active">
						<div class="row">
							<div class="col-lg-5 desc_col">
								<div class="tab_title">
									<h4>Descrizione</h4>
								</div>
								<div class="tab_text_block">
									<h2>!!!!Pocket cotton sweatshirt!!!!</h2> <!-- STESSO NOME DEL PRODOTTO NEL TITOLO -->
									<p>!!!!Nam tempus turpis at metus scelerisque placerat nulla deumantos solicitud felis. Pellentesque diam dolor, elementum etos lobortis des mollis ut...!!!!</p> <!-- DESCRIZIONE DEFAULT PRODOTTO -->
								</div>
							</div>
							<div class="col-lg-5 offset-lg-2 desc_col">
								<div class="tab_image">
									<img src="images/Error.png" alt=""> <!-- NOMI DELLA FOTO 2 DISPLAY DEFAULT -->
								</div>
							</div>
						</div>
					</div>

					<!-- Tab Additional Info -->

					<div id="tab_2" class="tab_container"> <!-- DA SCEGLIERE SE FARE UNA MEGA TABELLA CON TUTTE LE DIFFERENZE, O UNA TABELLA PER CATEGORIA -->
						<div class="row">
							<div class="col additional_info_col">
								<div class="tab_title additional_info_title">
									<h4>Informazioni Aggiuntive</h4>
								</div>
								<p>COLOR:<span>Gold, Red</span></p>
								<p>SIZE:<span>L,M,XL</span></p>
							</div>
						</div>
					</div>

					<!-- Tab Reviews -->

					<div id="tab_3" class="tab_container">
						<div class="row">

							<!-- User Reviews -->
							<!-- RECENSIONI (DA CANCELLARE SE NON VOGLIAMO GESTIRLO ORA) -->
							<div class="col-lg-6 reviews_col">
								<div class="tab_title reviews_title">
									<h4>Recensioni</h4>
								</div>

								<!-- User Review -->

								<div class="user_review_container d-flex flex-column flex-sm-row">
									<div class="user">
										<div class="user_pic"></div>
										<div class="user_rating">
											<ul class="star_rating">
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
											</ul>
										</div>
									</div>
									<div class="review">
										<div class="review_date">27 Aug 2016</div>
										<div class="user_name">Brandon William</div>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
									</div>
								</div>

								<!-- User Review -->

								<div class="user_review_container d-flex flex-column flex-sm-row">
									<div class="user">
										<div class="user_pic"></div>
										<div class="user_rating">
											<ul class="star_rating">
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
											</ul>
										</div>
									</div>
									<div class="review">
										<div class="review_date">27 Aug 2016</div>
										<div class="user_name">Brandon William</div>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
									</div>
								</div>
							</div>

							<!-- Add Review -->

							<div class="col-lg-6 add_review_col">

								<div class="add_review">
									<form id="review_form" action="post">
										<div>
											<h1>Add Review</h1>
											<input id="review_name" class="form_input input_name" type="text" name="name" placeholder="Name*" required="required" data-error="Name is required.">
											<input id="review_email" class="form_input input_email" type="email" name="email" placeholder="Email*" required="required" data-error="Valid email is required.">
										</div>
										<div>
											<h1>Your Rating:</h1>
											<ul class="user_star_rating">
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star" aria-hidden="true"></i></li>
												<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
											</ul>
											<textarea id="review_message" class="input_review" name="message"  placeholder="Your Review" rows="4" required data-error="Please, leave us a review."></textarea>
										</div>
										<div class="text-left text-sm-right">
											<button id="review_submit" type="submit" class="red_button review_submit_btn trans_300" value="Submit">submit</button>
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
	
	<%@include file="./generic_footer.jsp"%>