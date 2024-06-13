package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDaoImp;
import model.Utente;

/**
 * Servlet implementation class RegistrationControl
 */
@WebServlet("/register")
public class RegistrationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDaoImp dao = new UserDaoImp();
       
    public RegistrationControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/Registration.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = null;
		String Messaggio = "Registrazione avvenuta con successo";
		//xhr.send("username=" + username + "&email=" + email + "&password=" + password + "&confirmPassword=" + confirmPassword + "&Nome=" + firstName + "&Cognome=" + lastName + "&Indirizzo=" + address + "&CF=" + CF + "&dataNascita=" + dataNascita + "&telefono=" + telefono);
		u = new Utente(-1,
				request.getParameter("username"),
				request.getParameter("password"),
				request.getParameter("nome"),
				request.getParameter("cognome"),
				request.getParameter("CF"),
				request.getParameter("email"));
		try {
			dao.doSave(u);
		} catch (SQLException e) {
			Messaggio = "Registrazione fallita, riprova o contatta l'assistenza";
		    e.printStackTrace(); //TODO log
		} finally {
			request.setAttribute("Messaggio", Messaggio);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login-alternative.jsp");
			dispatcher.forward(request, response);
		}
	
	
		
		
	}

}
