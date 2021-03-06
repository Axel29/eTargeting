package eTargeting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.validator.routines.EmailValidator;

import eTargeting.model.MD5;
import eTargeting.model.UserModel;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Checking that the user is logged in
		if (((UserModel)request.getAttribute("user")).getUserId() != 0) {
			response.sendRedirect(request.getContextPath() + "/Dashboard");
		} else {
			request.setAttribute("user", (UserModel)request.getAttribute("user"));
			request.getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email     = request.getParameter("user_login");
		String password  = MD5.getMD5(StringEscapeUtils.escapeHtml4(request.getParameter("user_password")));
		String lastName  = StringEscapeUtils.escapeHtml4(request.getParameter("lastName"));
		String firstName = StringEscapeUtils.escapeHtml4(request.getParameter("firstName"));
		
		if(!email.isEmpty() && !password.isEmpty() && !lastName.isEmpty() && !firstName.isEmpty()){
			try {
				// Check that the email is valid, otherwise, we redirect the user to the registration page
				// @TODO Add error message
				EmailValidator emailValidator = EmailValidator.getInstance();
				if (!emailValidator.isValid(email)) {
					request.getServletContext().getRequestDispatcher( "/WEB-INF/registration.jsp" ).forward(request, response);
    			}
				
				// Insert user into database
				UserModel user      = new UserModel(0, email, password, lastName, firstName);
				// Redirect the user to the registration page if the email already exists
				if (user.userExists(email)) {
					response.sendRedirect(request.getContextPath() + "/Registration");
				} else {
					int userId = user.insertUser();
					user       = UserModel.getUserById(userId);
					user.saveUserSession(request);
					response.sendRedirect(request.getContextPath() + "/Dashboard");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect(request.getContextPath() + "/Registration");
		}
		
	}
}
