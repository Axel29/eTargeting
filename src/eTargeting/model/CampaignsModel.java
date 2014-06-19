package eTargeting.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>The CampaignsModel class represents a campaign.</b>
 * <p>
 * A campaign is represented by the following information:
 * <ul>
 * <li>A unique ID.</li>
 * <li>A name.</li>
 * <li>A sender name.</li>
 * <li>A sender email.</li>
 * <li>A subject</li>
 * <li>A content</li>
 * <li>A creation date</li>
 * <li>A schedule date</li>
 * <li>An execution date</li>
 * <li>A list</li>
 * </ul>
 * </p>
 * 
 * @see Model
 * 
 * @author Axel
 * @version 1.0
 */
public class CampaignsModel {
	
	private int id;
	private String name;
	private String fromName;
	private String fromEmail;
	private String subject;
	private String content;
	private Date createdAt;
	private Date scheduledAt;
	private Date sentOn;
	private int listId;
	private int owner;
	
	/**
	 * Current page
	 * 
	 * @see CampaignsModel#CampaignsModel(int, String, String, String, String, String, Date, Date, Date, int, int)
	 * @see CampaignsModel#getPage()
	 */
	private int page;
	
	/**
	 * Number of list to display per page
	 * 
	 * @see ListsModel#getLimit()
	 */
	private static double limit = 10;
	
	/**
     * CampaignsModel default constructor.
     * <p>
     * Set default values to the object with empty values except for the ID which is set to "0"
     * </p>
     */
	public CampaignsModel() {
		this.id          = 0;
		this.name        = new String();
		this.fromName    = new String();
		this.fromEmail   = new String();
		this.subject     = new String();
		this.content     = new String();
		this.createdAt   = new Date();
		this.scheduledAt = new Date();
		this.sentOn      = new Date();
		this.listId      = 0;
		this.owner       = 0;
	}
	
