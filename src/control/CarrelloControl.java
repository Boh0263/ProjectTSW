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

import model.*;

@WebServlet("/Carrello")
public class CarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static final ProdottoDAOImp pdao = new ProdottoDAOImp();
    
    public CarrelloControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setAttribute("custom_styles", new String[] {"./resources/styles/sc_styles.css", "./resources/styles/main_styles.css","./resources/styles/responsive.css"} );				
		  RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/shoppingcart.jsp");
		  dispatcher.forward(request, response);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		 HttpSession session = request.getSession(false);
	if( session != null && session.getAttribute("username") != null && session.getAttribute("role").equals("R") ) {
		if (action.equalsIgnoreCase("proceedToCheckout")) {
			session.setAttribute("carrello", session.getAttribute("carrello"));
			response.sendRedirect(request.getContextPath()+"/Checkout");
			
		} else if ( action.equalsIgnoreCase("addProduct") ) {
			  Carrello cart = (Carrello) session.getAttribute("carrello");
			  try {
			  if ( cart == null ) {
                  cart = new Carrello();
			  }   
			  	if( request.getParameter("prodotto") != null) {
            	  cart.addProduct(pdao.doRetrieveByKey(request.getParameter("prodotto")));
			  	}
            	  session.setAttribute("carrello", cart);
              
		} catch (SQLException e) {
			throw new ServletException(e.getMessage());
		 }
			  
	    } else if ( action.equalsIgnoreCase("removeProduct") ) {
	    	Carrello cart = (Carrello) session.getAttribute("carrello");
	    	try {
	    		if ( cart != null && request.getParameter("prodotto") != null ) {
	    			cart.removeProduct(pdao.doRetrieveByKey(request.getParameter("prodotto")));
	    		}
	    	} catch (SQLException e) {
	    		throw new ServletException(e.getMessage());
	    	}
	    }
	 }
  }
}
	