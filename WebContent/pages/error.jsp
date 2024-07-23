<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<title>Errore</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/responsive.css">
</head>

<%@include file="./generic_header.jsp" %>


<div class="main_slider" style="background-image: url(resources/static/images/slider_1.jpg)">
	<div class="container fill_height">
		<div class="row align-items-center fill_height">
			<div class="col">
				<div class="main_slider_content">
					<h1>Si è verificato un errore!</h1>
					<p>
						<%
						Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
							String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
							
							if (requestUri == null) {
								requestUri = "Sconosciuto";
							}
							
							if (statusCode != null) {
								out.println("Codice errore: " + statusCode);
								switch (statusCode) {
									case 401:
										out.println("<p>Non autorizzato: Accesso negato.</p>");
										break;
									case 403:
										out.println("<p>Proibito: Non hai il permesso di accedere a questa risorsa.</p>");
										break;
									case 404:
										out.println("<p>Pagina non trovata: " + requestUri + "</p>");
										break;
									case 405:
										out.println("<p>Metodo non consentito.</p>");
										break;
									case 500:
										out.println("<p>Errore interno del server.</p>");
										break;
									case 503:
										out.println(
												"<p>Servizio non disponibile: Il server Ã¨ temporaneamente sovraccarico o in manutenzione.</p>");
										break;
									default:
										out.println("<p>Si è verificato un errore.</p>");
										break;
									}
								} else {
									out.println("<p>Si è verificato un errore</p>");
								}

								Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
								if (throwable != null) {
									log("Errore nel servire " + requestUri, throwable);
								}
						%>
					</p>
					<p>
						Per favore, torna alla <a href="<%= request.getContextPath() %>/">homepage</a>
						e riprova.
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="./generic_footer.jsp" %>
</html>