	/**
     * CampaignsModel constructor with every attributes.
     * <p>
     * Creates the object with every values needed
     * </p>
     * 
     * @param id List unique ID.
     * @param name Campaign's name.
     * @param fromName Campaign's sender name
     * @param fromEmail Campaign's sender email
     * @param subject Campaign's subject
     * @param content Campaign's content
     * @param createdAt Campaign's creation date
     * @param scheduledAt Campaign's scheduled date
     * @param sentOn Campaign's send date
     * @param listId Campaign's list id
     * @param owner Campaign's owner id
     * @param page Current page
     * 
     * @see CampaignsModel#id
     * @see CampaignsModel#name
     * @see CampaignsModel#fromName
     * @see CampaignsModel#fromEmail
     * @see CampaignsModel#subject
     * @see CampaignsModel#content
     * @see CampaignsModel#createdAt
     * @see CampaignsModel#scheduledAt
     * @see CampaignsModel#sentOn
     * @see CampaignsModel#listId
     * @see CampaignsModel#owner
     * @see CampaignsModel#page
     */
	public CampaignsModel(int id, String name, String fromName, String fromEmail, String subject, String content, Date createdAt, Date scheduledAt, Date sentOn, int listId, int owner, int page) {
		this.id          = id;
		this.name        = name;
		this.fromName    = fromName;
		this.fromEmail   = fromEmail;
		this.subject     = subject;
		this.content     = content;
		this.createdAt   = createdAt;
		this.scheduledAt = scheduledAt;
		this.sentOn      = sentOn;
		this.listId      = listId;
		this.owner       = owner;
		this.page        = page;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fromName
	 */
	public String getFromName() {
		return fromName;
	}

	/**
	 * @param fromName the fromName to set
	 */
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	/**
	 * @return the fromEmail
	 */
	public String getFromEmail() {
		return fromEmail;
	}

	/**
	 * @param fromEmail the fromEmail to set
	 */
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the scheduledAt
	 */
	public Date getScheduledAt() {
		return scheduledAt;
	}

	/**
	 * @param scheduledAt the scheduledAt to set
	 */
	public void setScheduledAt(Date scheduledAt) {
		this.scheduledAt = scheduledAt;
	}

	/**
	 * @return the sentOn
	 */
	public Date getSentOn() {
		return sentOn;
	}

	/**
	 * @param sentOn the sentOn to set
	 */
	public void setSentOn(Date sentOn) {
		this.sentOn = sentOn;
	}

	/**
	 * @return the listId
	 */
	public int getListId() {
		return listId;
	}

	/**
	 * @param listId the listId to set
	 */
	public void setListId(int listId) {
		this.listId = listId;
	}

	/**
	 * @return the owner
	 */
	public int getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		if (page <= 0) {
			return 1;
		} else {
			return page;
		}
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the limit
	 */
	public static double getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public static void setLimit(double limit) {
		CampaignsModel.limit = limit;
	}
	
	/**
	 * Returns the number of campaigns belonging to the current user
	 *
	 * @param ownerId Campaign's owner's ID
	 * @return totalLists Number of campaigns
	 */
	public int numberOfCampaigns(int ownerId) {
		int totalCampaigns = 0;
		Model model        = new Model();
		try {
			String[] where  = {"C.owner = \"" + ownerId + "\""};
			ResultSet count = model.select("campaigns C", new String[] {"COUNT(*) as totalCampaigns"}, where, new String[0], new String[0], new double[2]);
			if (count != null) {
				while (count.next()) {
					totalCampaigns    = count.getInt("totalCampaigns");
				}
				count.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			model.closeConnection();
		}
		return totalCampaigns;
	}

	/**
	 * Returns array of CampaignsModel filled with CampaignsModel's objects
     * 
     * @param ownerId Campaign's owner's ID 
     * @return CampaignsModel array.
     */
	public CampaignsModel[] selectCampaigns(int ownerId, double page) {
		try {
			String table     = "campaigns C";
			String[] where   = {"C.owner = \"" + ownerId + "\""};
			Model model      = new Model();
			ResultSet result;
			if (page > 0) {
				int rowCount = this.numberOfCampaigns(ownerId);
				
				double numberOfPages = Math.ceil(rowCount/CampaignsModel.limit);			 
				if (page > numberOfPages) {
					page = numberOfPages;
				}
				double firstEntry = (page - 1) * CampaignsModel.limit;
				result = model.select(table, new String[0], where, new String[0], new String[0], new double[] {firstEntry, CampaignsModel.limit});
			} else {
				result = model.select(table, new String[0], where, new String[0], new String[0], new double[2]);
			}
			
			// Getting the resultSet's size:
			// We place the cursor to the last element
			result.last();
			// Then we get the current index of row
			int resultLength = result.getRow();
			// And finally, we place the cursor back to the first position
			result.beforeFirst();
			
			CampaignsModel[] campaigns = new CampaignsModel[resultLength];
			try {
				int i = 0;
				while (result.next()) {
					campaigns[i] = new CampaignsModel(result.getInt("id"), 
														result.getString("name"), 
														result.getString("from_name"), 
														result.getString("from_email"),
														result.getString("subject"),
														result.getString("content"),
														result.getDate("created_at"),
														result.getDate("scheduled_at"),
														result.getDate("sent_on"),
														result.getInt("list"), 
														result.getInt("owner"),
														1);
					i++;
				}
				return campaigns;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				model.closeConnection();				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get a campaign from database by it's id.
	 * Return null if this campaign doesn't exist.
	 * @param id
	 * @param owner
	 * @return CampaignsModel if exists | null if not exists
	 */
	public CampaignsModel selectCampaignById(int id, int owner) {
		try {
			String table     = "campaigns C";
			String[] where   = {"C.owner = \"" + owner + "\"", "C.id = " + id};
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], new double[] {1,0});
			
			try {
				if (result != null) {
					while (result.next()) {
						this.setId(result.getInt("id"));
						this.setName(result.getString("name"));
						this.setFromName(result.getString("from_name"));
						this.setFromEmail(result.getString("from_email"));
						this.setSubject(result.getString("subject"));
						this.setContent(result.getString("content"));
						this.setCreatedAt(result.getDate("created_at"));
						this.setScheduledAt(result.getDate("scheduled_at"));
						this.setSentOn(result.getDate("sent_on"));
						this.setListId(result.getInt("list"));
						this.setOwner(result.getInt("owner"));
					}
				}
				return this;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				model.closeConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * Inserts a new campaign into database.
     * Uses values from current object
     */
	public int insertCampaign() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String table               = "campaigns";
		String keys[]              = {"name", "from_name", "from_email", "subject", "content", "created_at", "scheduled_at", "sent_on", "list", "owner"};
		String values[]            = {this.getName(), 
									this.getFromName(),
									this.getFromEmail(),
									this.getSubject(),
									this.getContent(),
									formatter.format(this.getCreatedAt()),
									formatter.format(this.getScheduledAt()),
									formatter.format(this.getSentOn()),
									Integer.toString(this.getListId()),
									Integer.toString(this.getOwner())
								 };

		if (checkListBelonging(this.getListId())) {
			Model model = new Model();
			return model.insert(table, keys, values);
		} else {
			return 0;
		}
	}
	
	/**
	 * Check if the id sent belong the the current user
	 * @param id List id 
	 * @return true | false if the list belongs to the current user
	 */
	public boolean checkListBelonging(int id) {
		ListsModel listsModel = new ListsModel();
		ListsModel[] lists    = listsModel.selectLists(this.getOwner(), 0);
		
		// Insert every subscribers belonging to the user into an int table
		int[] userLists = new int[lists.length];
		for (int i = 0; i < lists.length; i++) {
			userLists[i] = lists[i].getId();
		}
		
		for (int i = 0; i < userLists.length; i++) {
			if (id == userLists[i]) {
				return true;
			}
		}
		
		return false;
	}
}
