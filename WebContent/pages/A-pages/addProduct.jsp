<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aggiungi Prodotto</title>
<link rel="stylesheet" type="text/css" href="./resources/styles/bootstrap4/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./resources/plugins/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./resources/styles/edit_style.css">
<style>
.form-control, .thumbnail {
    border-radius: 2px;
}
.btn-danger {
    background-color: #B73333;
}

/* File Upload */
.fake-shadow {
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
}
.fileUpload {
    position: relative;
    overflow: hidden;
}
.fileUpload #logo-id-1, #logo-id-2, #logo-id-3 {
    position: absolute;
    top: 0;
    right: 0;
    margin: 0;
    padding: 0;
    font-size: 33px;
    cursor: pointer;
    opacity: 0;
    filter: alpha(opacity=0);
}

.main-img-preview {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 200px;
    margin-bottom: 20px;
    
}

.img-preview {
    max-width: 200px;
    max-height: 200px; 
    border: 2px solid black;
    border-radius: 5px;
}

.input-group {
    margin-bottom: 20px;
}
</style>
</head>
<body>
<%@ include file="admin_header.jsp" %>  





<div class="container form-container">
    <div class="card">
        <div class="card-header">
           Aggiungi Prodotto
        </div>
        <div class="card-body">
            <form action="/AdminControl" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="prodNome">Nome</label>
                    <input type="text" id="prodNome" name="prodNome" class="form-control" placeholder="Inserisci il nome del prodotto..">
                </div>
                <div class="form-group">
                    <label for="prodGiacenza">Giacenza</label>
                    <input type="text" id="prodGiacenza" name="prodGiacenza" class="form-control" placeholder="Inserisci il numero di prodotti disponibili..">
                </div>
                <div class="form-group">
                    <label for="prodDescrizione">Descrizione</label>
                    <input type="text" id="prodDescrizione" name="prodDescrizione" class="form-control" placeholder="Inserisci la descrizione del prodotto..">
                </div>
                <div class="form-group">
                    <label for="prodPrezzo">Prezzo</label>
                    <input type="text" id="prodPrezzo" name="prodPrezzo" class="form-control" placeholder="Inserisci il prezzo del prodotto (Netto)..">
                </div>
                <div class="form-group">
                    <label for="prodIVA">IVA</label>
                    <input type="text" id="prodIVA" name="prodIVA" class="form-control" value="Inserire la percentuale IVA..">
                </div>
                <div class="form-group">
                    <label for="prodCategory">Categoria</label>
                    <select id="prodCategory" name="prodCategory" class="form-control">
                        <option value="Abbigliamento">Abbigliamento</option>
                        <option value="Armatura">Armatura</option>
                        <option value="Arma">Arma</option>
                        <option value="Accessorio" selected>Accessorio</option>
                    </select>
                </div>
               
                <div id="tipoField" class="form-group">
                    <label for="prodTipo">Tipo</label>
                    <input type="text" id="prodTipo" name="prodTipo" class="form-control" value="">
                </div>
                <div id="genereField" class="form-group">
                    <label for="prodGenere">Genere</label>
                    <input type="text" id="prodGenere" name="prodGenere" class="form-control" value="">
                </div>
             
                <div id="materialeField" class="form-group">
                    <label for="prodMateriale">Materiale</label>
                    <input type="text" id="prodMateriale" name="prodMateriale" class="form-control" value="">
                </div>
                <div id="pezzoField" class="form-group">
                    <label for="prodPezzo">Pezzo</label>
                    <input type="text" id="prodPezzo" name="prodPezzo" class="form-control" value="">
                </div>
            
                <div id="utilizzoField" class="form-group">
                    <label for="prodUtilizzo">Utilizzo</label>
                    <input type="text" id="prodUtilizzo" name="prodUtilizzo" class="form-control" value="">
                </div>
                
                <div class="form-group">
               		<label for ="prodImg1">Immagine 1</label>	
                    <div class="main-img-preview">
                <img class="thumbnail img-preview" id="img-preview-1" src="./image?img-id=1" title="Preview Logo">
                    </div>
               <div class="input-group">
                <input id="fakeUploadLogo1" class="form-control fake-shadow" placeholder="Choose File" disabled="disabled">
                <div class="input-group-btn">
                  <div class="fileUpload btn btn-dark fake-shadow">
                    <span><i class="fa fa-upload"></i> Upload File </span>
                    <input id="logo-id-1" name="logo" type="file" class="attachment_upload">
                  </div>
                </div>
              </div>
            </div>
            
				<div class="form-group">
               		<label for ="prodImg2">Immagine 2</label>	
                    <div class="main-img-preview">
                <img class="thumbnail img-preview" id="img-preview-2" src="./image?img-id=1" title="Preview Logo">
                    </div>
               <div class="input-group">
                <input id="fakeUploadLogo2" class="form-control fake-shadow" placeholder="Choose File" disabled="disabled">
                <div class="input-group-btn">
                  <div class="fileUpload btn btn-dark fake-shadow">
                    <span><i class="fa fa-upload"></i> Upload File </span>
                    <input id="logo-id-2" name="logo" type="file" class="attachment_upload">
                  </div>
                </div>
              </div>
            </div>
                <div class="form-group">
               		<label for ="prodImg1">Immagine 3</label>	
                    <div class="main-img-preview">
                <img class="thumbnail img-preview" id="img-preview-3" src="./image?img-id=1" title="Preview Logo">
                    </div>
               <div class="input-group">
                <input id="fakeUploadLogo3" class="form-control fake-shadow" placeholder="Choose File" disabled="disabled">
                <div class="input-group-btn">
                  <div class="fileUpload btn btn-dark fake-shadow">
                    <span><i class="fa fa-upload"></i> Upload File </span>
                    <input id="logo-id-3" name="logo" type="file" class="attachment_upload">
                  </div>
                </div>
              </div>
            </div>
 
                <button type="submit" class="btn btn-primary btn-block">Save</button>
            </form>
        </div>
    </div>
