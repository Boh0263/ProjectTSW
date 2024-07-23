<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Preferiti</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/responsive.css">
    <style>
        .wishlist_table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .wishlist_table th, .wishlist_table td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }

        .wishlist_table th {
            background-color: #f4f4f4;
        }

        .wishlist_table img {
            max-width: 100px;
            height: auto;
        }

        .wishlist_table .btn {
            margin: 5px;
        }

        .wishlist_title {
            text-align: center;
            margin: 20px 0;
            font-size: 24px;
        }

        .empty-message {
            text-align: center;
            margin-top: 50px;
            font-size: 18px;
            color: #777;
        }

        .empty-message img {
            max-width: 150px;
            margin-bottom: 20px;
        }

        .empty-message p {
            font-size: 24px;
            color: #333;
            font-weight: bold;
        }
    </style>
</head>

<%@include file="./generic_header.jsp" %>

<body>


<div class="main_banner">
	<div class="container fill_height" style="margin-top: 175px; margin-bottom:175px;">
	    <div class="row align-items-center fill_height">
	                <div class="row">
					<div class="main_slider_content">
						<h2>Preferiti</h2>
					</div>
					</div>
					</div>
					<div class="row justify-content-center">
					<div class="wishlist_content">
					    <!-- Il contentuto della wishlist sarà aggiunto qui. -->
					</div>
				</div>
			</div>
		</div>
		
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/styles/bootstrap4/popper.js"></script>
<script src="${pageContext.request.contextPath}/resources/styles/bootstrap4/bootstrap.min.js"></script>
<script>
    //wishlist a prefData
    var wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];
    var prefData = {};

    wishlist.forEach(function(productName) {
        prefData[productName] = 1; 
    });

    if (Object.keys(prefData).length === 0) {
        $('.wishlist_content').html(`
            <div class="empty-message">
                <img src="${pageContext.request.contextPath}/resources/static/images/empty_wishlist.jpg" alt="Empty Wishlist">
                <p>La tabella dei Preferiti è vuota</p>
            </div>
        `);
    } else {
        console.log('Sending prefData to server:', prefData);

        $.ajax({
            url: 'Preferiti',
            method: 'POST',
            data: { keys: JSON.stringify(prefData) }, // Manda prefData come JSON string
            success: function(prodotti) {
                console.log('Received prodotti from server:', prodotti); // Debug log

                if (prodotti && Object.keys(prodotti).length > 0) {
                    var tableHtml = `
                        <table class="table table-bordered wishlist_table">
                            <thead>
                                <tr>
                                    <th>Immagine</th>
                                    <th>Categoria</th>
                                    <th>Nome Prodotto</th>
                                    <th>Prezzo</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                    `;

                    Object.entries(prodotti).forEach(function([key, prodotto]) {
                        console.log('Processing prodotto:', prodotto); // Debug log

                        tableHtml += `
                            <tr>
                                <td data-label="Image"><img class="img-fluid" src="./image?img-id=` + prodotto.img1 + `" alt="` + prodotto.nome + `"></td>
                                <td data-label="Category">` + prodotto.categoria + `</td>
                                <td data-label="Name">` + prodotto.nome + `</td>
                                <td data-label="Price">&euro; `+ prodotto.prezzo.toFixed(2) + `</td>
                                <td data-label="Actions">
                                    <button class="btn btn-danger remove-item" data-name="` + prodotto.nome + `">Togli dai preferiti</button>
                                </td>
                            </tr>
                        `;
                        
                    });

                    tableHtml += `
                            </tbody>
                        </table>
                    `;

                    $('.wishlist_content').html(tableHtml);

                    $('.remove-item').click(function() {
                        var productName = $(this).data('name');
                        var wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];
                        var index = wishlist.indexOf(productName);
                        console.log(index);
                        console.log(productName);
                        if (index !== -1) {
                            wishlist.splice(index, 1);
                            localStorage.setItem('wishlist', JSON.stringify(wishlist));
                            window.location.reload();
                        }
                    });
                } else {
                    $('.wishlist_content').html('<p class="text-center">La tabella dei Preferiti è vuota</p>');
                }
            },
            error: function() {
                $('.wishlist_content').html('<p class="text-center">Si è verificato un errore, riprovare più tardi.</p>');
            }
        });
    }
</script>
</body>

<%@include file="./generic_footer.jsp" %>

</html>
