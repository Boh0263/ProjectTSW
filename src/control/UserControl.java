package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.*;


@WebServlet("/Account")
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserDAO udao = new UserDAOImp();
       
    public UserControl() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("username") == null) {
			session.invalidate();
			response.sendRedirect("./login");
			return;
		}
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/ImpostazioniUtente.jsp");
		request.setAttribute("custom_styles", new String[] { "./resources/styles/main_styles.css","./resources/styles/responsive.css" });
		
		 String username = session.getAttribute("username").toString();
		 
		try {
	      Utente user = udao.doRetrieveByName(username);
	      request.setAttribute("user", user);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			session.invalidate();
			response.sendRedirect("./login");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		JsonObject jsonResponse = new JsonObject();
		PrintWriter out = response.getWriter();
		String requestype = request.getContentType();
		
		if (requestype != null && "application/json".equals(requestype)) {
			
		 try (BufferedReader reader = request.getReader()) {
			 
		 JsonElement jsonel = JsonParser.parseReader(reader);
		 JsonObject jsonRequest = null;
		 
		 if (jsonel.isJsonObject()) {
			jsonRequest = jsonel.getAsJsonObject();
			
			String username = jsonRequest.has("userName") ? jsonRequest.get("userName").getAsString() : null;
			String newPassword = jsonRequest.has("userNPassword") ? jsonRequest.get("userNPassword").getAsString() : null;
			String Nome = jsonRequest.has("userNome") ? jsonRequest.get("userNome").getAsString() : null;
			String Cognome = jsonRequest.has("userCognome") ? jsonRequest.get("userCognome").getAsString() : null;
			String Email = jsonRequest.has("userEmail") ? jsonRequest.get("userEmail").getAsString() : null;
			String CF = jsonRequest.has("userCF") ? jsonRequest.get("userCF").getAsString() : null;
			String Data_Nascita = jsonRequest.has("userDataNascita") ? jsonRequest.get("userDataNascita").getAsString() : null;
			String Telefono = jsonRequest.has("userTelefono") ? jsonRequest.get("userTelefono").getAsString() : null;
			String Via = jsonRequest.has("userVia") ? jsonRequest.get("userVia").getAsString() : null;
			String Citta = jsonRequest.has("userCitta") ? jsonRequest.get("userCitta").getAsString() : null;
			String CAP = jsonRequest.has("userCAP") ? jsonRequest.get("userCAP").getAsString() : null;
			String Provincia = jsonRequest.has("userProvincia") ? jsonRequest.get("userProvincia").getAsString() : null;
			
			Indirizzo indirizzo = new Indirizzo(Via, CAP, Citta, Provincia);
			
			try {
				
				Utente user = new Utente(
						username,
						newPassword,
						Nome,
						Cognome,
						Email,
						CF,
						"R",
						Data_Nascita,
						Telefono,
						indirizzo
						);
				
				result = udao.update(user);
				
					} catch (SQLException e) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						jsonResponse.addProperty("message", "Errore interno al server");
						out.println(jsonResponse.toString());
						return;
					}
		 } else {
             response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
             jsonResponse.addProperty("message", "Richiesta non valida");
             out.println(jsonResponse.toString());
             return;
		 }
		 
		 } catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			jsonResponse.addProperty("message", "Errore interno al server");
			out.println(jsonResponse.toString());
			return;
		}
	} else {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
	if (result > 0) {
	response.setStatus(HttpServletResponse.SC_OK);
	return;
  } else {
	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	return;
    }
  }
}
