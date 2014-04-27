package eTargeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		if ((Integer)session.getAttribute("userId") != null) {
			response.sendRedirect("/eTargeting/Dashboard");
		} else {
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email      = request.getParameter("user_email");
		String password   = MD5.getMD5(request.getParameter("user_password"));
		UserClass user    = UserModel.login(email, password);

		if(user.getUserId() != 0){
			saveUserSession(request, user);
			response.sendRedirect("/eTargeting/Dashboard");
		}else{
			response.sendRedirect("/eTargeting/Login");
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
