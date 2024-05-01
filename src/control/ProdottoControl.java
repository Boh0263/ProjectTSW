package control;

import model.ProdottoDAOImp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProdottoControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static ProdottoDAOImp model = new ProdottoDAOImp();

	public ProdottoControl() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String categoria = req.getParameter("category");

		try {
			if (categoria != null) {
				req.removeAttribute("Prodotto");
				if (categoria.equalsIgnoreCase("Abbigliamento")) {
				
						req.setAttribute("Prodotto",
								model.doRetrieveByCategory("Abbigliamento"));

				} else if (categoria.equalsIgnoreCase("Arma")) {
					
						req.setAttribute("Prodotto",
								model.doRetrieveByCategory("Arma"));

				} else if (categoria.equalsIgnoreCase("Armatura")) {
						req.setAttribute("Prodotto",
								model.doRetrieveByCategory("Armatura"));
					

				} else if (categoria.equalsIgnoreCase("Accessorio")) {
						req.setAttribute("Prodotto",
								model.doRetrieveByCategory("Accessorio"));
		
					

				} else if (categoria.equalsIgnoreCase("All")) {
					req.setAttribute("Prodotto", model.doretrieveAll(null));

				}
			}
		} catch (SQLException e) {
			System.out.println("Errore: " + e.getMessage()); //fare la req.getRequestDispatcher("directory pagina di errore");
			throw new ServletException();
		}

		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/pages/Catalogo.jsp");
		dispatch.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
