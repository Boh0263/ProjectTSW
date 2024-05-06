$(document).ready(function() {
          function updateProductForm() {
              var selectedCategory = $("#prodCategory").val();
              switch(selectedCategory) {
                  case "Abbigliamento":
                      $("#tipo, #genere").show();
                      $("#materialeContainer, #pezzoContainer, #tipoArmaContainer, #utilizzoContainer").hide();
                      break;
                  case "Armatura":
                      $("#materialeContainer, #pezzoContainer").show();
                      $("#tipo, #genere, #tipoArmaContainer, #utilizzoContainer").hide();
                      break;
                  case "Arma":
                      $("#materialeContainer, #tipoArmaContainer, #utilizzoContainer").show();
                      $("#tipo, #genere, #pezzoContainer").hide();
                      break;
                  case "Accessorio":
                      $("#materialeContainer").show();
                      $("#tipo, #genere, #pezzoContainer, #tipoArmaContainer, #utilizzoContainer").hide();
                      break;
              }
          }
          
          updateProductForm();
          
          $("#prodCategory").change(updateProductForm);
      });