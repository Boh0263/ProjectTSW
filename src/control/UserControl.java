package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class UserControl
 */
@WebServlet("/UserControl")
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserDAO udao = new UserDAOImp();
       
    public UserControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("./resources/pages/ImpostazioniUtente.jsp");
		try {
			request.setAttribute("user", udao.doRetrieveByKey(1));
			
		} catch (SQLException e) {
			
			
		}
		
		
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// accetta una request di tipo application json con parametri da modificare dell' utente.
		
	}

}
