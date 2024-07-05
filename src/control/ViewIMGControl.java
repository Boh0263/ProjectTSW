package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

import model.ImageDAO;
import model.Immagine;


@WebServlet("/image")
public class ViewIMGControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ViewIMGControl() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (request.getParameter("img-id") == null || !request.getParameter("img-id").matches("\\d+")) { //matches("\\d+") controlla che la stringa sia un numero
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Errore: ID immagine errato");
			return;
		}
		
		int ID = Integer.parseInt(request.getParameter("img-id"));
		
		try {
				Immagine img = ImageDAO.doRetrieveByKey(ID);
				
				if (img == null /*Controllo sui permessi di accesso img HERE*/) {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					response.getWriter().write("Errore: immagine non trovata");
					return;
				}
				
				response.setContentType(img.getMimeType().getMimeType());
				response.setContentLength(img.getContent().length);
				response.setHeader("Content-Disposition", "inline; filename=\"" + img.getPlaceholder() + "\""); 
		
				//Nel model chiamo il nome del file di un Immagine Placeholder invece che filename.
				//Content-Disposition è un header HTTP che fornisce il nome del file o l'identificatore del file come suggerimento per il download del file. è un layer di sicurezza per evitare che il file venga eseguito automaticamente.
				
				try (OutputStream out = response.getOutputStream()) {
					out.write(img.getContent());
					out.close();
				}
				
			} catch (SQLException e) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.getWriter().write("Errore interno al server");
				}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
