package eTargeting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTargeting.model.ListsModel;
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
		// Checking that the user is logged in
		UserModel user = new UserModel();
		if (user.getLoggedUser(request).getUserId() == 0) {
			this.getServletContext().getRequestDispatcher("/Login").forward(request, response);
		} else {
			SubscribersModel subscribersModel = new SubscribersModel();
			SubscribersModel[] subscribers = subscribersModel.selectSubscribers(user.getLoggedUser(request).getUserId());

			for (int i = 0; i < subscribers.length; i++) {
				request.setAttribute("subscriber-" + i, subscribers[i]);
			}
			request.setAttribute("user", user.getLoggedUser(request));
			this.getServletContext().getRequestDispatcher("/WEB-INF/subscribers.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}