package eTargeting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import eTargeting.model.SubscribersModel;
import eTargeting.model.UserModel;

/**
 * Servlet implementation class Subscribers
 */
@WebServlet("/Subscribers")
public class Subscribers extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Subscribers() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SubscribersModel subscribersModel = new SubscribersModel();
		// Set number of page, current page, previous and next page's links into request
		int page = 1;
		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException nfe) {}
		}
		int numberOfSubscribers = subscribersModel.numberOfSubscribers(((UserModel)request.getAttribute("user")).getUserId());
		double numberOfPages    = Math.ceil(numberOfSubscribers/SubscribersModel.getLimit());
		String nextPage         = (page < numberOfPages) ? "Subscribers?page=" + Integer.toString(page + 1) : "#";
		String prevPage         = (page != 1) ? "Subscribers?page=" + Integer.toString(page - 1) : "#";
		
		request.setAttribute("numberOfPages", (int)numberOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("prevPage", prevPage);
		request.setAttribute("nextPage", nextPage);
		
		// Set every subscriber object into request
		SubscribersModel[] subscribers = subscribersModel.selectSubscribers(((UserModel)request.getAttribute("user")).getUserId(), page);
		for (int i = 0; i < subscribers.length; i++) {
			request.setAttribute("subscriber-" + i, subscribers[i]);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/subscribers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserModel user  = (UserModel)request.getAttribute("user");
		// Getting form's values from AJAX request
		String insertSubscriber = request.getParameter("insertSubscriber");
		String updateSubscriber = request.getParameter("updateSubscriber");
		String deleteSubscriber = request.getParameter("confirm-deletion");
		String email      = request.getParameter("email");
		String last_name  = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String gender     = request.getParameter("gender");
		int owner         = user.getUserId();
		int age           = 0;
		int page          = 0;
			
		if (request.getParameter("page") != null) {
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException nfe) {
				out.print("Erreur");
			}
		}
		if ( request.getParameter("age") != null && !"".equals(request.getParameter("age")) ) {
			age = Integer.parseInt(request.getParameter("age"));
		}
		String subscriberId  = "";
		if ( request.getParameter("subscriberId") != null && !"".equals(request.getParameter("subscriberId")) ) {
			subscriberId = request.getParameter("subscriberId");
		}
		
		////// Insert new subscriber //////
		if ("1".equals(insertSubscriber) && email != null) {
			EmailValidator emailValidator = EmailValidator.getInstance();
			if (emailValidator.isValid(email)) {
				SubscribersModel subscribersModel = new SubscribersModel(0, first_name, last_name, email, age, gender, owner);
				subscribersModel.insertSubscriber();
			} else {
				System.out.println("Email invalide");
			}
			
			// Adding subscriber's list to the response
			response.setContentType("text/html;charset=UTF-8");
			out.print(this.getSubscriberListHtml(owner, page).toString());
		}
		///// Edit subscriber //////
		else if ("1".equals(updateSubscriber) && email != null && !"".equals(subscriberId)) {
			EmailValidator emailValidator = EmailValidator.getInstance();
			if (emailValidator.isValid(email)) {
				SubscribersModel subscribersModel = new SubscribersModel(Integer.parseInt(subscriberId), first_name, last_name, email, age, gender, owner);
				subscribersModel.updateSubscriber();
			} else {
				System.out.println("Email invalide");
			}
			
			response.setContentType("text/html;charset=UTF-8");
			// Adding subscriber's list to the response
			out.print(this.getSubscriberListHtml(owner, page).toString());
		}
		////// Delete subscriber //////
		else if ("true".equals(deleteSubscriber) && !"".equals(subscriberId)) {
			String[] splitIds                 = subscriberId.split(",");
			int[] aIds                        = new int[splitIds.length];
			SubscribersModel subscribersModel = new SubscribersModel();
			
			try {
				for (int i = 0; i < splitIds.length; i++) {
					aIds[i] = Integer.parseInt(splitIds[i]);
				}
			} catch (NumberFormatException nbe) {
				out.print("Erreur");
			} catch (Exception e) {
				out.print("Erreur");
			}
			
			response.setContentType("text/html;charset=UTF-8");
			if (subscribersModel.deleteSubscriber(aIds, owner) == 0) {
				out.print("Erreur");
			} else {
				out.print(this.getSubscriberListHtml(owner, page));
			}
		}
		////// Error /////
		else {
			response.setContentType("text/html;charset=UTF-8");
			out.print("Erreur");
		}
	}
	
	/**
	 * Prepare HTML result for Ajax query
	 * @param owner
	 * @param page
	 * @return html PrintWriter containing html result
	 */
	public StringBuilder getSubscriberListHtml(int owner, int page) {
		// Adding subscriber's list to the response
		SubscribersModel subscribersModel = new SubscribersModel();
		SubscribersModel[] subscribers    = subscribersModel.selectSubscribers(owner, page);
		StringBuilder html                = new StringBuilder();
		for (int i = 0; i < subscribers.length; i++) {
			html.append("<tr class=\"tr-middle\">");
				html.append("<td class=\"col-lg-1 table_user_id hidden\">" + subscribers[i].getId() + "</td>");
				html.append("<td class=\"col-lg-4 table_user_email\">" + subscribers[i].getEmail() + "</td>");
				html.append("<td class=\"col-lg-2 table_user_first_name\">" + subscribers[i].getFirstName() + "</td>");
				html.append("<td class=\"col-lg-1 table_user_last_name\">" + subscribers[i].getLastName() + "</td>");
				html.append((subscribers[i].getAge() == 0) ? "<td class=\"col-lg-1 table_user_age\">N/A</td>" : "<td class=\"col-lg-1 table_user_age\">" + subscribers[i].getAge() + "</td>");
				html.append("<td class=\"col-lg-1 table_user_gender\">" + subscribers[i].getGender() + "</td>");
				html.append("<td class=\"col-lg-1\"><p><button class=\"btn btn-primary btn-xs center-block update-subscriber href-glyphicon\" data-title=\"Edit\" data-target=\"#edit\" data-placement=\"top\"><span class=\"glyphicon glyphicon-pencil\"></span></button></p></td>");
				html.append("<td class=\"col-lg-1\"><p><button class=\"btn btn-danger btn-xs center-block delete-subscriber href-glyphicon\" data-title=\"Delete\" data-target=\"#delete\" data-placement=\"top\"><span class=\"glyphicon glyphicon-trash\"></span></button></p></td>");
			html.append("</tr>");
		}
		return html;
	}
}