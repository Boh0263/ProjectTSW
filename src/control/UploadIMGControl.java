package control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ImageDAO;
import model.Immagine;


@WebServlet("/upload")
public class UploadIMGControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UploadIMGControl() {
        super();
    }


	//TODO ERROR HANDLING doGet non disponibile per questa servlet.

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filename = request.getPart("file").getSubmittedFileName();
		InputStream filestream = request.getPart("file").getInputStream();
		
		byte[] Content = filestream.readAllBytes();
		Immagine img = new Immagine(filename, Content);
		
		try {
			
		ImageDAO.doSave(img);
		
		} catch(SQLException e) {
			
			throw new ServletException(e.getMessage());
			
		}
	}

}
