package control;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginDAO;
import model.LoginInfo;

@WebServlet("/login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginControl() {
        super();
    }
    	
    	@Override	
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(200);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/A-pages/AdminHome.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect(request.getContextPath()+"/AdminHome.jsp");
            } else if (LoginDAO.UserValidation(new LoginInfo(username, encryptPassword(password))).equalsIgnoreCase("R")) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", "R");
                session.setMaxInactiveInterval(86400);
                
                if(request.getAttribute("forward") != null) {
                	response.sendRedirect("/" + (String) request.getAttribute("forward"));
                }
                else {
                 response.sendRedirect(getServletContext().getContextPath());
                }
            }
            else  {
            		System.out.println("Sono qui!");
				request.setAttribute("Messaggio", LoginDAO.UserValidation(new LoginInfo(username, encryptPassword(password))));
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/login-alternative.jsp");
				dispatcher.forward(request, response);
            }
        } catch (Exception e) {
        	throw new ServletException("Errore: "+ e.getMessage()); 
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
            
            System.out.println(hashtext);
            
            while (hashtext.length() < 64) {
                hashtext = "0" + hashtext;
            }
            
        	} catch(Exception e) {
            	throw new ServletException("Errore: "+ e.getMessage());
            	}
            
             return hashtext;
		}
 
}
