package control;

import java.io.IOException;
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
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (LoginDAO.UserValidation(new LoginInfo(username, password)).equalsIgnoreCase("A")) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(200);
                response.sendRedirect("AdminHome.jsp"); //Usa il Dispatcher con ServletContext
            } else if (LoginDAO.UserValidation(new LoginInfo(username, password)).equalsIgnoreCase("R")) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setMaxInactiveInterval(200);
                response.sendRedirect("home.jsp"); //Usa il Dispatcher con ServletContext
            }
            else  {
                response.sendRedirect("loginFailed.jsp"); //Usa il Dispatcher con ServletContext
            }
        }
    }


}
