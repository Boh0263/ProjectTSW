package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.PrintWriter;
import java.io.BufferedReader;

import model.UserDaoImp;

/**
 * Servlet implementation class UsernameCheckControl
 */
@WebServlet("/verifyUsername")
public class UsernameCheckControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDaoImp dao = new UserDaoImp();
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsernameCheckControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        JsonObject jsonResponse = new JsonObject();

	        try (BufferedReader reader = request.getReader()) {
	            JsonObject jsonRequest = JsonParser.parseReader(reader).getAsJsonObject();
	            String username = jsonRequest.get("username").getAsString();

	            // Check if the username is available
	            boolean isAvailable = !dao.doCheck(username);

	            jsonResponse.addProperty("available", isAvailable);
	        } catch (JsonSyntaxException e) {
	            jsonResponse.addProperty("available", false);
	            jsonResponse.addProperty("error", "Invalid JSON format");
	            e.printStackTrace();
	        } catch (Exception e) {
	            jsonResponse.addProperty("available", false);
	            jsonResponse.addProperty("error", "An unexpected error occurred");
	            e.printStackTrace();
	        }

	        try (PrintWriter out = response.getWriter()) {
	            out.print(gson.toJson(jsonResponse));
	            out.flush();
	        }
	    }
}


