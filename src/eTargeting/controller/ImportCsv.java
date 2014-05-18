package eTargeting.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import eTargeting.model.ListsModel;
import eTargeting.model.SubscribersModel;
import eTargeting.model.UserModel;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Servlet implementation class ImportCs
 */
@WebServlet("/ImportCsv")
public class ImportCsv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportCsv() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/importcsv.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName     = request.getParameter("file");
		String headersParam = request.getParameter("headers");
		JSONObject json     = new JSONObject();
		PrintWriter out     = response.getWriter();
		if (headersParam != null && fileName != null) {
			response.setContentType("application/json;charset=UTF-8");
			ArrayList<String> warnings = new ArrayList<String>();
			try {
				try {
					int userId            = ((UserModel)request.getAttribute("user")).getUserId();
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date             = new Date();
					String[] headers      = headersParam.split(",");
					
					// Select every subscribers belonging to the current user (in order to check if they exist later)
					SubscribersModel subscribersModel = new SubscribersModel();
					
					// Parse the CSV file
		            String [] line;
		            String filePath     = getServletContext().getInitParameter("uploads-directory") + dateFormat.format(date) + "-" + fileName;
		            CSVReader reader    = new CSVReader(new FileReader(filePath), ',', '"', 1);
		            
		            // Get columns indexes in order to match with database fields
		        	int index_first_name = Arrays.asList(headers).indexOf("first_name");
		        	int index_last_name  = Arrays.asList(headers).indexOf("last_name");
		        	int index_email      = Arrays.asList(headers).indexOf("email");
		        	int index_age        = Arrays.asList(headers).indexOf("age");
		        	int index_gender     = Arrays.asList(headers).indexOf("gender");
		        	
	    			ListsModel listsModel = new ListsModel();
	    			ListsModel list;
	    			int i                 = 0;
	    			int listId            = 0;
	    			
		            while ((line = reader.readNext()) != null) {
		            	i++;
		            	// Reject this line if there is no email address
		            	if (line.length > index_email && line[index_email] == null) {
		            		warnings.add("La ligne n°" + i + " n'a pas pu être importée. L'adresse email est obligatoire.");
		            		continue;
		            	}
		            	
		            	// Get informations from the line if they exist
		            	String first_name = (line.length > index_first_name && line[index_first_name] != null) ? line[index_first_name] : "";
		            	String last_name  = (line.length > index_last_name && line[index_last_name] != null) ? line[index_last_name] : "";
		            	String email      = (line.length > index_email && line[index_email] != null) ? line[index_email] : "";
		            	int age           = (line.length > index_age && line[index_age] != null) ? Integer.parseInt(line[index_age]) : 0;
		            	String gender     = (line.length > index_gender && line[index_gender] != null) ? line[index_gender] : "";

		            	// Insert new subscriber into 'subscribers' table if he doesn't exist
		            	SubscribersModel[] subscribers    = subscribersModel.selectSubscribers(userId, 0);
		            	subscribersModel = subscribersModel.setValues(0, first_name, last_name, email, age, gender, userId);
		            	int insertedId   = 0;
		            	for (int j=0; j<subscribers.length; j++) {
		            		if (subscribers[j].getEmail().equals(email)) {
		            			insertedId = subscribers[j].getId();
		            			warnings.add("L'adresse email " + email + " est déjà présente dans votre liste d'abonnés. Elle n'a donc été importée que dans la liste.");
		            		}
		            	}

	            		// Insert subscriber into database if he doesn't exist
		            	if (insertedId == 0) {
		            		insertedId = subscribersModel.insertSubscriber();
		            	}
		            		
	            		// Insert subscriber into the list
		    			if ( (request.getParameter("list") != null && Integer.parseInt(request.getParameter("list")) != 0) || listId != 0 ) {
		    				listId                = Integer.parseInt(request.getParameter("list"));
		    				list                  = listsModel.selectListById(listId, userId);
		    				String subscriberIds  = "";
		    				if (list.getSubscriberIds() == null || "".equals(list.getSubscriberIds())) {
		    					subscriberIds  = Integer.toString(insertedId);
		    				} else {
		    					subscriberIds  = list.getSubscriberIds() + "," + Integer.toString(insertedId);
		    				}
		    				
							listsModel.setId(listId);

							if (request.getParameter("name") != null) {
								listsModel.setName(request.getParameter("name"));
							} else {
								listsModel.setName(list.getName());
							}
							
							listsModel.setSubscriberIds(subscriberIds);
							listsModel.setOwner(userId);
							listsModel.updateList();
		    			}
		    			
		            	if (warnings.size() != 0) {
		            		json.put("warning", warnings.toString());
		            	}
		    			json.put("msg", "OK");
		            }
		            reader.close();
				} catch (NumberFormatException nfe) {
					json.put("msg", "L'identifiant de la liste et l'age doivent être numériques. Veuillez réessayer.");
				} catch (Exception e) {
					json.put("msg", "Une erreur est survenue. Veuillez réessayer.");
				}
			} catch (JSONException je) {}
			out.print(json);
		}
	}
}
