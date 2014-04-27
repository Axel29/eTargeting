package eTargeting;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		request.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email      = request.getParameter("user_email");
		String password   = MD5.getMD5(request.getParameter("user_password"));
		int userId        = UserModel.login(email, password);

		if(userId != 0){
			sauveSessionLogin(request, email, userId);
			response.sendRedirect("/eTargeting/Dashboard");
		}else{
			response.sendRedirect("/eTargeting/Login");
		}
	}
			
	public void sauveSessionLogin(HttpServletRequest request, String login, int userId){
		HttpSession session = request.getSession();
		session.setAttribute("login", login);
		session.setAttribute("userId", userId);
	}
}
