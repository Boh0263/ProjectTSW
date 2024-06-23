package control;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import model.Carrello;
import model.LoginDAO;
import model.LoginInfo;
import model.Prodotto;
import model.ProdottoDAO;
import model.ProdottoDAOImp;

@WebServlet("/login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ProdottoDAO pdao = new ProdottoDAOImp();
    
	public LoginControl() {
        super();
    }
    	
    	@Override	
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	session.setAttribute("ctoken", generateToken());
    	session.setMaxInactiveInterval(300);
    	
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login-alternative.jsp");
    	dispatcher.forward(request, response);
    	}
    	
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        	try {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
            
			if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
				request.setAttribute("Messaggio", "Inserire username e password");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login-alternative.jsp");
				dispatcher.forward(request, response);
				return;
			}

            if (LoginDAO.UserValidation(new LoginInfo(username, encryptPassword(password))).equalsIgnoreCase("A")) {
                
            	HttpSession session = request.getSession();
            	
            	session.removeAttribute("ctoken"); 
            	
                session.setAttribute("username", username);
                session.setAttribute("role", "A");
                session.setAttribute("atoken", generateToken());
                session.setMaxInactiveInterval(43200);
                
                response.sendRedirect(request.getContextPath()+"/AdminControl");
                
            } else if (LoginDAO.UserValidation(new LoginInfo(username, encryptPassword(password))).equalsIgnoreCase("R")) {
                
            	HttpSession session = request.getSession();
                
            	session.setAttribute("username", username);
                session.setAttribute("role", "R");
                session.setAttribute("ctoken", generateToken());
                session.setMaxInactiveInterval(86400);
                
				if (request.getParameter("cart") != null) {
					if(request.getParameter("mergeDecision") != null && request.getParameter("mergeDecision").equals("merge")) {
					MergeCart(request, session);
					}
				}
               
                if(request.getAttribute("forward") != null) {
                	response.sendRedirect("/" + (String) request.getAttribute("forward"));
                }
                else {
                	
                 response.sendRedirect(getServletContext().getContextPath());
                 
                }
            }
            else  {
				request.setAttribute("Messaggio", LoginDAO.UserValidation(new LoginInfo(username, encryptPassword(password))));
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login-alternative.jsp");
				dispatcher.forward(request, response);
            }
        } catch (Exception e) {
        	if (e instanceof NullPointerException) {
        	throw new ServletException("Errore: "+ e.getMessage()); 
        	}
        }
    }

		public String encryptPassword(String password) throws ServletException {
        String hashtext = "";
        MessageDigest md = null;
		try {
            
			md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);
    
            
            while (hashtext.length() < 64) {
                hashtext = "0" + hashtext;
            }
            
        	} catch(Exception e) {
            	throw new ServletException("Errore: "+ e.getMessage());
            	}
            
             return hashtext;
		}
		
		public String generateToken() {
            return UUID.randomUUID().toString();
		}
		
		public void MergeCart(HttpServletRequest request, HttpSession session) throws ServletException {
			
			Carrello cart1 = (Carrello) session.getAttribute("carrello");
			Carrello cart = new Carrello();
			String cart2 = request.getParameter("cart");
		
			
		
			if (cart2 != null && cart2 != "") {
		   try { 
			    if (cart1 == null) {
			    	cart1 = new Carrello();
			    }
			   
				try {
					Type tipo = new TypeToken<Map<String, Integer>>(){}.getType();
					Gson gson = new Gson();
					Map<String, Integer> cart2Map = gson.fromJson(cart2, tipo);
					
					for (Map.Entry<String, Integer> entry : cart2Map.entrySet()) {
						
						String key = entry.getKey();
						Integer value =  entry.getValue();
						try {
							Prodotto prod = pdao.doRetrieveByKey(key);
							if(prod != null) {
								cart.setProduct(prod, Math.min(value, 10));
								}
						 	} catch(SQLException e) {
							     throw new ServletException("Errore2: "+ e.getMessage());
                        }
					}
					cart1.mergeCarts(cart);
				   } catch(JsonSyntaxException e) {
                    throw new ServletException("Errore3: "+ e.getMessage());
                }
				} catch(NullPointerException e) {
					throw new ServletException("Errore4: "+ e.getMessage());
				}
			} else return;
			session.setAttribute("carrello", cart1);
	}
		
}
