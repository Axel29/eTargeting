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
		String email      = request.getParameter("email");
		String last_name  = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String gender     = request.getParameter("gender");
		int owner         = user.getUserId();
		int age           = 0;
		if ( request.getParameter("age") != null && !"".equals(request.getParameter("age")) ) {
			age = Integer.parseInt(request.getParameter("age"));
		}
		
		if (email != null) {
			SubscribersModel subscribersModel = new SubscribersModel(0, first_name, last_name, email, age, gender, owner);
			subscribersModel.insertSubscriber();
			response.setContentType("text/html;charset=UTF-8");
			// Adding subscriber's list to the response
			SubscribersModel[] subscribers = subscribersModel.selectSubscribers(owner);

			for (int i = 0; i < subscribers.length; i++) {
				out.println("<tr>");
					out.println("<td>" + subscribers[i].getId() + "</td>");
					out.println("<td>" + subscribers[i].getFirstName() + "</td>");
					out.println("<td>" + subscribers[i].getLastName() + "</td>");
					out.println("<td>" + subscribers[i].getEmail() + "</td>");
					out.println((subscribers[i].getAge() == 0) ? "<td>N/A</td>" : "<td>" + subscribers[i].getAge() + "</td>");
					out.println("<td>" + subscribers[i].getGender() + "</td>");
				out.println("</tr>");
			}
		} else {
			response.setContentType("text/html;charset=UTF-8");
		}
	}
}