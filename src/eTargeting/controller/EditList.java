package eTargeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTargeting.model.ListsModel;
import eTargeting.model.SubscribersModel;
import eTargeting.model.UserModel;

/**
 * Servlet implementation class EditList
 */
@WebServlet("/EditList")
public class EditList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId            = ((UserModel)request.getAttribute("user")).getUserId();
		int id                = 0;
		ListsModel listsModel = new ListsModel();
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		
			// Pagination
			// Set number of page, current page, previous and next page's links into request
			int page = 1;
			if (request.getParameter("page") != null) {
				try {
					page = Integer.parseInt(request.getParameter("page"));
				} catch (NumberFormatException nfe) {}
			}
			SubscribersModel subscribersModel = new SubscribersModel();
			int numberOfSubscribers           = subscribersModel.numberOfSubscribers(userId);
			double numberOfPages              = Math.ceil(numberOfSubscribers/SubscribersModel.getLimit());
			String nextPage = (page != numberOfPages) ? "EditList?id=" + id + "&page=" + Integer.toString(page + 1) : "#";
			String prevPage = (page != 1) ? "EditList?id=" + id + "&page=" + Integer.toString(page - 1) : "#";
			
			request.setAttribute("numberOfPages", (int)numberOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("prevPage", prevPage);
			request.setAttribute("nextPage", nextPage);
			// End of pagination
			
			// Set the ListsModel object to the request
			ListsModel list = listsModel.selectListById(id, userId);
			request.setAttribute("list", list);
			
			// Set an ArrayList of subscriber ids in order to pre-check every subscriber that belong to this list 
			String[] subscriberIds     = list.getSubscriberIds().split(",");
			Integer[] subscribersArray = new Integer[subscriberIds.length];
			for (int i = 0; i< subscriberIds.length; i++) {
				try {
					subscribersArray[i] = Integer.parseInt(subscriberIds[i]);
				} catch (NumberFormatException nfe) {}
			}
			ArrayList<Integer> idsList = new ArrayList<Integer>(Arrays.asList(subscribersArray));
			request.setAttribute("listSubscribers", idsList);
			
			// Get every subscriber
			try {
				SubscribersModel[] subscribers = subscribersModel.selectSubscribers(userId, page);
				for (int i = 0; i < subscribers.length; i++) {
					request.setAttribute("subscriber-" + i, subscribers[i]);
				}
			} catch (Exception e) {
				
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/editList.jsp").forward(request, response);
		} catch (NumberFormatException nfe) {
			response.sendRedirect(request.getContextPath() + "/Lists");
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/Lists");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();		
		if (request.getParameter("name") != null) {			
			String ids = null;
			if (request.getParameter("subscriberIds") != null) {
				ids = request.getParameter("subscriberIds");
			}
			
			UserModel user  = (UserModel)request.getAttribute("user");
			
			int id = 0;
			if (request.getParameter("id") != null) {
				try {
					id = Integer.parseInt(request.getParameter("id"));
				} catch (NumberFormatException nfe) {
					out.print("Erreur");
				}
			}
			
			ListsModel listsModel = new ListsModel(id, request.getParameter("name"), ids, user.getUserId(), 1);
			listsModel.updateList();
			
			response.setContentType("text/html;charset=UTF-8");
			// Adding subscriber's list to the response
			out.print("OK");
		}
	}
}
