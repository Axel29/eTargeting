package eTargeting;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eTargeting.ListModel;

@WebServlet("/Lists")
public class Lists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lists() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Checking that the user is logged in
		UserClass user = new UserClass();
		if (user.getLoggedUser(request).getUserId() == 0) {
			this.getServletContext().getRequestDispatcher("/Login").forward(request, response);
		} else {
			ListModel listModel = new ListModel();
			ListClass[] lists = listModel.selectLists(user.getLoggedUser(request).getUserId());

			for (int i = 0; i < lists.length; i++) {
				request.setAttribute("list-" + i, lists[i]);
			}
			request.setAttribute("user", user.getLoggedUser(request));
			this.getServletContext().getRequestDispatcher("/lists.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}