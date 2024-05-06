<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<%@page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aggiungi Prodotto</title>
</head>
<body>
<%@ include file="admin_header.jsp" %>

<div align="center">
		<form action="/AdminControl" method="post">
			<table class="productTable">
				<thead>
					<tr>
						<th colspan="2">
							Nuovo Prodotto
						</th>
					</tr>
				</thead>
				<tr>
					<td>Nome</td>
					<td><input type="text" name="prodNome" size="20" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Giacenza</td>
					<td><input type="text" name="prodGiacenza" size="20" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Descrizione</td>
					<td><input type="text" name="prodDescrizione" size="20" class="productTextField"/></td>
				</tr>
				<tr>
					<td>Prezzo</td>
					<td><input type="text" name="prodPrezzo" size="20" class="productTextField"/></td>
				</tr>
				<tr>
					<td>IVA</td>
					<td><input type="text" name="prodIVA" size="20" class="productTextField"/></td>
				</tr>
				
				<tr>
				<td>Categoria</td>
				<td>
				<select name="prodCategory" id="prodCategory">        
                <option value="Abbigliamento" selected="true">Abbigliamento</option>
                <option value="Armatura" >Armatura</option>
                <option value="Arma">Arma</option>
                <option value="Accessorio">Accessorio</option>
        		</select>
        		</td>
				</tr>
				
				<tr>
					<td>Tipo</td>
					<td>
					  <div id="tipo">  <input type="text" name="prodTipo" size="20" class="productTextField"/>
              </div>
					</td>
				</tr>
				<tr>
					<td>Genere</td>
					<td>
					  <div id="genere">  <input type="text" name="prodGenere" size="20" class="productTextField"/>
              </div>
					</td>
				</tr>
				
				<tr>
					<td>Materiale</td>
					<td>
					  <div id="materialeContainer">  <input type="text" name="prodMateriale" size="20" class="productTextField"/>
              </div>
					</td>
				</tr>
				<tr>
					<td>Pezzo</td>
					<td>
					  <div id="pezzoContainer">  <input type="text" name="prodPezzo" size="20" class="productTextField"/>
              </div>
					</td>
				</tr>
				
				<tr>
					<td>Utilizzo</td>
					<td>
					  <div id="utilizzoContainer">  <input type="text" name="prodUtilizzo" size="20" class="productTextField"/>
              </div>
					</td>
				</tr>
			</table>
			<button class="actionBtn" style="margin-top:10px">Save</button>
		</form>
	</div>
</body>
<script src="/resources/js/add_custom.js" type="text/javascript"></script>
</html>