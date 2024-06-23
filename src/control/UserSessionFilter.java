package control;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserSessionFilter
 */
@WebFilter("/UserSessionFilter")
public class UserSessionFilter implements Filter {

    public UserSessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	HttpServletRequest  req = (HttpServletRequest) request;
	HttpServletResponse res = (HttpServletResponse) response;
	
	HttpSession session = req.getSession(false);
	
	if (session == null || session.getAttribute("username") == null) {
		res.sendRedirect(req.getContextPath() + "/login-alternative.jsp");
		return;
	} else if (session.getAttribute("username") != null) {
		session.setAttribute("username", session.getAttribute("username"));
		if (session.getAttribute("role") == null) {
            res.sendRedirect(req.getContextPath() + "/login-alternative.jsp");
		} else if (session.getAttribute("role").equals("A")) {
			if (session.getAttribute("atoken") != null && session.getAttribute("atoken") == req.getParameter("atoken")) {
				session.setAttribute("role", "A");
				session.setAttribute("atoken", session.getAttribute("atoken"));
				res.sendRedirect(req.getContextPath() + "/AdminControl");
			} else {
				session.invalidate();
				res.sendRedirect(req.getContextPath() + "/login-alternative.jsp");
			}
		} else if (session.getAttribute("role").equals("R")) { 
		    
		  if(session.getAttribute("ctoken") == req.getParameter("ctoken")) {
			  session.setAttribute("role", "R");
			  session.setAttribute("ctoken", session.getAttribute("ctoken"));
		   } else {
			  session.invalidate();
			  res.sendRedirect(req.getContextPath() + "/login-alternative.jsp");
			 }
		}
	}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		 
	}

}
