package eTargeting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import eTargeting.model.ListsModel;
import eTargeting.model.UserModel;

@WebServlet("/AddList")
public class AddList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Checking that the user is logged in
		UserModel user = new UserModel();
		if (user.getLoggedUser(request).getUserId() == 0) {
			this.getServletContext().getRequestDispatcher("/Login").forward(request, response);
		} else {
			request.setAttribute("user", user.getLoggedUser(request));
			this.getServletContext().getRequestDispatcher("/WEB-INF/addList.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("name") != null){
			UserModel user = new UserModel();
			// Insert list into database
			ListsModel list = new ListsModel(0, StringEscapeUtils.escapeHtml4(request.getParameter("name")), "", user.getLoggedUser(request).getUserId());
			list.insertList();
		}
		response.sendRedirect("/eTargeting/Lists");
	}

}
