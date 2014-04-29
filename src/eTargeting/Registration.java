package eTargeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

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
		UserClass user = new UserClass();
		if (user.getLoggedUser(request).getUserId() != 0) {
			response.sendRedirect("/eTargeting/Dashboard");
		} else {
			request.setAttribute("user", user.getLoggedUser(request));
			request.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
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
				// Insert user into database
				UserClass user      = new UserClass(0, email, password, lastName, firstName);
				UserModel userModel = new UserModel();
				// Redirect the user to the registration page if the email already exists
				if (userModel.checkUserExists(email)) {
					response.sendRedirect("/eTargeting/Registration");
				} else {
					int userId = userModel.insertUser(user);
					user = UserModel.getUser(userId);
					saveUserSession(request, user);
					response.sendRedirect("/eTargeting/Dashboard");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("/eTargeting/Registration");
		}
		
	}
	public void saveUserSession(HttpServletRequest request, UserClass user){
		HttpSession session = request.getSession();
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("last_name", user.getLastName());
		session.setAttribute("first_name", user.getFirstName());
	}

}
