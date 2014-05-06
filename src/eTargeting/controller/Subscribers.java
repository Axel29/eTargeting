package eTargeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		UserModel user = new UserModel();
		SubscribersModel subscribersModel = new SubscribersModel();
		SubscribersModel[] subscribers = subscribersModel.selectSubscribers(user.getLoggedUser(request).getUserId());

		for (int i = 0; i < subscribers.length; i++) {
			request.setAttribute("subscriber-" + i, subscribers[i]);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/subscribers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out   = response.getWriter();
		// Getting form's values from AJAX request
		UserModel user    = (UserModel)request.getAttribute("user");
		String insertSubscriber = request.getParameter("insertSubscriber");
		String deleteSubscriber = request.getParameter("confirm-deletion");
		String email      = request.getParameter("email");
		String last_name  = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String gender     = request.getParameter("gender");
		int owner         = user.getUserId();
		int age           = 0;
		if ( request.getParameter("age") != null && !"".equals(request.getParameter("age")) ) {
			age = Integer.parseInt(request.getParameter("age"));
		}
		String subscriberId  = "";
		if ( request.getParameter("subscriberId") != null && !"".equals(request.getParameter("subscriberId")) ) {
			subscriberId = request.getParameter("subscriberId");
		}
		
		// Inserting new subscriber
		if ("1".equals(insertSubscriber) && email != null) {
			SubscribersModel subscribersModel = new SubscribersModel(0, first_name, last_name, email, age, gender, owner);
			subscribersModel.insertSubscriber();
			
			response.setContentType("text/html;charset=UTF-8");
			// Adding subscriber's list to the response
			out.println(this.getSubscriberListHtml(owner).toString());
		} else if ("true".equals(deleteSubscriber) && !"".equals(subscriberId)) {
			String[] splitIds                 = subscriberId.split(",");
			int[] aIds                        = new int[splitIds.length];
			SubscribersModel subscribersModel = new SubscribersModel();
			
			for (int i = 0; i < splitIds.length; i++) {
				aIds[i] = Integer.parseInt(splitIds[i]);
			}
			subscribersModel.deleteSubscriber(aIds);
			
			response.setContentType("text/html;charset=UTF-8");
			out.println(this.getSubscriberListHtml(owner));
		} else {
			response.setContentType("text/html;charset=UTF-8");
			out.println("Erreur");
		}
	}
	
	public StringBuilder getSubscriberListHtml(int owner) {
		// Adding subscriber's list to the response
		SubscribersModel subscribersModel = new SubscribersModel();
		SubscribersModel[] subscribers = subscribersModel.selectSubscribers(owner);
		StringBuilder html = new StringBuilder();
		for (int i = 0; i < subscribers.length; i++) {
			html.append("<tr>");
				html.append("<td class=\"table_user_id\">" + subscribers[i].getId() + "</td>");
				html.append("<td class=\"table_user_email\">" + subscribers[i].getEmail() + "</td>");
				html.append("<td class=\"table_user_first_name\">" + subscribers[i].getFirstName() + "</td>");
				html.append("<td class=\"table_user_last_name\">" + subscribers[i].getLastName() + "</td>");
				html.append((subscribers[i].getAge() == 0) ? "<td class=\"table_user_age\">N/A</td>" : "<td class=\"table_user_age\">" + subscribers[i].getAge() + "</td>");
				html.append("<td class=\"table_user_gender\">" + subscribers[i].getGender() + "</td>");
				html.append("<td><p><button class=\"btn btn-primary btn-xs edit-subscriber\" data-title=\"Edit\" data-target=\"#edit\" data-placement=\"top\" rel=\"tooltip\"><span class=\"glyphicon glyphicon-pencil\"></span></button></p></td>");
				html.append("<td><p><button class=\"btn btn-danger btn-xs delete-subscriber\" data-title=\"Delete\" data-target=\"#delete\" data-placement=\"top\" rel=\"tooltip\"><span class=\"glyphicon glyphicon-trash\"></span></button></p></td>");
			html.append("</tr>");
		}
		return html;
	}
}