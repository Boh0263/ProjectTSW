package control;

import java.time.ZonedDateTime;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecensioneDAO;
import model.Recensione;

public class RecensioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public RecensioneControl() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String SanitizedEmail = sanitize(request.getParameter("Email"));
		int Votazione = Integer.parseInt(request.getParameter("Votazione"));
		String SanitizedCommento = sanitize(request.getParameter("Commento"));
		String Data_Recensione = ZonedDateTime.now().toString();
		
		Recensione rec = new Recensione(SanitizedEmail, Votazione, SanitizedCommento, Data_Recensione);
		
		try {
			int id = 0;
			if("POST".equals(action)) {
				id = RecensioneDAO.doSave(rec);
			}
			response.setStatus(HttpServletResponse.SC_OK);
			//if-else per successo ed errori ?
		} catch (Exception e) {
			response.setHeader("message","Errore interno al server");
		}
		
	}
	
	protected String sanitize(String input) {
		if (input == null) {
			return null;
		}
    
		StringBuilder sanitized = new StringBuilder();
		for (char c : input.toCharArray()) {
			switch (c) {
            	case '<':
            		sanitized.append("&lt;");
            		break;
            	case '>':
            		sanitized.append("&gt;");
            		break;
            	case '&':
            		sanitized.append("&amp;");
            		break;
            	case '"':
            		sanitized.append("&quot;");
            		break;
            	case '\'':
            		sanitized.append("&#x27;");
            		break;
            	case '/':
            		sanitized.append("&#x2F;");
            		break;
            	default:
            		sanitized.append(c);
            		break;
			}
		}
		return sanitized.toString();
	}
}