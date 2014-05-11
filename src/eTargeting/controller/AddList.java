package eTargeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;

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
		
		// Set number of page, current page, previous and next page's links into request
		int page = 1;
		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException nfe) {}
		}
		int numberOfSubscribers = subscribersModel.numberOfSubscribers(user.getLoggedUser(request).getUserId());
		double numberOfPages    = Math.ceil(numberOfSubscribers/SubscribersModel.getLimit());
		String nextPage         = (page != numberOfPages) ? "AddList?page=" + Integer.toString(page + 1) : "#";
		String prevPage         = (page != 1) ? "AddList?page=" + Integer.toString(page - 1) : "#";
		
		request.setAttribute("numberOfPages", (int)numberOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("prevPage", prevPage);
		request.setAttribute("nextPage", nextPage);
		
		SubscribersModel[] subscribers    = subscribersModel.selectSubscribers(user.getLoggedUser(request).getUserId(), 1);
		for (int i = 0; i < subscribers.length; i++) {
			request.setAttribute("subscriber-" + i, subscribers[i]);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/addList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json;charset=UTF-8");
		try {
			JSONObject json = new JSONObject();
			if(request.getParameter("name") != null){
				String ids = null;
				if (request.getParameter("subscriberIds") != null) {
					ids = request.getParameter("subscriberIds");
				}
				// Insert list into database
				UserModel user  = new UserModel();
				ListsModel list = new ListsModel(0, StringEscapeUtils.escapeHtml4(request.getParameter("name")), ids, user.getLoggedUser(request).getUserId(), 1);
				list.insertList();

				json.put("msg", "OK");
				out.print(json);
			} else {
				json.put("msg", "Erreur");
				out.print(json);
			}
		} catch (JSONException je) {}
	}

}
