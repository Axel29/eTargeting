package eTargeting;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

import au.com.bytecode.opencsv.CSVWriter;

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
		HttpSession session = request.getSession();
		// If the user tries to lougout
		if (request.getParameter("logout") != null) {
			UserClass.logout(request, response);
		    response.sendRedirect("/eTargeting/Index");
		    return;
		} else {
			UserClass user = new UserClass();
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
		UserClass user    = UserModel.login(email, password);

		// If the login succeeded, we insert the user into session or cookies and redirect him to Dashboard
		if(user.getUserId() != 0){
			// Create cookie if "remember me" checkbox is checked
			if (request.getParameter("remember_me") != null) {
				saveUserCookies(response, user);
			}
			// Or insert the user into session if not checked
			else {
				saveUserSession(request, user);
			}
			response.sendRedirect("/eTargeting/Dashboard");
		}
		// Otherwise we redirect him back to login's page
		else {
			response.sendRedirect("/eTargeting/Login");
		}
	}
	
	
	public void saveUserCookies(HttpServletResponse response, UserClass user) {
		try {
			// Creating a CSV from user's values in order to put it in one cookie
			StringWriter stringWriter = new StringWriter();
			CSVWriter csvWriter       = new CSVWriter(stringWriter);
			String[] userValues       = {Integer.toString(user.getUserId()), user.getEmail(), user.getLastName(), user.getFirstName()};
			// Writing into the string writer
			csvWriter.writeNext(userValues);
			csvWriter.close();
			Cookie cookie = new Cookie("user", stringWriter.toString());
			// Setting expire date for cookies to current day + 30 days
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveUserSession(HttpServletRequest request, UserClass user) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("email", user.getEmail());
		session.setAttribute("last_name", user.getLastName());
		session.setAttribute("first_name", user.getFirstName());
	}
}
