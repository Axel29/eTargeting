package eTargeting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringEscapeUtils;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// If the user tries to lougout
		if (request.getParameter("logout") != null) {
			UserModel.logout(request, response);
		    response.sendRedirect("/eTargeting/Index");
		    return;
		} else {
			UserModel user = new UserModel();
			if (user.getLoggedUser(request).getUserId() != 0) {
				response.sendRedirect("/eTargeting/Dashboard");
			} else {
				request.setAttribute("user", user.getLoggedUser(request));
				request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email      = StringEscapeUtils.escapeHtml4(request.getParameter("user_email"));
		String password   = MD5.getMD5(StringEscapeUtils.escapeHtml4(request.getParameter("user_password")));
		UserModel user    = UserModel.login(email, password);

		// If the login succeeded, we insert the user into session or cookies and redirect him to Dashboard
		if(user.getUserId() != 0){
			// Create cookie if "remember me" checkbox is checked
			if (request.getParameter("remember_me") != null) {
				user.saveUserCookies(response);
			}
			// Or insert the user into session if not checked
			else {
				user.saveUserSession(request);
			}
			response.sendRedirect("/eTargeting/Dashboard");
		}
		// Otherwise we redirect him back to login's page
		else {
			response.sendRedirect("/eTargeting/Login");
		}
	}
}
