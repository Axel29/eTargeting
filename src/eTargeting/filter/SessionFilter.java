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

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {
	
	private ArrayList<String> urlList;
	
    /**
     * Default constructor. 
     */
    public SessionFilter() {
    }

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
		boolean allowedRequest  = false;
		
		if (urlList.contains(url)) {
			allowedRequest = true;
		}
		
		if (!allowedRequest) {
			HttpSession session = req.getSession(false);
			if (null == session) {
				res.sendRedirect("index.jsp");
				return;
			}
		}
		
		chain.doFilter(request,  response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String urls           = fConfig.getInitParameter("avoid-urls");
		System.out.println("URLS BEFORE: " + urls);
		StringTokenizer token = new StringTokenizer(urls, ",");
		urlList               = new ArrayList<String>();
		System.out.println("URLS AFTER: " + urls);
		if (token != null) {
			while (token.hasMoreTokens()) {
				urlList.add(token.nextToken());
			}
		}
	}
}
