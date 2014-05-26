package eTargeting.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <b>The ListsModel class represents a list containing one or several subscribers and allows the user to create new lists.</b>
 * <p>
 * A list is represented by the following information:
 * <ul>
 * <li>A unique ID.</li>
 * <li>A name.</li>
 * <li>A list of subscribers comma-separated.</li>
 * <li>The ID of the list's owner.</li>
 * <li>The current page number</li>
 * </ul>
 * </p>
 * 
 * @see Model
 * 
 * @author Axel
 * @version 1.0
 */
public class ListsModel {
	/**
     * The list's ID. This ID can't be updated
     * 
     * @see ListsModel#ListsModel(int, String, String, int)
     * @see ListsModel#getId()
     */
	private int id;
	
	/**
     * The list's name.
     * 
     * @see ListsModel#ListsModel(int, String, String, int)
     * @see ListsModel#getName()
     */
	private String name;
	
	/**
     * The list's subscriber's IDs, comma-separated.
     * 
     * @see ListsModel#ListsModel(int, String, String, int)
     * @see ListsModel#getSubscriberIds()
     */
	private String subscriberIds;
	
	/**
     * The list's owner's ID. This ID refers to the user who created it.
     * 
     * @see ListsModel#ListsModel(int, String, String, int)
     * @see ListsModel#getOwner()
     */
	private int owner;
	
	/**
	 * Current page
	 * 
	 * @see ListsModel#ListsModel(int, String, String, int, int)
	 * @see ListsModel#getPage()
	 */
	private int page;
	
	/**
	 * Number of list to display per page
	 * 
	 * @see ListsModel#getLimit()
	 */
	private static double limit = 10;
	
	/**
     * ListsModel default constructor.
     * <p>
     * Set default values to the object with empty values except for the ID which is set to "0"
     * </p>
     */
	public ListsModel() {
		this.id            = 0;
		this.name          = new String();
		this.subscriberIds = new String();
		this.owner         = 0;
	}
	
	/**
     * ListsModel constructor with every attributes.
     * <p>
     * Creates the object with every values needed
     * </p>
     * 
     * @param id List unique ID.
     * @param name List's name.
     * @param subscribers Subscribers ids belonging to that list
     * @param owner List's owner ID
     * @param page Current page
     * 
     * @see ListsModel#id
     * @see ListsModel#name
     * @see ListsModel#subscribers
     * @see ListsModel#owner
     * @see ListsModel#page
     */
	public ListsModel(int id, String name, String subscribers, int owner, int page){
		this.id            = id;
		this.name          = name;
		this.subscriberIds = subscribers;
		this.owner         = owner;
		this.setPage(page); 
	}
	
	/**
	 * Returns lits's ID.
	 * 
	 * @return list's ID. 
	 */
	public int getId() {
		return id;
	}

	/**
     * @param id List's ID to set
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns list's name.
	 * 
	 * @return list's name. 
	 */
	public String getName(){
		return this.name;
	}
	
	/**
     * @param name List's name to set
     */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Returns lit's subscriber's ids, comma-separated.
	 * 
	 * @return list's subscriber's ids. 
	 */
	public String getSubscriberIds() {
		return subscriberIds;
	}

	/**
     * @param subscriberIds List's subscribers ids (comma-separated) to set
     */
	public void setSubscriberIds(String subscriberIds) {
		this.subscriberIds = subscriberIds;
	}

	/**
	 * Returns list's owner's ID.
	 * 
	 * @return list's owner's ID. 
	 */
	public int getOwner() {
		return owner;
	}

	/**
     * @param owner List's owner's ID to set
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
	 * Number of lists to display per page
	 * @return the limit
	 */
	public static double getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public static void setLimit(double limit) {
		ListsModel.limit = limit;
	}
	
