package control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ImageDAO;
import model.Immagine;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 16177215) // MAX SIZE 16MB (MEDIUMBLOB)
public class UploadIMGControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadIMGControl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String filename = request.getPart("file").getSubmittedFileName();
		String mimeType = request.getPart("file").getContentType();
		InputStream filestream = request.getPart("file").getInputStream();
		
		byte[] Content = filestream.readAllBytes();
		Immagine img = new Immagine(filename, Content, mimeType);
		
		try {
			
		int id = 0;
		if ("update".equals(action)) {
			
		int idtbe = Integer.parseInt(request.getParameter("id"));	
		id = ImageDAO.doUpdate(img, idtbe);
		
		} else if ("add".equals(action)) {
			
		id = ImageDAO.doSave(img);
		
		}
		
		response.setStatus(HttpServletResponse.SC_OK);

		if (id > 0) {
			String jsonResponse = "{\"filename\": \"" + filename + ", \"id\": " + id + "}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
	} else {
		response.getWriter().write("Errore nel salvataggio dell'immagine");
	}
} catch (IOException e) {

	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	response.getWriter().write("Errore nella lettura del file");
		
		} catch(SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Errore interno al server");
			
		}
	}

}
