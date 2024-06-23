package control;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/UserPOSTSessionFilter")

public class UserPOSTSessionFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
    public UserPOSTSessionFilter() {
        super();
        
    }
    
	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        String method = httpRequest.getMethod();
       
        if ("POST".equalsIgnoreCase(method)) {
            if (session == null) {
            	httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized to access this page.");
                return;
            }
			else if (session.getAttribute("username") == null) {
				//Casi limite per cui l'utente è autorizzato a fare una richiesta POST.
				String path = httpRequest.getRequestURI();
				
				if (!path.endsWith("login") && !path.endsWith("register") && !path.endsWith("verifyEmail") && !path.endsWith("verifyUsername")) {
					httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
							"You are not authorized to access this page.");
					return;
				}
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"You are not authorized to access this page.");
				return;
			}
            
            String csrfToken = httpRequest.getParameter("ctoken");
            String sessionCsrfToken = (String) session.getAttribute("ctoken");
            
            if (csrfToken == null || sessionCsrfToken == null || !csrfToken.equals(sessionCsrfToken)) {
    
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token.");
                return;
            }
          }
            chain.doFilter(request, response);
    }

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