	/**
	 * Returns the number of lists belonging to the current user
	 *
	 * @param ownerId Lists' owner's ID
	 * @return totalLists Number of lists
	 */
	public int numberOfLists(int ownerId) {
		Model model      = new Model();
		String[] where = {"L.owner = \"" + ownerId + "\""};
		ResultSet count  = model.select("lists L", new String[] {"COUNT(*) as totalLists"}, where, new String[0], new String[0], new double[2]);
		int totalLists   = 0;
		try {
			while (count.next()) {
				totalLists    = count.getInt("totalLists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalLists;
	}

	/**
	 * Returns array of ListsModel filled with ListsModel's objects
     * 
     * @param ownerId Lists' owner's ID 
     * @return ListsModel array.
     */
	public ListsModel[] selectLists(int ownerId, double page) {
		try {
			String table     = "lists L";
			String[] where   = {"L.owner = \"" + ownerId + "\""};
			Model model      = new Model();
			ResultSet result;
			if (page > 0) {
				int rowCount = this.numberOfLists(ownerId);
				
				double numberOfPages = Math.ceil(rowCount/ListsModel.limit);			 
				if (page > numberOfPages) {
					page = numberOfPages;
				}
				double firstEntry = (page - 1) * ListsModel.limit;
				result = model.select(table, new String[0], where, new String[0], new String[0], new double[] {firstEntry, ListsModel.limit});
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
			
			ListsModel[] lists = new ListsModel[resultLength];
			try {
				int i = 0;
				while (result.next()) {
					lists[i] = new ListsModel(result.getInt("id"), result.getString("name"), result.getString("subscriber_ids"), result.getInt("owner"), 1);
					i++;
				}
				model.closeConnection();
				return lists;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get a list from database by it's id.
	 * Return null if this list doesn't exist.
	 * @param id
	 * @param owner
	 * @return ListsModel if exists | null if not exists
	 */
	public ListsModel selectListById(int id, int owner) {
		try {
			String table     = "lists L";
			String[] where   = {"L.owner = \"" + owner + "\"", "L.id = " + id};
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], new double[] {1,0});
			
			try {
				while (result.next()) {
					this.setId(result.getInt("id"));
					this.setName(result.getString("name"));
					this.setSubscriberIds(result.getString("subscriber_ids"));
					this.setOwner(result.getInt("owner"));
				}
				model.closeConnection();
				return this;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * Inserts a new list into database.
     * Uses values from current object
     */
	public int insertList() {
		String table           = "lists";
		String keys[]          = {"name", "subscriber_ids", "owner"};
		String values[]        = {this.getName(), ("".equals(this.getSubscriberIds())) ? "NULL" : this.getSubscriberIds(), Integer.toString(this.getOwner())};
		
		String[] subscriberIds = this.getSubscriberIds().split(",");
		int[] idsArray         = new int[subscriberIds.length];
		
		// Cast every subscriber's id into an integer array in order to check their belonging to the user
		if (!"".equals(this.getSubscriberIds())) {			
			for (int i = 0; i < subscriberIds.length; i++) {
				try {
					idsArray[i] = Integer.parseInt(subscriberIds[i]);
				} catch (NumberFormatException nfe) {}
			}
			
			// Insert the list if everything's correct 
			if (checkSubscribersBelonging(idsArray)) {
				Model model = new Model();
				return model.insert(table, keys, values);
			}
		} else {
			Model model = new Model();
			return model.insert(table, keys, values);
		}
		return 0;
	}
	
	/**
     * Update a list from database.
     * Uses values from current object
     */
	public void updateList() {
		// Check that the list belongs to that user
		ListsModel[] lists = this.selectLists(this.getOwner(), 0);
		boolean allowedList = false;
		for (ListsModel list : lists) {
			if (list.getId() == this.getId()) {
				allowedList = true;
				break;
			}
		}
		
		if (allowedList) {
			// Adding every keys and their values into two lists if they have been sent.
			ArrayList<String> keyList   = new ArrayList<String>();
			ArrayList<String> valueList = new ArrayList<String>();
			
			if (this.getName() != null && !"".equals(this.getName())) {
				keyList.add("name");
				valueList.add(this.getName());
			}
			if (this.getSubscriberIds() != null) {
				keyList.add("subscriber_ids");
				valueList.add(this.getSubscriberIds());
			}
			
			// Checking that the subscriber is really possessed by the user
			SubscribersModel subscribersModel = new SubscribersModel();
			SubscribersModel[] subscribers    = subscribersModel.selectSubscribers(this.getOwner(), 0);
			boolean allowedRequest = false;
			
			for (int i = 0; i < subscribers.length; i++) {
				if (subscribers[i].getId() == this.getOwner()){
					allowedRequest = true;
				}
			}
			
			// Inserting values into database if some values have been sent
			if (allowedRequest && !keyList.isEmpty() && keyList.contains("name") && !valueList.isEmpty()) {
				String table    = "lists";
				String[] keys   = new String[keyList.size()];
				String[] values = new String[valueList.size()];
				String[] where  = {"id = " + this.getId()};
				
				// Converting the ArrayList into String Array
				keyList.toArray(keys);
				valueList.toArray(values);
				
				Model model = new Model();
				model.update(table, keys, values, where);
			}
		}
	}
	
	/**
	 * Delete list from database, referenced by his ID
	 * @param listId List's ID to delete
	 */
	public int deleteList(int[] aIds, int owner) {
		// Checking that the list is really possessed by the user
		ListsModel[] lists   = this.selectLists(owner, 0);
		int[] allowedRequest = new int[aIds.length];
		
		for (int i = 0; i < aIds.length; i++) {
			int allowedId = 0;
			for (int j = 0; j < lists.length; j++) {
				if (lists[j].getId() == aIds[i]){
					allowedId = 1;
				}
			}
			allowedRequest[i] = allowedId;
		}
		
		// Delete the list if it is allowed
		if (Arrays.binarySearch(allowedRequest, 0) < 0) {
			Model model = new Model();
			model.delete("lists", aIds);
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Check if every ids sent belong the the current user
	 * @param ids Integer table containing every ids to user 
	 * @return true | false if every subscribers checked belong to the user
	 */
	private boolean checkSubscribersBelonging(int[] ids) {
		SubscribersModel subscribersModel = new SubscribersModel();
		SubscribersModel[] subscribers    = subscribersModel.selectSubscribers(this.getOwner(), 0);
		
		// Insert every subscribers belonging to the user into an int table
		int[] userSubscribers = new int[subscribers.length];
		for (int i = 0; i < subscribers.length; i++) {
			userSubscribers[i] = subscribers[i].getId();
		}
		
		for (int i = 0; i < ids.length; i++) {
			boolean allowedId = false;
			for (int j = 0; j < userSubscribers.length; j++) {
				if (ids[i] == userSubscribers[j]) {
					allowedId = true;
				}
			}
			
			if (!allowedId) {
				return false;
			}
			
		}
		return true;
	}
}
