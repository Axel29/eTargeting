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
		request.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String inscrire = request.getParameter("sinscrire");
		String connexion = request.getParameter("connexion");
		System.out.println(inscrire);
		if(connexion.equalsIgnoreCase("Connexion")){
			String login = request.getParameter("email");
			String mdp = request.getParameter("mdp");
			
			boolean verifLogin = DbConnect.verifLogin(login,mdp);
			
			if(verifLogin){
				sauveSessionLogin(request,login);
				response.sendRedirect("/eTargeting/Admin");
			}else{
				response.sendRedirect("/eTargeting/Login");
			}
			
		}else if(inscrire.equalsIgnoreCase("Sinscrire")){
			System.out.println("sinscrire");
			String email = request.getParameter("iEmail");
			String password = request.getParameter("iMdp");
			String last_name = request.getParameter("last_name");
			String first_name = request.getParameter("first_name");
			
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
	}
	
	public void sauveSessionLogin(HttpServletRequest request,String login){
		HttpSession session = request.getSession();
    	session.setAttribute("login", login);
	}

}
