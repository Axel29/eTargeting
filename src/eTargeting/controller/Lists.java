package eTargeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eTargeting.model.ListsModel;
import eTargeting.model.UserModel;

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
		UserModel user = new UserModel();
		ListsModel listsModel = new ListsModel();
		ListsModel[] lists = listsModel.selectLists(user.getLoggedUser(request).getUserId());

		for (int i = 0; i < lists.length; i++) {
			request.setAttribute("list-" + i, lists[i]);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/lists.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out   = response.getWriter();
		UserModel user    = (UserModel)request.getAttribute("user");
		String deleteList = request.getParameter("confirm-deletion");
		String listId     = "";
		int owner         = user.getUserId();
		
		if ( request.getParameter("listId") != null && !"".equals(request.getParameter("listId")) ) {
			listId = request.getParameter("listId");
		}
		
		if ("true".equals(deleteList) && !"".equals(listId)) {
			int listIdInt = 0;
			try {
				listIdInt = Integer.parseInt(listId);
			} catch (NumberFormatException nfe) {
				out.println("Erreur");
			}
			int[] aIds           = {listIdInt};
			ListsModel listModel = new ListsModel();
			
			response.setContentType("text/html;charset=UTF-8");
			if (listModel.deleteList(aIds, owner) == 0) {
				out.println("Erreur");
			} else {
				out.println(this.getListsListHtml(owner));
			}
		}
	}
	
	/**
	 * Prepare HTML result for Ajax query
	 * @param owner
	 * @return html PrintWriter containing html result
	 */
	public StringBuilder getListsListHtml(int owner) {
		// Adding list's list to the response
		ListsModel listsModel = new ListsModel();
		ListsModel[] lists = listsModel.selectLists(owner);
		StringBuilder html = new StringBuilder();
		for (int i = 0; i < lists.length; i++) {
			html.append("<tr>");
			html.append("<td class=\"hidden table_list_id\">" + lists[i].getId() + "</td>");
			html.append("<td class=\"col-md-10\">" + lists[i].getName() + "</td>");
			html.append("<td class=\"col-md-1\"><p><a href=\"EditList?id=" + lists[i].getId() + "\" class=\"btn btn-primary btn-xs center-block update-subscriber\" data-title=\"Edit\" data-target=\"#edit\" data-placement=\"top\" rel=\"tooltip\"><span class=\"glyphicon glyphicon-pencil\"></span></a></p></td>");
			html.append("<td class=\"col-md-1\"><p><button class=\"btn btn-danger btn-xs center-block delete-list\" data-title=\"Delete\" data-target=\"#delete\" data-placement=\"top\"><span class=\"glyphicon glyphicon-trash\"></span></button></p></td>");
			html.append("</tr>");
		}
		return html;
	}
}