</div>


<script src="./resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
 
    $('#tipoField').hide();
    $('#genereField').hide();
    $('#materialeField').hide();
    $('#pezzoField').hide();
    $('#utilizzoField').hide();

    $('#prodCategory').change(function() {
        var selectedCategory = $(this).val();

 
        $('#tipoField').hide();
        $('#genereField').hide();
        $('#materialeField').hide();
        $('#pezzoField').hide();
        $('#utilizzoField').hide();

        switch (selectedCategory) {
            case 'Abbigliamento':
                $('#tipoField').show();
                $('#genereField').show();
                break;
            case 'Armatura':
                $('#materialeField').show();
                $('#pezzoField').show();
                break;
            case 'Arma':
                $('#materialeField').show();
                $('#utilizzoField').show();
                break;

            default:
                break;
        }
    });
});
</script>
<script type="text/javascript">
var imgchange = {
		img1: false,
		img2: false,
		img3: false
}


$(document).ready(function() {
	 
	var brand1 = document.getElementById('logo-id-1');
	    brand1.className = 'attachment_upload';
	    brand1.onchange = function() {
	        document.getElementById('fakeUploadLogo1').value = this.value.substring(12);
	    };
	var brand2 = document.getElementById('logo-id-2');
	    brand2.className = 'attachment_upload';
	    brand2.onchange = function() {
	        document.getElementById('fakeUploadLogo2').value = this.value.substring(12);
	    };	 
	var brand3 = document.getElementById('logo-id-3');
	    brand3.className = 'attachment_upload';
	    brand3.onchange = function() {
	        document.getElementById('fakeUploadLogo3').value = this.value.substring(12);
	    };
	    
	    function readURL(input, previewId) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();
	            
	            reader.onload = function(e) {
	                $(previewId).attr('src', e.target.result);
	            };
	            reader.readAsDataURL(input.files[0]);
	        }
	    }
	    
	    $("#logo-id-1").change(function() {
	        readURL(this, '#img-preview-1');
	        imgchange.img1 = true;
	        $('#fakeUploadLogo1').val(this.value.substring(12));
	    });

	    $("#logo-id-2").change(function() {
	        readURL(this, '#img-preview-2');
	        imgchange.img2 = true;
	        $('#fakeUploadLogo2').val(this.value.substring(12));
	    });

	    $("#logo-id-3").change(function() {
	        readURL(this, '#img-preview-3');
	        imgchange.img3 = true; 
	        $('#fakeUploadLogo3').val(this.value.substring(12));
	    });
		
	});
	
</script>

<script type="text/javascript">
$(document).ready(function() {
	
    $('form').on('submit', function(event) {
        event.preventDefault();
        
        function sendImage(file, action, id) {
            var formData = new FormData();
            formData.append('file', file);
            formData.append('action', action);

            if (action === 'update') {
                formData.append('id', id);
            }

            $.ajax({
                url: '/upload', 
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response) {
               	if(response.id > 0 && response.id === id){
               		console.log("Upload dell' immagine " + response.filename + " avvenuto con successo.");
               	} else {
               		console.log("Upload dell'immagine annullato.");
               	}
               	return response.id;
                },
                error: function(xhr, status, error) {
                    console.error('Error uploading file:', error);
                    return 0;
                }
            });
        }

            // Function to handle form submission
            function submitForm() {
                // Prepare product data
                var formData = {
                    prodNome: $('#prodNome').val(),
                    prodGiacenza: $('#prodGiacenza').val(),
                    prodDescrizione: $('#prodDescrizione').val(),
                    prodPrezzo: $('#prodPrezzo').val(),
                    prodIVA: $('#prodIVA').val(),
                    prodCategory: $('#prodCategory').val(),
                    prodTipo: $('#prodTipo').val(),
                    prodGenere: $('#prodGenere').val(),
                    prodMateriale: $('#prodMateriale').val(),
                    prodPezzo: $('#prodPezzo').val(),
                    prodUtilizzo: $('#prodUtilizzo').val()
                };

                // Prepare FormData for images
                var formDataImages = new FormData();
                if (imgchange.img1) {
                    formDataImages.append('logo1', $('#logo-id-1')[0].files[0]);
                }
                if (imgchange.img2) {
                    formDataImages.append('logo2', $('#logo-id-2')[0].files[0]);
                }
                if (imgchange.img3) {
                    formDataImages.append('logo3', $('#logo-id-3')[0].files[0]);
                }

                // Combine product data and images data
                formData.images = formDataImages;

                // Submit combined data via AJAX
                $.ajax({
                    url: '/AdminControl',
                    type: 'POST',
                    data: JSON.stringify(formData),
                    contentType: 'application/json',
                    processData: false,
                    success: function(response) {
                        // Handle success response
                        console.log('Product and images uploaded successfully:', response);
                        // Optionally show success message or redirect
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        // Handle error
                        console.error('Error uploading product and images:', textStatus, errorThrown);
                        // Optionally show error message
                    }
                });
            }

            // Event listener for form submission
            $('#submitBtn').on('click', function() {
                submitForm();
            });

        });
        </script>
	
</script>

<%@ include file="admin_footer.jsp" %>

</body>

</html>
