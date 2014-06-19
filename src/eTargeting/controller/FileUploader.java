package eTargeting.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.json.JSONObject;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Servlet implementation class FileUploader
 */
@WebServlet("/FileUploader")
public class FileUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploader() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JSONObject json      = new JSONObject();
			PrintWriter out      = response.getWriter();
			
			// Check that we have a file upload request
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if( !isMultipart ){
				json.put("msg", "Une erreur s'est produite. Veuillez réessayer.");
				out.print(json);
				return;
			}
			// Create a factory for disk-based file items
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date             = new Date();
			DiskFileItemFactory factory   = new DiskFileItemFactory();
			ServletContext servletContext = this.getServletConfig().getServletContext();
			File repository               = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			String filePath               = getServletContext().getInitParameter("uploads-directory");

			// Set factory constraints (max memory limit 4Mo and secure repository)
			factory.setSizeThreshold(4 * 1024);
			factory.setRepository(repository);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint (20M)
			upload.setSizeMax(20971520);
			
			response.setContentType("application/json;charset=UTF-8");
			try{ 
				// Parse the request to get file items.
				List<FileItem> items        = upload.parseRequest(request);
				// Process the uploaded items
				Iterator<FileItem> iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = iterator.next();
					// Check that the field is a file
					if (!item.isFormField()) {
						// Get the uploaded file parameters
						String fileName    = item.getName();
						// Write the file
						File file = new File(filePath + dateFormat.format(date) + "-" + fileName);
						item.write(file) ;
		
						// Parse the CSV file
		                String [] line;
		                CSVReader reader                    = new CSVReader(new FileReader(file));
		                String[] headers                    = reader.readNext();
		                ArrayList<String[]> contentExamples = new ArrayList<String[]>();
		                int i                               = 1;
		                while ((line = reader.readNext()) != null) {
		                	// Stop parsing the CSV if we reach the 10th line
		                	if (i == 10) { break; }
		                	// Add the examples to the ArrayList
		                	contentExamples.add(line);
		                	i++;
		                }
		                reader.close();
		                // Send ajax response in JSON format
						json.put("msg", "OK");
						json.put("headers", headers);
						json.put("contentExamples", prepareContentExamples(headers, contentExamples));
						json.put("file", fileName);
					} else {
						json.put("msg", "Une erreur s'est produite. Veuillez réessayer.");
					}
					out.print(json);
				}
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    } catch (JSONException jse) {}
	}
	
	/**
	 * Prepare HTML content to return for CSV example.
	 * Return a table containing every header from the CSV and the 15 first lines (maximum) to show, with a submit button
	 * @param headers
	 * @param contentExamples
	 * @return html StringBuilder containing the whole table and submit button to display
	 */
	public StringBuilder prepareContentExamples(String[] headers, ArrayList<String[]> contentExamples) {
		StringBuilder html = new StringBuilder();
		html.append("<table id=\"content-example\" class=\"table table-striped table-bordered table-hover\">");
			html.append("<thead>");
					html.append("<tr>");
						for (int h=0; h<headers.length; h++) {
							html.append("<th>");
								html.append(headers[h]);
								html.append("<select name=\"headers[]\" class=\"contentpreview-headers\">");
									String emailChecked     = (headers[h].toLowerCase().indexOf("email") > -1) ? "selected=\"selected\"" : "";
									String firstNameChecked = (headers[h].toLowerCase().indexOf("prenom") > -1 || headers[h].toLowerCase().indexOf("firstname") > -1 || headers[h].toLowerCase().indexOf("first_name") > -1) ? "selected=\"selected\"" : "";
									String lastNameChecked  = (headers[h].toLowerCase().indexOf("nom") > -1 || headers[h].toLowerCase().indexOf("lastname") > -1 || headers[h].toLowerCase().indexOf("last_name") > -1) ? "selected=\"selected\"" : "";
									String ageChecked       = (headers[h].toLowerCase().indexOf("age") > -1) ? "selected=\"selected\"" : "";
									String genderChecked    = (headers[h].toLowerCase().indexOf("sexe") > -1 || headers[h].toLowerCase().indexOf("gender") > -1) ? "selected=\"selected\"" : "";
									html.append("<option value=\"email\" " + emailChecked + ">Email</option>");
									html.append("<option value=\"last_name\" " + lastNameChecked + ">Nom</option>");
									html.append("<option value=\"first_name\" " + firstNameChecked + ">Prénom</option>");
									html.append("<option value=\"age\" " + ageChecked + ">Age</option>");
									html.append("<option value=\"gender\" " + genderChecked + ">Sexe</option>");
								html.append("</select>");
							html.append("</th>");
						}
					html.append("</tr>");
			html.append("</thead>");
			html.append("<tbody class=\"contentExample\">");
				for (String[] examples : contentExamples) {
					html.append("<tr>");
						for (int i=0; i<examples.length; i++) {
							html.append("<td class=\"col-md-4\"><input type=\"text\" name=\"" + headers[i] + "\" class=\"input-styleless\" value=\"" + examples[i] + "\" disabled=\"disabled\" /></td>");
						}
					html.append("</tr>");
				}
			html.append("</tbody>");
		html.append("</table>");
		html.append("<button type=\"submit\" id=\"sendcsv\" name=\"sendcsv\" class=\"btn btn-primary\">Importer</button>");
		return html;
	}
}
