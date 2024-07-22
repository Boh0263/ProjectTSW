
<!-- Footer -->
<div id="banner-container"></div>
	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="footer_nav_container d-flex flex-sm-row flex-column align-items-center justify-content-lg-start justify-content-center text-center">
						<ul class="footer_nav">
							<li><a href="#">Blog</a></li>
							<li><a href="#">FAQs</a></li>
							<li><a href="contact.html">Contact us</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="footer_social d-flex flex-row align-items-center justify-content-lg-end justify-content-center">
						<ul>
							<li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-skype" aria-hidden="true"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</footer>

</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script> <!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/styles/bootstrap4/popper.js"></script>
<script src="${pageContext.request.contextPath}/resources/styles/bootstrap4/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/easing/easing.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script> <!-- jQuery UI -->
<script src="${pageContext.request.contextPath}/resources/js/cart-number.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/categories_custom.js"></script>

<% if ( session != null && session.getAttribute("username") != null) { %>
<script type="text/javascript">
 $(document).ready(function() {
	 updateSCartCounter();
		 document.getElementById('checkout_items').innerHTML = localStorage.getItem('CartSCounter');
		 if(localStorage.getItem('CartSCounter') == 0 || localStorage.getItem('CartSCounter') == null) {
			document.getElementById('checkout_items').style.display = "none";
		 } 
  });
 
 function updateSCartCounter() {
 $.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/ProdottoCount",
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
 $(document).ready(function() {
		 document.getElementById('checkout_items').innerHTML = localStorage.getItem('CartCounter');
		 if(localStorage.getItem('CartCounter') == 0 || localStorage.getItem('CartCounter') == null) {
			document.getElementById('checkout_items').style.display = "none";
		 } 
  });
</script>
<% } %>
</body>

</html>