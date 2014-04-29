package eTargeting;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <b>The ListsModel class represents a list of subscribers and allows the user to create new lists.</b>
 * <p>
 * A list is represented by the following information:
 * <ul>
 * <li>A unique ID.</li>
 * <li>A name.</li>
 * <li>A list of subscribers comma-separated.</li>
 * <li>The ID of the list's owner.</li>
 * </ul>
 * </p>
 * 
 * @see Model
 * 
 * @author Axel
 * @version 1.0
 */
public class ListsModel extends Model {
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
     * ListsModel constructor.
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
     * @see ListsModel#id
     * @see ListsModel#name
     * @see ListsModel#ubscribers
     * @see ListsModel#owner
     */
	public ListsModel(int id, String name, String subscribers, int owner){
		this.id            = id;
		this.name          = name;
		this.subscriberIds = subscribers;
		this.owner         = owner;
	}

	/**
	 * Returns array of ListsModel filled with ListsModel's objects
     * 
     * @param ownerId List's owner's ID 
     * @return ListsModel array.
     */
	public ListsModel[] selectLists(int ownerId) {
		try {
			String table     = "lists L";
			String[] where   = {"L.owner = \"" + ownerId + "\""};
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], 0);
			
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
					lists[i] = new ListsModel(result.getInt("id"), result.getString("name"), result.getString("subscriber_ids"), result.getInt("owner"));
					i++;
				}
				//System.out.println(lists[0]);
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
     * Inserts a new list into database.
     * Uses values from current object
     */
	public void insertList() {
		String table    = "lists";
		String keys[]   = {"name", "subscriber_ids", "owner"};
		String values[] = {this.getName(), this.getSubscriberIds(), Integer.toString(this.getOwner())};
		Model model = new Model();
		model.insert(table, keys, values);
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
}
