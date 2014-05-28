package eTargeting.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTargeting.model.CampaignsModel;
import eTargeting.model.ListsModel;
import eTargeting.model.UserModel;

@WebServlet("/Campaigns")
public class Campaigns extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Campaigns() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CampaignsModel campaignsModel = new CampaignsModel();
		ListsModel listsModel         = new ListsModel();
		int userId                    = ((UserModel)request.getAttribute("user")).getUserId();
		if (request.getParameter("id") != null) {
			try {
				int id                  = Integer.parseInt(request.getParameter("id"));
				CampaignsModel campaign = campaignsModel.selectCampaignById(id, userId);
				ListsModel list         = listsModel.selectListById(campaign.getListId(), userId);
				request.setAttribute("name", campaign.getName());
				request.setAttribute("from_name", campaign.getFromName());
				request.setAttribute("from_email", campaign.getFromEmail());
				request.setAttribute("subject", campaign.getSubject());
				request.setAttribute("content", campaign.getContent());
				request.setAttribute("scheduled_at", campaign.getScheduledAt());
				request.setAttribute("sent_on", campaign.getSentOn());
				request.setAttribute("list", list.getName());
				request.getServletContext().getRequestDispatcher( "/WEB-INF/seeCampaign.jsp" ).forward(request, response);
			} catch (NumberFormatException nfe) {
				response.sendRedirect("eTargeting/Campaigns");
			}
		} else {
			// Set number of page, current page, previous and next page's links into request
			int page = 1;
			if (request.getParameter("page") != null) {
				try {
					page = Integer.parseInt(request.getParameter("page"));
				} catch (NumberFormatException nfe) {}
			}
			int numberOfCampaigns = campaignsModel.numberOfCampaigns(userId);
			double numberOfPages  = Math.ceil(numberOfCampaigns/ListsModel.getLimit());
			String nextPage       = (page < numberOfPages) ? "Campaigns?page=" + Integer.toString(page + 1) : "#";
			String prevPage       = (page != 1) ? "Campaigns?page=" + Integer.toString(page - 1) : "#";
	
			request.setAttribute("numberOfPages", (int)numberOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("prevPage", prevPage);
			request.setAttribute("nextPage", nextPage);
			
			// Set every list object into request
			CampaignsModel[] campaigns = campaignsModel.selectCampaigns(((UserModel)request.getAttribute("user")).getUserId(), page);
			if (campaigns != null) {
				for (int i = 0; i < campaigns.length; i++) {
					request.setAttribute("campaign-" + i, campaigns[i]);
				}
			}
			request.getServletContext().getRequestDispatcher( "/WEB-INF/campaigns.jsp" ).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
