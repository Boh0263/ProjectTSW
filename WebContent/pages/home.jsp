<%@include file="./generic_header.jsp"%>

	<!-- Slider -->
	<div class="main_slider" style="background-image:url(resources/static/images/slider_1.jpg)">
		<div class="container fill_height">
			<div class="row align-items-center fill_height">
				<div class="col">
					<div class="main_slider_content">
						<h6>Collezione Estiva 2023/2024</h6>
						<h1>Ottieni prezzi scontati fino al 30%</h1>
						<div class="red_button shop_button"><a href="${pageContext.request.contextPath}/Catalogo?category=Arma">Acquista ora!</a></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Banner -->

	<div class="main_banner">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<div class="banner_item align-items-center" style="background-image:url(resources/static/images/Arma.jpg)">
						<div class="banner_category" style="background-color:rgb(244,237,229)">
							<a href="./Catalogo?category=Arma">armi</a>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="banner_item align-items-center" style="background-image:url(resources/static/images/Armatura.jpg)">
						<div class="banner_category" style="background-color:rgb(244,237,229)">
							<a href="./Catalogo?category=Armatura">armature</a>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="banner_item align-items-center" style="background-image:url(resources/static/images/Accessorio.jpg)">
						<div class="banner_category" style="background-color:rgb(244,237,229)">
							<a href="./Catalogo?category=Accessorio">accessori</a>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="banner_item align-items-center" style="background-image:url(resources/static/images/Abbigliamento.jpg)">
						<div class="banner_category" style="background-color:rgb(244,237,229)">
							<a href="./Catalogo?category=Abbigliamento">abbigliamento</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
<%@ include file="./generic_footer.jsp" %>
