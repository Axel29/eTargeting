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
	 * Ajax Request from Edit List page.
	 * Get every subscribers that belong to the current list, set them in session and return them into JSON object.
	 * Used to precheck every subscribers and to navigate through subscribers' pages. 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserModel user        = new UserModel();
			ListsModel listsModel = new ListsModel();
			JSONObject json       = new JSONObject();
			PrintWriter out       = response.getWriter();
			int listId            = 0;
			int userId            = user.getLoggedUser(request).getUserId();

			if (request.getParameter("listId") != null) {
				try {
					listId = Integer.parseInt(request.getParameter("listId"));
				} catch (NumberFormatException nfe) {
					json.put("msg", "Erreur");
				}
				
				if (checkListBelonging(listId, userId)) {
					response.setContentType("application/json;charset=UTF-8");					

					// Get every subscribers belonging to that list
					ListsModel list      = listsModel.selectListById(listId, user.getLoggedUser(request).getUserId());
					String subscriberIds = list.getSubscriberIds();
					// Set them into session
					HttpSession session = request.getSession();
					session.setAttribute("listSubscriberIds", subscriberIds);
					
					json.put("msg", "OK");
					json.put("subscriberIds", subscriberIds);
					out.print(json);
				} else {
					json.put("msg", "Erreur");
					out.print(json);
				}
			}
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ajax Request from Edit and Add List pages.
	 * Set every subscriber ids received from the Ajax query in session when the user arrives on the Edit list's page.
	 * This is used to be able to precheck every subscribers belonging to the list.
	 * Finally, this adds the subscribers list to the JSON object, in order to change the page's content
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListsModel listsModel = new ListsModel();
		
		// If the user just arrived to the EditList page, we set subscriberIds to session
		if (request.getParameter("subscriberIds") != null) {
			HttpSession session = request.getSession();
			session.setAttribute("listSubscriberIds", request.getParameter("subscriberIds"));
		}

		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		int userId      = ((UserModel)request.getAttribute("user")).getUserId();
		int page        = 0;
		response.setContentType("application/json;charset=UTF-8");

		try {
			// Check if the request comes from edit list page...
			if (request.getParameter("page") != null && request.getParameter("listId") != null) {
				int listId      = 0;
				try {
					page   = Integer.parseInt(request.getParameter("page"));
					listId = Integer.parseInt(request.getParameter("listId"));
				} catch (NumberFormatException nfe) {
					json.put("msg", "Erreur nfe");
					out.print(json.toString());
				} 
				
				if (checkListBelonging(listId, userId)) {
					int numberOfSubscribers = listsModel.numberOfLists(userId);
					double numberOfPages    = Math.ceil(numberOfSubscribers/ListsModel.getLimit());
					
					json.put("msg", "OK");
					json.put("list", getSubscriberListHtml(userId, page, request));
					json.put("pagination", getPaginationHtml(listId, page, numberOfPages));
				} else {
					json.put("msg", "Erreur la liste n'appartient pas à cet utilisateur");
				}
				out.print(json.toString());
			}
			// ... or if it comes from add list page 
			else if (request.getParameter("page") != null && request.getParameter("listId") == null) {
				try {
					page = Integer.parseInt(request.getParameter("page"));
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					json.put("msg", "Erreur");
				}

				int numberOfSubscribers = listsModel.numberOfLists(userId);
				double numberOfPages    = Math.ceil(numberOfSubscribers/ListsModel.getLimit());
				
				json.put("msg", "OK");
				json.put("list", getSubscriberListHtml(userId, page, request));
				json.put("pagination", getPaginationAddHtml(page, numberOfPages));
				out.print(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Check that the list recieved from Ajax query really belongs to that user.
	 * Prevent the user to hack other user's lists
	 * @param listId
	 * @param userId
	 * @return true | false
	 */
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
					if (!"".equals(splitIds[i])) {
						idsList.add(Integer.parseInt(splitIds[i]));
					}
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
			html.append("<tr class=\"tr-middle\">");
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
	 * Prepare pagination HTML for EditList page's Ajax query when getting subscriber's list
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
			html.append("<a href=\"" + prevPage + "\" class=\"page-link\">&laquo;</a>");
		html.append("</li>");
		
		for (int i = 1; i <= (int)numberOfPages; i++) {
			String isActive = (currentPage == i) ? "active" : "" ;
			html.append("<li class=\"" + isActive + "\">");
				html.append("<a href=\"EditList?id=" + listId + "&page=" + i + "\" class=\"page-link\">" + i + "</a>");
			html.append("</li>");
		}
		
		html.append("<li class=\"" + ((currentPage == (int)numberOfPages) ? "disabled" : "") + "\">");
			html.append("<a href=\"" + nextPage + "\" class=\"page-link\">&raquo;</a>");
		html.append("</li>");
		
		return html;
	}
	
	/**
	 * Prepare pagination HTML for AddList's page Ajax query when getting subscriber's list
	 * @param currentPage
	 * @param numberOfPages
	 * @return
	 */
	public StringBuilder getPaginationAddHtml(int currentPage, double numberOfPages) {
		// Format html pagination
		String nextPage      = (currentPage != (int)numberOfPages) ? "AddList?page=" + Integer.toString(currentPage + 1) : "#";
		String prevPage      = (currentPage != 1) ? "AddList?page=" + Integer.toString(currentPage - 1) : "#";
		StringBuilder html = new StringBuilder();
		
		html.append("<li class=\"" + ((currentPage == 1) ? "disabled" : "") + "\">");
			html.append("<a href=\"" + prevPage + "\">&laquo;</a>");
		html.append("</li>");
		
		for (int i = 1; i <= (int)numberOfPages; i++) {
			String isActive = (currentPage == i) ? "active" : "" ;
			html.append("<li class=\"" + isActive + "\">");
				html.append("<a href=\"AddList?page=" + i + "\" class=\"page-link\">" + i + "</a>");
			html.append("</li>");
		}
		
		html.append("<li class=\"" + ((currentPage == (int)numberOfPages) ? "disabled" : "") + "\">");
			html.append("<a href=\"" + nextPage + "\">&raquo;</a>");
		html.append("</li>");
		
		return html;
	}
}
