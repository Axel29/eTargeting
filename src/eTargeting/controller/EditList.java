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
		UserModel user        = new UserModel();
		// Get the list chosen by it's ID
		ListsModel listsModel = new ListsModel();
		int id = 0;
		try {
			// Set the ListsModel object to the request 
			id              = Integer.parseInt(request.getParameter("id"));
			ListsModel list = listsModel.selectListById(id, user.getLoggedUser(request).getUserId());
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
		} catch (NumberFormatException nfe) {
			response.sendRedirect("/eTargeting/Lists");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/eTargeting/Lists");
		}
		
		// Get every subscriber
		try {
			SubscribersModel subscribersModel = new SubscribersModel();
			SubscribersModel[] subscribers    = subscribersModel.selectSubscribers(user.getLoggedUser(request).getUserId());

			for (int i = 0; i < subscribers.length; i++) {
				request.setAttribute("subscriber-" + i, subscribers[i]);
			}
		} catch (Exception e) {
			response.sendRedirect("/eTargeting/Lists");
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/editList.jsp").forward(request, response);
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
			out.println("OK");
		}
	}
}
