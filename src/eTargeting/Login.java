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
		request.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login      = request.getParameter("user_login");
		String password   = request.getParameter("user_password");
		
		int userId = DbConnect.verifLogin(login, password);
		
		if(userId != 0){
			sauveSessionLogin(request,login, userId);
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
