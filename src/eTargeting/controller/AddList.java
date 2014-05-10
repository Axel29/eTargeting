package eTargeting.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringEscapeUtils;
import eTargeting.model.ListsModel;
import eTargeting.model.SubscribersModel;
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
		// Set subscriber's list to the request so that we can display it
		UserModel user                    = new UserModel();
		SubscribersModel subscribersModel = new SubscribersModel();
		SubscribersModel[] subscribers    = subscribersModel.selectSubscribers(user.getLoggedUser(request).getUserId());

		for (int i = 0; i < subscribers.length; i++) {
			request.setAttribute("subscriber-" + i, subscribers[i]);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/addList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] subscribersChecked  = request.getParameterValues("subscribersChecked[]");
		StringBuilder subscribersIds = new StringBuilder();
		if (subscribersChecked != null) {
			for (int i = 0; i < subscribersChecked.length; i++) {
				if (i+1 != subscribersChecked.length) {
					subscribersIds.append(subscribersChecked[i] + ",");
				} else {
					subscribersIds.append(subscribersChecked[i]);
				}
			}
		}
		
		if(request.getParameter("name") != null){
			UserModel user = new UserModel();
			// Insert list into database
			ListsModel list = new ListsModel(0, StringEscapeUtils.escapeHtml4(request.getParameter("name")), subscribersIds.toString(), user.getLoggedUser(request).getUserId(), 1);
			list.insertList();
		}
		response.sendRedirect("/eTargeting/Lists");
	}

}
