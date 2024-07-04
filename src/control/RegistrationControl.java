package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.Map;
import java.util.UUID;

import model.Indirizzo;


import model.UserDAOImp;
import model.Utente;

/**
 * Servlet implementation class RegistrationControl
 */
@WebServlet("/register")
public class RegistrationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDAOImp dao = new UserDAOImp();
	private Gson gson = new Gson();
       
    public RegistrationControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
    	session.setAttribute("ctoken", generateToken());
    	session.setMaxInactiveInterval(300);
    	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/Registration.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JsonObject jsonResponse = new JsonObject();
        
        try (BufferedReader reader = request.getReader()) {
        	JsonElement jsonElement =  JsonParser.parseReader(reader);
        	
        	if (jsonElement.isJsonObject()) {
            JsonObject jsonRequest = jsonElement.getAsJsonObject();
            
            String username = jsonRequest.get("username").getAsString();
            String email = jsonRequest.get("email").getAsString();
            String password = jsonRequest.get("password").getAsString();
            String firstName = jsonRequest.get("Nome").getAsString();
            String lastName = jsonRequest.get("Cognome").getAsString();
            String address = jsonRequest.get("Indirizzo").getAsString();
            String CF = jsonRequest.get("CF").getAsString();
            String dataNascita = jsonRequest.get("dataNascita").getAsString();
            String telefono = jsonRequest.get("telefono").getAsString();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() ||
                firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() ||
                CF.isEmpty() || dataNascita.isEmpty() || telefono.isEmpty()) {

                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Registrazione fallita: Campi non validi");
            } else {
                boolean isRegistered = dao.doSave(new Utente(
                        username,
                        password,
                        firstName,
                        lastName,
                        CF,
                        email,
                        "R",
                        dataNascita,
                        telefono,
                        new Indirizzo(address)
                		));

                if (isRegistered) {
                    jsonResponse.addProperty("success", true);
                    jsonResponse.addProperty("message", "Registrazione avvenuta con successo");
                } else {
                    jsonResponse.addProperty("success", false);
                    jsonResponse.addProperty("message", "Registrazione fallita: Username o Email già in uso");
                }
            }
        } else {
			jsonResponse.addProperty("success", false);
			jsonResponse.addProperty("message", "Registrazione fallita: Invalid JSON format");
        }
        	
        } catch (JsonSyntaxException e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Registrazione fallita: Invalid JSON format");
            e.printStackTrace();
        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Registrazione fallita: Network Error");
            e.printStackTrace();
        }

        out.print(gson.toJson(jsonResponse));
        out.flush();
	}
	
	public String generateToken() {
        return UUID.randomUUID().toString();
	}
		
		
	}


