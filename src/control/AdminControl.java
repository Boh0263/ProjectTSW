package control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet("/AdminControl")
public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProdottoDAOImp dao = new ProdottoDAOImp();
       
  
    public AdminControl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {
    	    String action = request.getParameter("action");
    	    RequestDispatcher rd = null;
    	    if (action != null) {
    	      rd = request.getRequestDispatcher(getServletContext().getRealPath("/" + action + ".jsp"));
    	      rd.forward(request, response);
    	    } else {
    	      rd = request.getRequestDispatcher(getServletContext().getRealPath("/AdminHome.jsp"));
    	      rd.forward(request, response);
    	    }
    	  }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	      throws ServletException, IOException {
    	    String action = request.getParameter("action");
    	    if (action != null) {
    	      switch (action) {
    	        case "edit":
    	          processEdit(request, response);
    	          break;
    	        case "add":
    	          processAdd(request, response);
    	          break;
    	        case "delete":
    	          processDelete(request, response);
    	          break;
    	        case "view":
    	          processView(request, response);
    	          break;
    	        case "search":
    	          processSearch(request, response);
    	          break;
    	        default:
    	        	processError(request, response);
    	        	break;
    	      }
    	    } else {
    	      doGet(request, response);
    	    }
    }
    
	protected void processEdit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		Prodotto tbe = null;
		try {
			tbe = parseProdotto(req);
			dao.update(tbe);
		} catch (SQLException e) {
			System.out.println("Errore: "+ e.getMessage()); //Handle Error with redirection to jsp error page with Dispatcher
		}
	}
	
	protected void processAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Prodotto tba = null;
		try {
			tba = parseProdotto(req);
			dao.doSave(tba);
		} catch(SQLException e) {
			System.out.println("Errore: "+ e.getMessage()); //Handle Error with redirection to jsp error page with Dispatcher
		}
	}
	
	protected void processDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			dao.doDeletebyID(req.getParameter("prodNome"));
		} catch (SQLException e) {
			System.out.println("Errore: "+ e.getMessage()); //Handle Error with redirection to jsp error page with Dispatcher
		}
	}
	
	protected void processView(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		try {
			req.removeAttribute("Prodotto");
			if(req.getParameter("sort").isEmpty()){
			req.setAttribute("Prodotto", dao.doretrieveAll(null));
			} else {
				req.setAttribute("Prodotto", dao.doretrieveAll(req.getParameter("sort")));
			}
		} catch(SQLException e ) {
			System.out.println("Errore: "+ e.getMessage()); //Handle Error with redirection to jsp error page with Dispatcher
		}
	}
	
	protected void processSearch(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
		try {
			req.removeAttribute("Prodotto");
			req.setAttribute("Prodotto", dao.doRetrieveByKey(req.getParameter("prodNome")));
			
		} catch(SQLException e ) {
			System.out.println("Errore: "+ e.getMessage()); //Handle Error with redirection to jsp error page with Dispatcher
		}
	}
	
	protected void processError(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//TODO
	}
	
	private Prodotto parseProdotto(HttpServletRequest req) throws ServletException, IOException {
			  Enumeration<String> parameterNames = req.getParameterNames();

			  String productCategoryParam = null;
			  while (parameterNames.hasMoreElements()) {
			    String paramName = parameterNames.nextElement();
			    if (paramName.startsWith("prodCategory")) {
			      productCategoryParam = paramName;
			      break;
			    }
			  }

			  if (productCategoryParam == null) {
			    throw new IOException("Missing product category parameter (prodCategory)");
			  }

			  String productCategory = req.getParameter(productCategoryParam);
			  Prodotto product;
			  switch (productCategory) {
			    case "Abbigliamento":
			      product = new Abbigliamento();
			      break;
			    case "Armatura":
			      product = new Armatura();
			      break;
			    case "Arma":
			      product = new Arma();
			      break;
			    case "Accessorio":
			      product = new Accessorio();
			      break;
			    default:
			      throw new IOException("Unsupported product category: " + productCategory);
			  }

			  while (parameterNames.hasMoreElements()) {
			    String paramName = parameterNames.nextElement();
			    if (paramName.startsWith("prod") && !paramName.equals(productCategoryParam)) {
			      String value = req.getParameter(paramName);
			      String attributeName = paramName.substring(4); 

			      try {
			        Class<?> productClass = product.getClass();
			        String getterName = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
			        String setterName = "set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);

			        Method getter = productClass.getMethod(getterName);

			        Method setter = productClass.getMethod(setterName, getter.getReturnType());
			        setter.invoke(product, value);
			      } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			        throw new IOException("Error setting product attribute: " + attributeName, e);
			      }
			    }
			  }
			  
			return product;
	}
	

}
