package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Prodotto;
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
               response.sendRedirect("./Prodotto/" + productName);
                return;
            }
        } else {
            try {
            	Object obj = (Prodotto) dao.doRetrieveByKey(productName);
            	System.out.println(obj.toString());
                request.setAttribute("prodotto", obj);
               // System.out.println( ((Prodotto) request.getAttribute("prodotto")).toString());
            } catch (Exception e) {
                if (e instanceof SQLException) {
                    throw new ServletException("Prodotto non trovato. :"+ e.getMessage());
                } else {
                    //throw new ServletException("Errore: " + e.getMessage());
                	e.printStackTrace();
                }
            }
        }
        
        String[] styles = {"./resources/styles/single_responsive.css", "./resources/styles/single_styles.css"};
        request.setAttribute("custom_styles", styles);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/singlea.jsp");
        dispatcher.forward(request, response);
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
