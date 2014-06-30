package eTargeting.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTargeting.model.CampaignsModel;
import eTargeting.model.ListsModel;
import eTargeting.model.SubscribersModel;
import eTargeting.model.UserModel;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Servlet implementation class CreateCampaign
 */
@WebServlet("/CreateCampaign")
public class CreateCampaign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateCampaign() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set every list object into request
		ListsModel listsModel = new ListsModel();
		ListsModel[] lists    = listsModel.selectLists(((UserModel)request.getAttribute("user")).getUserId(), 0);
		for (int i = 0; i < lists.length; i++) {
			request.setAttribute("list-" + i, lists[i]);
		}
		request.getServletContext().getRequestDispatcher( "/WEB-INF/createCampaign.jsp" ).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int owner         = ((UserModel)request.getAttribute("user")).getUserId();
			String name       = (request.getParameter("campaign_name") != null) ? request.getParameter("campaign_name") : "";
			String fromName   = (request.getParameter("from_name") != null) ? request.getParameter("from_name") : "";
			String fromEmail  = (request.getParameter("from_email") != null) ? request.getParameter("from_email") : "";
			String subject    = (request.getParameter("email_subject") != null) ? request.getParameter("email_subject") : "";
			String listId     = (request.getParameter("list") != null) ? request.getParameter("list") : "";
			String content    = (request.getParameter("email_content") != null) ? request.getParameter("email_content") : "";
	
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date createdAt    = new Date();
			Date scheduledAt  = new Date();
			Date sentOn       = new Date();
			if (request.getParameter("scheduledAt") != null) {
				scheduledAt = formatter.parse(request.getParameter("scheduledAt"));
			}

			if ( !"".equals(name) && !"".equals(fromName) && !"".equals(fromEmail) && !"".equals(subject) && !"".equals(listId) && !"".equals(content) ) {
				EmailValidator emailValidator = EmailValidator.getInstance();
				if (!emailValidator.isValid(fromEmail)) {
					request.getServletContext().getRequestDispatcher( "/WEB-INF/createCampaign.jsp" ).forward(request, response);
    			}
				int list = Integer.parseInt(listId);
				CampaignsModel campaignsModel = new CampaignsModel(0, name, fromName, fromEmail, subject, content, createdAt, scheduledAt, sentOn, list, owner, 0);
				// Insert campaign into database and send mail
				if (campaignsModel.insertCampaign() != 0) {
					ListsModel listsModel = new ListsModel();
					String[] subscribers  = listsModel.selectListById(list, owner).getSubscriberIds().split(",");
					// Get system properties
					Properties properties = System.getProperties();
					
					// Setup mail server
					properties.setProperty("mail.smtp.host", "localhost");
					
					// Get the default Session object.
					Session session = Session.getInstance(properties);
					SubscribersModel subscribersModel = new SubscribersModel();
					for (int i = 0; i < subscribers.length; i++) {
						if (!"".equals(subscribers[i])) {
							try{
								SubscribersModel subscriber = subscribersModel.selectSubscriberById(Integer.parseInt(subscribers[i]), owner);
								MimeMessage message         = new MimeMessage(session);
								message.setFrom(new InternetAddress(fromEmail, fromName));
								message.addRecipient(Message.RecipientType.TO, new InternetAddress(subscriber.getEmail()));
								message.setSubject(subject);
								message.setContent(content, "text/html");
							
								// Send message
								Transport.send(message);
								System.out.println("Mail envoyé");
						 	} catch (MessagingException mex) {
						 		mex.printStackTrace();
						 	} catch (Exception e) {
						 		e.printStackTrace();
						 	}
						}
					}
				}
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/Campaigns");
	}
}
