package control;

import java.io.IOException;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/Registration.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 //parse the ajax request written in the registration.jsp file
	
		
		
	}

}
