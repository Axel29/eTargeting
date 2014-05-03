package eTargeting.controller;

import java.io.IOException;

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
	}

}