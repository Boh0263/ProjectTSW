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
					//try {
						req.setAttribute("Prodotto",
								model.doRetrieveByCategory("Abbigliamento"));/*Class<? extends Prodotto>) Class.forName("Abbigliamento")*/
				//	} catch (ClassNotFoundException e) {
					//	System.out.println("Errore: " + e.getMessage());
				//	

				} else if (categoria.equalsIgnoreCase("Arma")) {
					//try {
						req.setAttribute("Prodotto",
								model.doRetrieveByCategory("Arma"/* (Class<? extends Prodotto>) Class.forName("Arma") */));
					//} catch (ClassNotFoundException e) {
					//	System.out.println("Errore: " + e.getMessage());
					

				} else if (categoria.equalsIgnoreCase("Armatura")) {
					//try {
						req.setAttribute("Prodotto",
								model.doRetrieveByCategory("Armatura"/* (Class<? extends Prodotto>) Class.forName("Armatura") */));
				//	} catch (ClassNotFoundException e) {
					//	System.out.println("Errore: " + e.getMessage());
					

				} else if (categoria.equalsIgnoreCase("Accessorio")) {
					//try {
						req.setAttribute("Prodotto",
								model.doRetrieveByCategory("Accessorio"/*
															 * (Class<? extends Prodotto>)
															 * Class.forName("Abbigliamento")
															 */));
			//		} catch (ClassNotFoundException e) {
				//		System.out.println("Errore: " + e.getMessage());
					

				} else if (categoria.equalsIgnoreCase("All")) {
					req.setAttribute("Prodotto", model.doretrieveAll(null));

				}
			}
		} catch (SQLException e) {
			System.out.println("Errore: " + e.getMessage());
			throw new ServletException();
		}

		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/pages/Catalogo.jsp");
		dispatch.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
