package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import model.*;

public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ProdottoDAOImp pdao = new ProdottoDAOImp();
	static OrdineDAOImp odao = new OrdineDAOImp();
    static UserDAOImp udao = new UserDAOImp();
    
    private Gson gson = new Gson();
       
  
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
					rd = getServletContext().getRequestDispatcher("/pages/A-pages/addProduct.jsp");
					break;
				}
				
				case "editProduct": {
					String  name = request.getParameter("prodName");
					if (name != null) {
					try {
						request.setAttribute("prodtbe", (Prodotto) pdao.doRetrieveByKey(name));
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
		
		String action = null;
		boolean test = false;
		
		String tiporequest = request.getContentType();
		JsonObject jsonResponse = new JsonObject();
		PrintWriter out = response.getWriter();
		System.out.println(tiporequest);
		if (tiporequest != null && tiporequest.equals("application/json")) {
			System.out.println("JSON");
	    response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
         try (BufferedReader reader = request.getReader()) {
         	JsonElement jsonElement =  JsonParser.parseReader(reader);
         	JsonObject jsonRequest = null;
         	
         	if (jsonElement.isJsonObject()) {
             jsonRequest = jsonElement.getAsJsonObject();
             action = jsonRequest.has("action") ? jsonRequest.get("action").getAsString() : null;
         	} 
         	
         	if (action != null) {
    	    	
    	    	
    	      switch (action) {
    	      
    	        case "editProduct":
    	          test = processEditProduct(jsonRequest, response);
    	          break;
    	        case "addProduct":
    	          test = processAddProduct(jsonRequest, response);
    	          break;
    	        case "editOrder":
    	          processEditOrder(jsonRequest, response);
    	           break;
				case "editUser":
				  test = processEditUser(jsonRequest, response);
					break;
    	        default:
    	        	processError(request, response);
    	        	break;
    	      }
    	      
    	      if(test) {
    	    	  jsonResponse.addProperty("success", true);
    	    	  jsonResponse.addProperty("message", "Operazione eseguita con successo");
    	    	  out.print(gson.toJson(jsonResponse));
      	    	  out.flush();
      	    	  } else {
      	    	  jsonResponse.addProperty("success", false);
      	    	  jsonResponse.addProperty("message", "Operazione fallita");
      	    	  out.print(gson.toJson(jsonResponse));
      	    	  out.flush();
      	    	  }
    	   
    	    } else {
    	      doGet(request, response);  
    	    } } catch (JsonSyntaxException e) {
 
         		jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Errore: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: " + e.getMessage());
                out.print(gson.toJson(jsonResponse));
                out.flush();
                return;
         	} 
         
         } else if ( tiporequest != null && tiporequest.equals("application/x-www-form-urlencoded") && request.getParameter("action") != null) {
         		
        	 action = request.getParameter("action");
         		
         		switch (action) {
         		case "addProduct":
         		case "editProduct":
         		case "addOrder":
         		case "editOrder":
         		case "editUser": {
         			jsonResponse.addProperty("success", false);
         			jsonResponse.addProperty("message", "Errore: Richiesta non valida");
         			out.print(gson.toJson(jsonResponse));
         			out.flush();
         			return;
         		}
         		case "deleteProduct": {
					test = processDeleteProduct(request, response);
					break;
         		}
				case "deleteOrder": {
					processDeleteOrder(request, response);
					break;
				}
				case "deleteUser": {
					test = processDeleteUser(request, response);
					break;
				}
         		default: break;
         	  }
         		
         		
         	if(test) {
       	    	  jsonResponse.addProperty("success", true);
       	    	  jsonResponse.addProperty("message", "Operazione eseguita con successo");
       	    	  out.print(gson.toJson(jsonResponse));
         	      out.flush();
         	} else {
         	      jsonResponse.addProperty("success", false);
         	      jsonResponse.addProperty("message", "Operazione fallita");
         	      out.print(gson.toJson(jsonResponse));
         	      out.flush();
         	     }
         		
         	}
    
    	
    	   
    	  
         
         }
         		
    
    
	protected boolean processEditProduct(JsonObject jsonRequest, HttpServletResponse res) throws ServletException, IOException {
		boolean result = false;
		System.out.println(jsonRequest);
		String nome = jsonRequest.has("prodNome") ? jsonRequest.get("prodNome").getAsString() : null;
		//il nome del prodotto è la chiave primaria, quindi obbligatorio.
		if (nome == null) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: Nome del prodotto non valido");
			processError(null, res);
			return false;
		}
		
	    Prodotto prod = null;
	    
	            try {
					prod = pdao.doRetrieveByKey(nome);
					if (prod == null) {
						res.sendError(HttpServletResponse.SC_NOT_FOUND, "Errore: Prodotto non trovato");
						processError(null, res);
						return false;
					}
				} catch (SQLException e) {
					res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore: "+ e.getMessage());
					processError(null, res);
					return false;
				}
	            
	   String nomeNew = jsonRequest.has("prodNomeNew") ? jsonRequest.get("prodNomeNew").getAsString() : null;         
	   Double Prezzo = jsonRequest.has("prodPrezzo") ? jsonRequest.get("prodPrezzo").getAsDouble() : null; 
	   Double IVA = jsonRequest.has("prodIVA") ? jsonRequest.get("prodIVA").getAsDouble() : null;
	   String Descrizione = jsonRequest.has("prodDesc") ? jsonRequest.get("prodDesc").getAsString() : null;
	   Integer Giacenza = jsonRequest.has("prodGiac") ? jsonRequest.get("prodGiac").getAsInt() : null;
	   Integer img1 = jsonRequest.has("prodImg1") ? jsonRequest.get("prodImg1").getAsInt() : null;
	   Integer img2 = jsonRequest.has("prodImg2") ? jsonRequest.get("prodImg2").getAsInt() : null;
	   Integer img3 = jsonRequest.has("prodImg3") ? jsonRequest.get("prodImg3").getAsInt() : null;
	   String Categoria = jsonRequest.has("prodCategory") ? jsonRequest.get("prodCat").getAsString() : "";
	   
	    switch (Categoria) {
	   
	    case "Abbigliamento":
	    case "Armatura":
	    case "Arma":
	    case "Accessorio": {
	    	if (prod.getClass().getSimpleName().equals(Categoria)) {
        		//aggiornamento del prodotto
        		if (nomeNew == null || prod.getNome().equals(nomeNew)) {
        		try {
        		
        		prod.setPrezzo(Prezzo);
        		prod.setIVA(IVA);
        		prod.setDescrizione(Descrizione);
        		prod.setGiacenza(Giacenza);
        		prod.setImg1(img1);
        		prod.setImg2(img2);
        		prod.setImg3(img3);
        		
        		pdao.update(prod);
        		
        		} catch (SQLException e) {
        			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore: "+ e.getMessage());
        			processError(null, res);
        			return false;
        		}
        		
        	} else {
        			
          			//Cambio di categoria del prodotto.
        		
        			try {
        				
        				pdao.doDeletebyID(prod.getNome());
        				
        				switch (Categoria) {
        				case "Abbigliamento":
        					prod = new Abbigliamento(nomeNew);
        					((Abbigliamento) prod).setTipo(jsonRequest.has("prodTipo") ? jsonRequest.get("Tipo").getAsString() : null);
        					((Abbigliamento) prod).setGenere(jsonRequest.has("prodGenere") ? jsonRequest.get("Genere").getAsString() : null);
        					break;
        				case "Armatura":
        					prod = new Armatura(nomeNew);
        					((Armatura) prod).setMateriale(jsonRequest.has("prodMateriale") ? jsonRequest.get("Materiale").getAsString() : null);
        					((Armatura) prod).setPezzo(jsonRequest.has("prodPezzo") ? jsonRequest.get("Peso").getAsString() : null);
        					break;
        				case "Arma":
        					prod = new Arma(nomeNew);
        					((Arma) prod).setTipo(jsonRequest.has("prodTipo") ? jsonRequest.get("prodTipo").getAsString() : null);
        					((Arma) prod).setUtilizzo(jsonRequest.has("prodUtilizzo") ? jsonRequest.get("prodUtilizzo").getAsString() : null);
        					break;
        				case "Accessorio":
        					prod = new Accessorio(nomeNew);
        					break;
        					
        				default: break;
        				}
        				
        				prod.setPrezzo(Prezzo);
        				prod.setIVA(IVA);
        				prod.setDescrizione(Descrizione);
        				prod.setGiacenza(Giacenza);
        				prod.setImg1(img1);
        			    prod.setImg2(img2);
        			    prod.setImg3(img3);
        			    
        				 result = pdao.doSave(prod); //TODO overload con connection
        				 System.out.println(result + " " + prod.getNome());
        	
        				
        			} catch (SQLException e) {
        				res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore: "+ e.getMessage());
        				processError(null, res);
        				return false;
        			}
        		
        		
        		}
	    	
	    	}
	    }
	    
	    default: {
	    	
	    	prod.setPrezzo(Prezzo);
	    	prod.setIVA(IVA);
	    	prod.setDescrizione(Descrizione);
	    	prod.setGiacenza(Giacenza);
	    	prod.setImg1(img1);
	    	prod.setImg2(img2);
	    	prod.setImg3(img3);
	    	
	    	
	    	switch (prod.getClass().getSimpleName()) {
	    	case "Abbigliamento": {
	    		((Abbigliamento) prod).setTipo(jsonRequest.has("prodTipo") ? jsonRequest.get("Tipo").getAsString() : null);
	    		((Abbigliamento) prod).setGenere(jsonRequest.has("prodGenere") ? jsonRequest.get("Genere").getAsString() : null);
	    		break;
	    	}
	    	case "Armatura": {
	    		((Armatura) prod).setMateriale(jsonRequest.has("prodMateriale") ? jsonRequest.get("Materiale").getAsString() : null);
            	((Armatura) prod).setPezzo(jsonRequest.has("prodPezzo") ? jsonRequest.get("Pezzo").getAsString() : null);
            	break;
	    	}
	        case "Arma": {
				((Arma) prod).setTipo(jsonRequest.has("prodTipo") ? jsonRequest.get("Tipo").getAsString() : null);
				((Arma) prod).setUtilizzo(jsonRequest.has("prodUtilizzo") ? jsonRequest.get("Utilizzo").getAsString() : null);
				break;
	        }
	        case "Accessorio": {
	        	//nessun attributo da aggiornare.
	        	break;
	        }
	        default: break;
	    }
	    	 
		try {
			
			pdao.update(prod);
			
		} catch (SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore: " + e.getMessage());
			processError(null, res);
			return false;
		    }
		return result;
	    } 
	    
	    }
}
	    
		
		
		
		
	
	
	protected boolean processAddProduct(JsonObject  jsonRequest, HttpServletResponse res) throws ServletException, IOException {
		   
		   String Nome = jsonRequest.has("prodNomeNew") ? jsonRequest.get("prodNomeNew").getAsString() : null;         
		   Double Prezzo = jsonRequest.has("prodPrezzo") ? jsonRequest.get("prodPrezzo").getAsDouble() : null; 
		   Double IVA = jsonRequest.has("prodIVA") ? jsonRequest.get("prodIVA").getAsDouble() : null;
		   String Descrizione = jsonRequest.has("prodDesc") ? jsonRequest.get("prodDesc").getAsString() : null;
		   Integer Giacenza = jsonRequest.has("prodGiac") ? jsonRequest.get("prodGiac").getAsInt() : null;
		   Integer img1 = jsonRequest.has("prodImg1") ? jsonRequest.get("prodImg1").getAsInt() : null;
		   Integer img2 = jsonRequest.has("prodImg2") ? jsonRequest.get("prodImg2").getAsInt() : null;
		   Integer img3 = jsonRequest.has("prodImg3") ? jsonRequest.get("prodImg3").getAsInt() : null;
		   String Categoria = jsonRequest.has("prodCategory") ? jsonRequest.get("prodCategory").getAsString() : null;
		   String Materiale = jsonRequest.has("prodMateriale") ? jsonRequest.get("prodMateriale").getAsString() : null;
		   String Tipo = jsonRequest.has("prodTipo") ? jsonRequest.get("prodTipo").getAsString() : null;
		   String Pezzo = jsonRequest.has("prodPezzo") ? jsonRequest.get("prodPezzo").getAsString() : null;
		   String Utilizzo = jsonRequest.has("prodUtilizzo") ? jsonRequest.get("prodUtilizzo").getAsString() : null;
		   String Genere = jsonRequest.has("prodGenere") ? jsonRequest.get("prodGenere").getAsString() : null;
		   
		   Prodotto prod = null;
		   
		  switch (Categoria) {
		   case "Abbigliamento": {
               prod = new Abbigliamento(Nome, Prezzo, Descrizione, Giacenza, img1, img2, img3, -1, Tipo, Genere);
               break;
		   }
		   case "Armatura": {
               prod = new Armatura(Nome, Prezzo, Descrizione, Giacenza, img1, img2, img3, -1, Materiale, Pezzo);
               break;
           }
		   
		   case "Arma": {
               prod = new Arma(Nome, Prezzo, Descrizione, Giacenza, img1, img2, img3, -1, Materiale, Tipo, Utilizzo);
               break;
           }
		   case "Accessorio": {
               prod = new Accessorio(Nome, Prezzo, Descrizione, Giacenza, img1, img2, img3, -1);
               break;
           }
		    default: {
            	res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: Categoria non valida");
            	processError(null, res);
            	return false;
            }
		  }
		  
		   prod.setIVA(IVA);
			try {
				pdao.doSave(prod);
				res.setStatus(HttpServletResponse.SC_OK);
				return true;
		
			} catch (SQLException e) {
				res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore: " + e.getMessage());
				processError(null, res);
				return false;
			}
			
	}
	
	protected boolean processDeleteProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		boolean test = false;
		try {
			test = pdao.doDeletebyID(req.getParameter("prodNome"));
			
		} catch (SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore: " + e.getMessage());
			processError(req, res);
		}
		return test;
	}
	
	protected void processEditOrder(JsonObject jsonRequest, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	protected void processDeleteOrder(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	protected boolean processEditUser(JsonObject jsonRequest, HttpServletResponse res) throws ServletException, IOException {
		
		String Username = jsonRequest.has("username") ? jsonRequest.get("username").getAsString() : null;
		String Operation = jsonRequest.has("inneraction") ? jsonRequest.get("inneraction").getAsString() : null;
		
		boolean test = false;
		
		try {
			
		if (Operation != null) {
			
			switch (Operation) {
			case "OP": {
				 test = (udao.update(new Utente(Username, "A")) == 1);
                break;
			}
			case "DEOP": {
				 test = (udao.update(new Utente(Username, "R")) == 1);
                break;
			}
			default: break;
		}
			} else {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: Operazione non valida");
			processError(null, res);
			} 
		
		} catch (SQLException e) {
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore: " + e.getMessage());
                processError(null, res);
            } 
		return test;
		
	}
	
	protected boolean processDeleteUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		boolean test = false;
		
		if (username != null) {
			
            try {
                test = udao.doDelete(new Utente(username, "R"));
            } catch (SQLException e) {
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore: " + e.getMessage());
                processError(req, res);
            }
        } else {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore: Username non valido");
            processError(req, res);
        }
		        return test;
	
	}
	
	protected void processError(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//TODO
		return;
	}
	
	

}
