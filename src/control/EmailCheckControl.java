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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if (dao.doEmail(email)) {
			response.getWriter().write("true");
		} else {
			response.getWriter().write("false");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
