package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDaoImp;

/**
 * Servlet implementation class EmailCheckControl
 */

@WebServlet("/verifyEmail")
public class EmailCheckControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDaoImp dao = new UserDaoImp();
       
   
    public EmailCheckControl() {
        super();
        
    }
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if (email == null || email.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email is required.");
            return;
        }

		boolean emailExists = dao.doEmail(email);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(emailExists ? "true" : "false");
	}
}
