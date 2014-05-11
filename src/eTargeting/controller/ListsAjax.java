package eTargeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import eTargeting.model.ListsModel;
import eTargeting.model.SubscribersModel;
import eTargeting.model.UserModel;

/**
 * Servlet implementation class ListsAjax
 */
@WebServlet("/ListsAjax")
public class ListsAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListsAjax() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getParameter("listId") != null) {
				UserModel user        = new UserModel();
				ListsModel listsModel = new ListsModel();
				JSONObject json       = new JSONObject();
				PrintWriter out       = response.getWriter();
				int listId            = 0;
				int userId            = user.getLoggedUser(request).getUserId();
				
				try {
					listId = Integer.parseInt(request.getParameter("listId"));
				} catch (NumberFormatException nfe) {
					json.put("msg", "Erreur");
				}
				System.out.println("listId: " + listId + " - userId: " + userId);
				System.out.println("checkListBelonging(listId, userId): " + checkListBelonging(listId, userId));
				if (checkListBelonging(listId, userId)) {
					response.setContentType("application/json;charset=UTF-8");					

					// Get every subscribers belonging to that list
					ListsModel list      = listsModel.selectListById(listId, user.getLoggedUser(request).getUserId());
					String subscriberIds = list.getSubscriberIds();
					// Set them into session
					HttpSession session = request.getSession();
					session.setAttribute("listSubscriberIds", subscriberIds);
					System.out.println("subscriberIds: " + subscriberIds);
					json.put("msg", "OK");
					json.put("subscriberIds", subscriberIds);
				}
				out.print(json);
			}
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel user        = new UserModel();
		ListsModel listsModel = new ListsModel();
		
		// Set subscriberIds to session (from edit / add list's pages)
		if (request.getParameter("subscriberIds") != null) {
			HttpSession session = request.getSession();
			session.setAttribute("listSubscriberIds", request.getParameter("subscriberIds"));
		}
		
		if (request.getParameter("page") != null && request.getParameter("listId") != null) {
			JSONObject json = new JSONObject();
			PrintWriter out = response.getWriter();
			int page        = 0;
			int listId      = 0;
			int userId      = user.getLoggedUser(request).getUserId();
			response.setContentType("application/json;charset=UTF-8");
			try {
				try {
					page   = Integer.parseInt(request.getParameter("page"));
					listId = Integer.parseInt(request.getParameter("listId"));
				} catch (NumberFormatException nfe) {
					json.put("msg", "Erreur nfe");
					out.print(json.toString());
				} 
				
				if (checkListBelonging(listId, userId)) {
					int numberOfLists    = listsModel.numberOfLists(user.getLoggedUser(request).getUserId());
					double numberOfPages = Math.ceil(numberOfLists/ListsModel.getLimit());
					
					json.put("msg", "OK");
					json.put("list", getSubscriberListHtml(user.getLoggedUser(request).getUserId(), page, request));
					json.put("pagination", getPaginationHtml(listId, page, numberOfPages));
				} else {
					json.put("msg", "Erreur la liste n'appartient pas à cet utilisateur");
				}
				out.print(json.toString());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public boolean checkListBelonging(int listId, int userId) {
		// Check that the list belongs to that user
		ListsModel listsModel = new ListsModel();
		ListsModel[] lists    = listsModel.selectLists(userId, 0);
		for (ListsModel list : lists) {
			if (list.getId() == userId) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Prepare HTML result for Ajax query when getting subscriber's list
	 * @param owner
	 * @param page
	 * @return html PrintWriter containing html result
	 */
	public StringBuilder getSubscriberListHtml(int owner, int page, HttpServletRequest request) {
		// Get every subscribers already checked from session in order to pre-check them if needed
		HttpSession session        = request.getSession();
		ArrayList<Integer> idsList = new ArrayList<Integer>();
		if (session.getAttribute("listSubscriberIds") != null) {
			String[] splitIds = ((String)session.getAttribute("listSubscriberIds")).split(",");
			try {
				for (int i = 0; i < splitIds.length; i++) {
					idsList.add(Integer.parseInt(splitIds[i]));
				}
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
			}
		}
		// Adding subscriber's list to the response
		SubscribersModel subscribersModel = new SubscribersModel();
		SubscribersModel[] subscribers    = subscribersModel.selectSubscribers(owner, page);
		StringBuilder html                = new StringBuilder();
		for (int i = 0; i < subscribers.length; i++) {
			String checked  = (idsList.contains(subscribers[i].getId())) ? "checked=\"checked\"" : "" ;
			html.append("<tr>");
				html.append("<td class=\"col-md-1\"><input type=\"checkbox\" name=\"subscribersChecked[]\" value=\"" + subscribers[i].getId() + "\" class=\"checkthis\" " + checked + " /></td>");
				html.append("<td class=\"col-md-4\"><input type=\"text\" name=\"email\" class=\"input-styleless\" value=\"" + subscribers[i].getEmail() + "\" disabled=\"disabled\" /></td>");
				html.append("<td class=\"col-md-2\"><input type=\"text\" name=\"first_name\" class=\"input-styleless\" value=\"" + subscribers[i].getFirstName() + "\" disabled=\"disabled\" /></td>");
				html.append("<td class=\"col-md-2\"><input type=\"text\" name=\"last_name\" class=\"input-styleless\" value=\"" + subscribers[i].getLastName() + "\" disabled=\"disabled\" /></td>");
				html.append("<td class=\"col-md-1\"><input type=\"text\" name=\"age\" class=\"input-styleless\" value=\"" + subscribers[i].getAge() + "\" disabled=\"disabled\" /></td>");
				html.append("<td class=\"col-md-2\"><input type=\"text\" name=\"email\" class=\"input-styleless\" value=\"" + subscribers[i].getGender() + "\" disabled=\"disabled\" /></td>");
			html.append("</tr>");
		}
		return html;
	}
	
	/**
	 * Prepare pagination HTML for Ajax query when getting subscriber's list
	 * @param currentPage
	 * @param numberOfPages
	 * @return
	 */
	public StringBuilder getPaginationHtml(int listId, int currentPage, double numberOfPages) {
		// Format html pagination
		String nextPage      = (currentPage != (int)numberOfPages) ? "EditList?id=" + listId + "&page=" + Integer.toString(currentPage + 1) : "#";
		String prevPage      = (currentPage != 1) ? "EditList?id=" + listId + "&page=" + Integer.toString(currentPage - 1) : "#";
		StringBuilder html = new StringBuilder();
		
		html.append("<li class=\"" + ((currentPage == 1) ? "disabled" : "") + "\">");
			html.append("<a href=\"" + prevPage + "\">&laquo;</a>");
		html.append("</li>");
		
		for (int i = 1; i <= (int)numberOfPages; i++) {
			String isActive = (currentPage == i) ? "active" : "" ;
			html.append("<li class=\"" + isActive + "\">");
				html.append("<a href=\"EditList?id=" + listId + "&page=" + i + "\" class=\"page-link\">" + i + "</a>");
			html.append("</li>");
		}
		
		html.append("<li class=\"" + ((currentPage == (int)numberOfPages) ? "disabled" : "") + "\">");
			html.append("<a href=\"" + nextPage + "\">&raquo;</a>");
		html.append("</li>");
		
		return html;
	}
}
