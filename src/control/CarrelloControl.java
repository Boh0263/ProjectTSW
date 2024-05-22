package control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

@WebServlet("/Carrello")
public class CarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static final OrdineDAOImp dao = new OrdineDAOImp();
       private static final ProdottoDAOImp pdao = new ProdottoDAOImp();
    
    public CarrelloControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		  HttpSession session = request.getSession(false);
		  Carrello cart;
		  try {
		  String cartCookieValue = getCartFromCookie(request);
		  if (cartCookieValue != null) {
		    cart = deserializeCarr(cartCookieValue);
		  } else {
		    cart = new Carrello();
		  }

		  if (session != null && session.getAttribute("username") != null) {
		    Carrello sessionCart = (Carrello) session.getAttribute("carrello");
		    if (sessionCart != null) {
		      sessionCart.mergeCarts(cart);
		      session.setAttribute("carrello", sessionCart);
		      request.setAttribute("carrello", sessionCart);
		      request.setAttribute("cartItems", sessionCart.getTotalItems());
		      
		    } else {
		      session.setAttribute("carrello", cart);
		      request.setAttribute("carrello", cart);
		      request.setAttribute("cartItems", cart.getTotalItems());
		    }
		  } else {
		    String serializedCarr = serializeCarr(cart);
		    setCarrCookie(response, serializedCarr);
		    request.setAttribute("carrello", cart);
		    request.setAttribute("cartItems", cart.getTotalItems());
		  }
		  } catch(Exception e) {
			  System.out.println("Errore" + e.getMessage());
		  }
		  if(request.getAttribute("forward") != null && request.getAttribute("forward").toString().isEmpty()) {
		  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(request.getAttribute("forward").toString());
		  dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/shoppingcart.jsp");
			dispatcher.forward(request, response);
			}
		}
	
		private String getCartFromCookie(HttpServletRequest request) {
			  Cookie[] cookies = request.getCookies();
			  if (cookies != null) {
			    for (Cookie cookie : cookies) {
			      if (cookie.getName().equals("carrello")) {
			        return cookie.getValue();
			      }
			    }
			  }
			  return null;
			}

		private Carrello deserializeCarr(String serializedCart) throws ClassNotFoundException, IOException {
			  if (serializedCart == null) {
			    return null; // no cookie
			  }
			  byte[] bytes = Base64.getDecoder().decode(serializedCart); // Decodifica Base64
			  ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			  ObjectInputStream ois = new ObjectInputStream(bais);
			  return (Carrello) ois.readObject();
			}

		private String serializeCarr(Carrello cart) throws IOException {
			
			  ByteArrayOutputStream baos = new ByteArrayOutputStream();
			  ObjectOutputStream oos = new ObjectOutputStream(baos);
			  oos.writeObject(cart);
			  oos.flush();
			  byte[] bytes = baos.toByteArray();
			  return Base64.getEncoder().encodeToString(bytes); // Codifica Base64
			}

		private void setCarrCookie(HttpServletResponse response, String serializedCart) {
			  
			  int expirySeconds = 60 * 60 * 24; // 24 ore (DA DECIDERE)
			  Cookie cookie = new Cookie("carrello", serializedCart);
			  cookie.setMaxAge(expirySeconds);
			  cookie.setPath("/");
			  cookie.setHttpOnly(true); // Per evitare XSS
			  response.addCookie(cookie);
			}
		
		public boolean processCheckout(HttpServletResponse response, HttpSession session, Carrello cart) throws ServletException {
		    if (session == null || !session.getAttribute("username").equals("temp")) {
		      throw new ServletException("Checkout non autorizzato");
		    }
		    String ragioneSociale = "temp1"; //TODO
		    Indirizzo address = new Indirizzo("temp"); //TODO
		    double ScontoCoupon = 0.00d; //TODO Sistema di verifica coupon
		    boolean saveStatus = false;
		    Ordine ordine = new Ordine(cart.getProdotti(), ragioneSociale, ScontoCoupon, address);
		    try {
		     saveStatus = dao.doSave(ordine);
		    if (saveStatus) {
		      cart.clearCarr();
		      session.removeAttribute("carrello");
		      Cookie cookie = new Cookie("carrello", "");
			  cookie.setMaxAge(0);
			  response.addCookie(cookie);
		    	} else {
		      throw new ServletException("L'ordine non è stato salvato. Riprova più tardi.");
		    	}
		    } catch(Exception e) {
		    	throw new ServletException(e.getMessage());
		    } 
		    return saveStatus;
		  }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		 HttpSession session = request.getSession(false); //TODO Sequenza di verifica utente.
		if (action.equalsIgnoreCase("proceedToCheckout")) {
			if(request.getAttribute("username") != null /*Controllo di sessione utente qui.*/) {
			  try {
				Carrello carrello = (Carrello) session.getAttribute("carrello");
			    boolean success = processCheckout(response, session, carrello);
			    if (success) {
			      response.sendRedirect("/pages/checkouts-success.jsp"); //TODO
			    } else {
			      request.setAttribute("errorMessage", "L'ordine non è stato salvato. Riprova più tardi.");
			      RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/shoppingcart.jsp");
			      dispatcher.forward(request, response);
			    }
			  } catch (Exception e) {
			    // Auth Errors etc.
				//TODO verifica sessione utente.
				  throw new ServletException(e.getMessage());
			  }
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("pages/login-alternative.jsp");
				request.setAttribute("forward", "/pages/shoppingcart.jsp");
				dispatcher.forward(request, response);
			}
			} else if (action.equalsIgnoreCase("addProduct")) {
			    try {
			        Carrello cart = null;
			        if (session != null && session.getAttribute("carrello") != null) {
			        	cart = (Carrello) session.getAttribute("carrello");                        
 			        } else {
			          String cartCookieValue = getCartFromCookie(request);
			          if (cartCookieValue != null) {
			            cart = deserializeCarr(cartCookieValue);
			          } else {
			            cart = new Carrello();
			           }
			         }
			        String prodottoname = (String) request.getAttribute("prodotto");
			        if(prodottoname != null && !prodottoname.isEmpty()) {
			        	
			        Prodotto prodotto = pdao.doRetrieveByKey(prodottoname);
			        //TODO Prevenire attacchi DDOS (weak code) Idea: limitare il numero di oggetti da aggiungere al carrello in un lasso di tempo ( non duplicati). 
			        cart.addProduct(prodotto); //Prevenire attacchi da parte di utenti non loggati.
			        if (session != null /*TODO verifica sessione utente*/) {
			         
			          session.setAttribute("carrello", cart);
			          request.setAttribute("carrello", cart);
			          request.setAttribute("cartItems", cart.getTotalItems());
			        } else {
			          String serializedCart = serializeCarr(cart);
			          setCarrCookie(response, serializedCart);
			          request.setAttribute("carrello", cart);
			          request.setAttribute("cartItems", cart.getTotalItems());
			        }
			       }
			      } catch (Exception e) {
			        throw new ServletException("Errore nell'aggiungere il prodotto: " + e.getMessage()); //TODO error handling
			      }
			    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(request.getAttribute("forward").toString());
				  dispatcher.forward(request, response);
			    }
		}
}