package control;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecensioneDAO;
import model.SanitizeInput;
import model.Recensione;
@WebServlet("/review")
public class RecensioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public RecensioneControl() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String SanitizedEmail = SanitizeInput.sanitize(request.getParameter("Email"));
		int Votazione = Integer.parseInt(request.getParameter("Votazione"));
		String SanitizedCommento = SanitizeInput.sanitize(request.getParameter("Commento"));
		String SanitizedIDProdotto = SanitizeInput.sanitize(request.getParameter("Prodotto"));
		String Data_Recensione = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Recensione rec = new Recensione(SanitizedEmail, Votazione, SanitizedCommento, Data_Recensione, SanitizedIDProdotto);
		
		try {
		    int id = 0;
			if("POST".equals(action)) {
				id = RecensioneDAO.doSave(rec);
				if(id > 0) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.setHeader("message","Recensione inserita con successo");
				response.getWriter().write(Integer.toString(id));
				return;
                } else {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setHeader("message","Errore interno del server");
				return;
			}
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.setHeader("message","Richiesta non valida");
				return;
			}
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setHeader("message","Errore interno al server");
			return;
		}
		
	}
	
	
}