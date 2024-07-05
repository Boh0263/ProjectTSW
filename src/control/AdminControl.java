package control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProdottoDAOImp pdao = new ProdottoDAOImp();
	static OrdineDAOImp odao = new OrdineDAOImp();
    static UserDAOImp udao = new UserDAOImp();
       
  
    public AdminControl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    String action = request.getParameter("action");
    	    RequestDispatcher rd = null;
    	    if (action != null) {
    	      request.setAttribute("custom_styles", new String[] { "./resources/styles/admin_style.css"} );
    	      
				switch (action) {
				case "searchProduct": {
					try {
						request.setAttribute("prodAll", pdao.doretrieveAll(null));
					} catch (SQLException e) {
						System.out.println("Errore: " + e.getMessage()); 
					}
					rd = getServletContext().getRequestDispatcher("/pages/A-pages/searchProduct.jsp");
					break;
				}
					
				case "addProduct": {
					rd = getServletContext().getRequestDispatcher("/pages/A-pages/add.jsp");
					break;
				}
				
				case "editProduct": {
					String  name = request.getParameter("prodName");
					if (name != null) {
					try {
						request.setAttribute("prodtbe", pdao.doRetrieveByKey(name));
						rd = getServletContext().getRequestDispatcher("/pages/A-pages/editProduct.jsp");
					 } catch (SQLException e) {
						System.out.println("Errore: " + e.getMessage()); 
						rd = getServletContext().getRequestDispatcher("/pages/A-pages/searchProduct.jsp");
					 }
				  } else {
					    rd = getServletContext().getRequestDispatcher("/pages/A-pages/AdminHome.jsp");
				  }
					break;
				}
				
				case "editOrder": {
					String id = request.getParameter("ordID");
					if (id != null && !id.isEmpty()) {
					try {
						request.setAttribute("ordtbe", odao.doRetrieveByKey(Integer.parseInt(id)));
						rd = getServletContext().getRequestDispatcher("/pages/A-pages/editOrder.jsp");
					  } catch (SQLException e) {
						  System.out.println("Errore: " + e.getMessage()); 
						  rd = getServletContext().getRequestDispatcher("/pages/A-pages/searchOrder.jsp");
					   }
				    } else {
					    rd = getServletContext().getRequestDispatcher("/pages/A-pages/AdminHome.jsp");
				    }
					break;
				}
				case "searchOrder": {
					 try {
						 request.setAttribute("ordAll", odao.doretrieveAll(null));
						} catch (SQLException e) {
							System.out.println("Errore: " + e.getMessage()); 
							rd = getServletContext().getRequestDispatcher("/pages/A-pages/AdminHome.jsp");
						}
					rd = getServletContext().getRequestDispatcher("/pages/A-pages/searchOrder.jsp");
					break;
				}
				
				case "searchUser": {
					try {
						request.setAttribute("userAll", udao.doretrieveAll(null));
					} catch (SQLException e) {
						System.out.println("Errore: " + e.getMessage()); 
						rd = getServletContext().getRequestDispatcher("/pages/A-pages/AdminHome.jsp");
					}
					rd = getServletContext().getRequestDispatcher("/pages/A-pages/searchUser.jsp");
					break;
				}
				
				case "editUser": {
					String userID = request.getParameter("userID");
					if (userID != null && !userID.isEmpty()) {
						try {
							request.setAttribute("usertbe", udao.doRetrieveByKey(Integer.parseInt(userID)));
							rd = getServletContext().getRequestDispatcher("/pages/A-pages/editUser.jsp");
						} catch (SQLException e) {
							System.out.println("Errore: " + e.getMessage()); 
							rd = getServletContext().getRequestDispatcher("/pages/A-pages/searchUser.jsp");
						}
					} else {
						rd = getServletContext().getRequestDispatcher("/pages/A-pages/AdminHome.jsp");
					}
					break;
				}
				
				default:
					rd = getServletContext().getRequestDispatcher("/pages/A-pages/AdminHome.jsp");
					break;
				}
				
				rd.forward(request, response);
    	      
    	    } else {
    	      request.setAttribute("custom_styles", new String[] { "./resources/styles/admin_style.css"} );
    	      rd = getServletContext().getRequestDispatcher("/pages/A-pages/AdminHome.jsp");
    	      rd.forward(request, response);
    	    }
    	  }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    
    	String action = request.getParameter("action");
    	
    	    if (action != null) {
    	    	
    	      switch (action) {
    	      
    	        case "editProduct":
    	          processEditProduct(request, response);
    	          break;
    	        case "addProduct":
    	          processAddProduct(request, response);
    	          break;
    	        case "deleteProduct":
    	          processDeleteProduct(request, response);
    	          break;
    	        case "editOrder":
    	          processEditOrder(request, response);
    	           break;
				case "deleteOrder":
				  processDeleteOrder(request, response);
					break;
				case "editUser":
					processEditUser(request, response);
					break;
				case "deleteUser":
					processDeleteUser(request, response);
					break;	
    	        default:
    	        	processError(request, response);
    	        	break;
    	      }
    	    } else {
    	      doGet(request, response);
    	    }
    }
    
	protected void processEditProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		
		
	}
	
	protected void processAddProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	
	protected void processDeleteProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			pdao.doDeletebyID(req.getParameter("prodNome"));
		} catch (SQLException e) {
			System.out.println("Errore: "+ e.getMessage()); //Handle Error with redirection to jsp error page with Dispatcher
		}
	}
	
	protected void processEditOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	
	protected void processDeleteOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	protected void processEditUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	
	protected void processDeleteUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	protected void processError(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//TODO
		return;
	}
	
	/*private Prodotto parseProdotto(HttpServletRequest req) throws ServletException, IOException {
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
	*/

}
