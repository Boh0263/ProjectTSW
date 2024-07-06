<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Controllo per verificare che l'utente abbia effettuato il login, altrimenti redirect ??-->

<%@ include file="generic_header.jsp"%>

	<div class="container">
		<div class="title">Inserisci recensione per il prodotto</div>
		<div class="content">
			<%
				String emailRec = null;
				String action = "OrderControl?action=aggiungi&codice=" + request.getParameter("codice");
				if (request.getParameter("emailRec") != null) {
					emailRec = request.getParameter("emailRec");
					action = "RecensioneControl?action=aggiungiMod&emailRec=" + emailRec + "&codice=" + request.getParameter("codice");
				}
			%>
			<form action="<%=action%>" METHOD="POST">
				<div class="user-details" style="display: block">
					<div class="input-box">
						<%
							if (request.getParameter("voto") != null) {			
						%>
						<span class="details">Votazione (da 1 a 10)</span>
						<input type="number" step="1" name="votazione" min="1" max="10" value="<%=Integer.parseInt(request.getParameter("voto"))%>" placeholder="1-10" required style="margin-bottom: 30px; width: 18%"/>
						<%
							} else {
						%>
						<span class="details">Votazione (da 1 a 10)</span>
						<input type="number" step="1" name="votazione" min="1" max="10" placeholder="1-10" required style="margin-bottom: 30px; width: 18%"/>
						<%
							}
						%>
					</div>
					<div class="input-box">
						<%
							if (request.getParameter("testo") != null) {			
						%>
						<span class="details">Testo (opzionale)</span>
						<textarea id="testo" name="testo" rows="4" cols="60" placeholder="Aggiungi testo della recensione (opzionale)..." style="resize: none; width: 600px; height: 160px" style="margin-bottom: 30px"><%=request.getParameter("testo")%></textarea> <!-- 93 -->
						<%
							} else {
						%>
						<span class="details">Testo (opzionale)</span>
						<textarea id="testo" name="testo" rows="4" cols="60" placeholder="Aggiungi testo della recensione (opzionale)..." style="resize: none; width: 600px; height: 160px" style="margin-bottom: 30px"></textarea> <!-- 93 -->
						<%
							}
						%>
					</div>
				</div>
				<div class="button">
					<input type="submit" class="recensione" name="recensione" id="recensione" value="Aggiungi recensione" style="margin-top: 80px; margin-bottom: 40px">
				</div>
			</form>
		</div>
	</div>
	
<%@ include file="generic_footer.jsp"%>