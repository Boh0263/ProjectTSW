package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import model.UserDaoImp;



@WebServlet("/verifyEmail")
public class EmailCheckControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDaoImp dao = new UserDaoImp();
       
   
    public EmailCheckControl() {
        super();
        
    }
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean emailExists = false;

	        try (BufferedReader reader = request.getReader()) {
	            JsonObject jsonRequest = JsonParser.parseReader(reader).getAsJsonObject();
	            String email = jsonRequest.get("email").getAsString();
		
		if (email == null || email.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email is required.");
            return;
        }
		
		try {
		emailExists = dao.doEmail(email);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(emailExists ? "true" : "false");
		} catch (SQLException e) {
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
		  }
		
		} catch (JsonSyntaxException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON.");
				return;
			}
		
	}
}
