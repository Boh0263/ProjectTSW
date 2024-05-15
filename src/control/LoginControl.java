package control;

import java.io.IOException;

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
    	doPost(request,response);
    	}
    	
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        	try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (LoginDAO.UserValidation(new LoginInfo(username, password)).equalsIgnoreCase("A")) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(200);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/A-pages/AdminHome.jsp");
                //CRSF TOKEN 
                dispatcher.forward(request, response);
                response.sendRedirect(request.getContextPath()+"/AdminHome.jsp"); //Usa il Dispatcher con ServletContext
            } else if (LoginDAO.UserValidation(new LoginInfo(username, password)).equalsIgnoreCase("R")) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(200);
                if(request.getAttribute("forward") != null) {
                	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(request.getAttribute("forward").toString());
                	dispatcher.forward(request, response);
                }
                else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(request.getContextPath() + "/home.jsp");
                dispatcher.forward(request, response);
                }
            }
            else  {
                response.sendRedirect(request.getContextPath() +"/loginFailed.jsp"); 
            }
        } catch (Exception e) {
        	throw new ServletException("Errore: "+ e.getMessage()); 
        }
    }
}
