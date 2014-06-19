package eTargeting.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
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
import eTargeting.model.UserModel;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {
	
	private ArrayList<String> urlList;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req  = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url              = req.getServletPath();
		String uri              = req.getRequestURI();
		boolean allowedRequest  = false;
		UserModel user          = new UserModel();
		
		// Exclude css, javascript, images and fonts from redirecting.
		if (uri.indexOf("css") > 0) {
			chain.doFilter(request, response);
		} else if (uri.indexOf("img") > 0) {
			chain.doFilter(request, response);
		} else if (uri.indexOf("js") > 0) {
			chain.doFilter(request, response);
		} else if (uri.indexOf("font-awesome") > 0) {
			chain.doFilter(request, response);
		} else if (uri.indexOf("fonts") > 0) {
			chain.doFilter(request, response);
		}
		// Redirecting the user to the login page if he's not logged-in
		else {
			if (urlList != null) {
				if (urlList.contains(url) || "".equals(url)) {
					allowedRequest = true;
				}
				
				if (!allowedRequest) {
					HttpSession session = req.getSession(false);
					if (null == session.getAttribute("email") && user.getLoggedUser(req).getUserId() == 0) {
						res.sendRedirect(req.getContextPath() + "/Login");
						return;
					}
				}
			}
		}
		
		// Setting the user's object to the request in order to use it's information in every JSP
		request.setAttribute("user", user.getLoggedUser(req));
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String urls           = fConfig.getInitParameter("avoid-urls");
		if (urls != null) {
			StringTokenizer token = new StringTokenizer(urls, ",");
			urlList               = new ArrayList<String>();
			if (token != null) {
				while (token.hasMoreTokens()) {
					urlList.add(token.nextToken());
				}
			}
		}
	}
}
