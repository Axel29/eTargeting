package eTargeting;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		if ((Integer)session.getAttribute("userId") == null) {
			this.getServletContext().getRequestDispatcher("/Login").forward(request, response);
		} else {
			int userId = (Integer)session.getAttribute("userId");
			ListModel listModel = new ListModel();
			ListClass[] lists = listModel.selectLists(userId);
			
			
			for (int i = 0; i < lists.length; i++) {
				request.setAttribute("list-" + i, lists[i]);
				//System.out.println("List name: " + lists[i].getName() + " - list owner: " + lists[i].getOwner());
			}
			
			this.getServletContext().getRequestDispatcher("/lists.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("name") != null){
			HttpSession session = request.getSession();
			// Insert list into database
			ListModel listModel = new ListModel();
			ListClass list = new ListClass(0, request.getParameter("name"), "", (Integer)session.getAttribute("userId"));
			listModel.insertList(list);
		}
		response.sendRedirect("/eTargeting/Lists");
	}
}