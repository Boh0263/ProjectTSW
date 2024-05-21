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
	doPost(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente u = new Utente();
		String Messaggio = "Registrazione avvenuta con successo";
		//TODO proteggere l'user input
		u.setNome(request.getParameter("nome"));
		u.setCognome(request.getParameter("cognome"));
		u.setEmail(request.getParameter("email"));
		u.setPassword(request.getParameter("password"));
		try {
			dao.doSave(u);
		} catch (SQLException e) {
			Messaggio = "Registrazione fallita, riprova o contatta l'assistenza";
		    e.printStackTrace(); //TODO log
		} finally {
			request.setAttribute("Messaggio", Messaggio);
			RequestDispatcher dispatcher = request.getRequestDispatcher("pages/login-alternative.jsp");
			dispatcher.forward(request, response);
		}
	
	
		
		
	}

}
