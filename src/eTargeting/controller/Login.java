package eTargeting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import eTargeting.model.MD5;
import eTargeting.model.UserModel;

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
		    response.sendRedirect(request.getContextPath() + "/Index");
		    return;
		} else {
			if (((UserModel)request.getAttribute("user")).getUserId() != 0) {
				response.sendRedirect(request.getContextPath() + "/Dashboard");
			} else {
				request.setAttribute("user", ((UserModel)request.getAttribute("user")));
				request.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
			response.sendRedirect(request.getContextPath() + "/Dashboard");
		}
		// Otherwise we redirect him back to login's page
		else {
			response.sendRedirect(request.getContextPath() + "/Login");
		}
	}
}
