package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;


@WebServlet("/ProdottoCount")
public class ProdottoCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ProdottoCount() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int itemCount = 0;
        if (session != null && session.getAttribute("carrello") != null) {
            Carrello cart = (Carrello) session.getAttribute("carrello");
            itemCount = cart.getTotalItems();
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(Integer.toString(itemCount));
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
