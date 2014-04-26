package eTargeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getServletContext().getRequestDispatcher( "/registration.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("sinscrire");
		String email = request.getParameter("user_login");
		String password = request.getParameter("user_password");
		String last_name = request.getParameter("nom");
		String first_name = request.getParameter("prenom");
		
		if(!email.isEmpty() || !password.isEmpty() || !last_name.isEmpty() || !first_name.isEmpty()){
			System.out.println("isenmpty");
			try {
				DbConnect.addUser(email, password, first_name, last_name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sauveSessionLogin(request,email);
			response.sendRedirect("/eTargeting/Admin");
		}else{
			response.sendRedirect("/eTargeting/Login");
		}
		
	}
	public void sauveSessionLogin(HttpServletRequest request,String login){
		HttpSession session = request.getSession();
    	session.setAttribute("login", login);
	}

}
