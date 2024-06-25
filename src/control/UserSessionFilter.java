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
import javax.servlet.http.Cookie;
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
	String csrfTokenRequest = null;
	
	// Retrieve CSRF token from request cookie
    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("CSRF-TOKEN".equals(cookie.getName())) {
                csrfTokenRequest = cookie.getValue();
                break;
            }
        }
    }
	
	HttpSession session = req.getSession(false);
	
	if (session == null || session.getAttribute("username") == null) {
		System.out.println("Errore: sessione non valida");
		res.sendRedirect(req.getContextPath() + "/login");
		return;
	} else if (session.getAttribute("username") != null) {
		session.setAttribute("username", session.getAttribute("username"));
		if (session.getAttribute("role") == null) {
			System.out.println("Errore: sessione non valida!");
            session.invalidate();
            res.sendRedirect(req.getContextPath() + "/login");
            return;
		} else if (((String) session.getAttribute("role")).equalsIgnoreCase("A")) {
			if (session.getAttribute("atoken") != null && ((String)session.getAttribute("atoken")).equals(csrfTokenRequest)) {
				session.setAttribute("role", "A");
				session.setAttribute("atoken", session.getAttribute("atoken"));
				//request.removeAttribute("atoken");
				chain.doFilter(request, response);
			} else {
				System.out.println(session.getAttribute("atoken") + " " + csrfTokenRequest);
				session.invalidate();
				
				res.sendRedirect(req.getContextPath() + "/login");
				return;
			}
		} else if (((String) session.getAttribute("role")).equalsIgnoreCase("R")) { 
		    
		  if(((String)session.getAttribute("ctoken")).equals(csrfTokenRequest)) {
			  session.setAttribute("role", "R");
			  session.setAttribute("ctoken", session.getAttribute("ctoken"));
				
			  if (req.getRequestURI().contains("AdminControl")) {
					session.invalidate();
					System.out.println("Errore: Utente non autorizzato"); // TODO mandare a pagina di errore
					res.sendRedirect(req.getContextPath() + "/login");
					return;
				} else {
					System.out.println("Utente Riconosciuto");
					chain.doFilter(request, response);
				}
		   } else {
			  session.invalidate();
			  res.sendRedirect(req.getContextPath() + "/login");
			  return;
			 }
		}
	} else {
		res.sendRedirect(req.getContextPath() + "/login");
		return;
	 }
	
	}
	public void init(FilterConfig fConfig) throws ServletException {
		 
	}

}

