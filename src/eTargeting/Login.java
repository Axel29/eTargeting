package eTargeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
				String login = request.getParameter("user_login");
				String mdp = request.getParameter("user_password");
				
				boolean verifLogin = DbConnect.verifLogin(login,mdp);
				
				if(verifLogin){
					sauveSessionLogin(request,login);
					response.sendRedirect("/eTargeting/Dashboard");
				}else{
					response.sendRedirect("/eTargeting/Login");
				}
			}
			
			public void sauveSessionLogin(HttpServletRequest request,String login){
				HttpSession session = request.getSession();
		    	session.setAttribute("login", login);
			}


}
