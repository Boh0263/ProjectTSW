package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottoDAOImp;


public class ProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ProdottoDAOImp dao = new ProdottoDAOImp();
       
    public ProdottoControl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = null;
        String pathInfo = request.getPathInfo();
        
        if (pathInfo != null) {
            productName = pathInfo.substring(1); 
        }

        // Se il nome del prodotto non è presente nell'URL lo si cerca nei parametri della richiesta.
        
        if (productName == null) {
            productName = request.getParameter("Nome");
            if (productName != null) {
                // Se il nome del prodotto è presente nei parametri della richiesta si reindirizza alla pagina del prodotto.
                response.sendRedirect(request.getContextPath() + "/ProdottoControl/" + productName);
                return;
            }
        } else {
            try {
                request.setAttribute("prodotto", dao.doRetrieveByKey(productName));
            } catch (Exception e) {
                if (e instanceof SQLException) {
                    throw new ServletException("Prodotto non trovato.");
                } else {
                    throw new ServletException("Errore: " + e.getMessage());
                }
            }
        }
        String[] styles = {"./resources/styles/single_responsive.css", "./resources/styles/single_styles.css"};
        request.setAttribute("custom_styles", styles);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/single.jsp");
        dispatcher.forward(request, response);
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
