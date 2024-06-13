package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDaoImp;

/**
 * Servlet implementation class UsernameCheckControl
 */
@WebServlet("/verifyUsername")
public class UsernameCheckControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDaoImp dao = new UserDaoImp();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsernameCheckControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		try {
			if (dao.doCheck(username)) {
				response.getWriter().write("true");
				response.setStatus(200);
			} else {
				response.getWriter().write("false");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace(); //TODO log
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
