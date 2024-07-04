package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;


public class OrdineControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static OrdineDAOImp odao;   
    private static UserDAOImp udao;

    public OrdineControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() throws ServletException {
		super.init();
		odao = new OrdineDAOImp();
		udao = new UserDAOImp();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/pages/Ordine.jsp");
		String [] custom_styles = {"./resources/styles/main_styles.css", "./resources/styles/responsive.css"}; //TODO add custom styles
		request.setAttribute("custom_styles", custom_styles);
		doPost(request, response); //TODO check if it's correct (it was missing in the original code)
 		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");
		if (username != null && role.equalsIgnoreCase("R")) {
			try {
			 Utente user = udao.doRetrieveByKey(username);
			 Collection<Ordine> ordini = odao.doRetrieveByUser(user);
			 session.setAttribute("ordine", ordini);
			}
			catch (SQLException e) {
                throw new ServletException("Errore: " + e.getMessage()); 
            }
			
		}
		
	}

}
