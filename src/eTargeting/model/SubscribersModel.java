package eTargeting.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <b>The SubscribersModel class represents a list of subscribers and allows the user to create new subscribers.</b>
 * <p>
 * A subscriber is represented by the following information:
 * <ul>
 * <li>A unique ID.</li>
 * <li>A first name.</li>
 * <li>A last name.</li>
 * <li>An email address.</li>
 * <li>An age.</li>
 * <li>A gender.</li>
 * </ul>
 * </p>
 * 
 * @see Model
 * 
 * @author Axel
 * @version 1.0
 */
public class SubscribersModel {

	/**
     * The susbscriber's ID. This ID can't be updated
     * 
     * @see SubscribersModel#SubscribersModel(int, String, String, String, int, String)
     * @see SubscribersModel#getId()
     */
	private int id;
	
	/**
     * The susbscriber's first name.
     * 
     * @see SubscribersModel#SubscribersModel(int, String, String, String, int, String)
     * @see SubscribersModel#getFirstName()
     */
	private String firstName;
	
	/**
     * The susbscriber's last name.
     * 
     * @see SubscribersModel#SubscribersModel(int, String, String, String, int, String)
     * @see SubscribersModel#getLastName()
     */
	private String lastName;
	
	/**
     * The susbscriber's email.
     * 
     * @see SubscribersModel#SubscribersModel(int, String, String, String, int, String)
     * @see SubscribersModel#getEmail()
     */
	private String email;
	
	/**
     * The susbscriber's age.
     * 
     * @see SubscribersModel#SubscribersModel(int, String, String, String, int, String)
     * @see SubscribersModel#getAge()
     */
	private int age;
	
	/**
     * The susbscriber's gender.
     * 
     * @see SubscribersModel#SubscribersModel(int, String, String, String, int, String)
     * @see SubscribersModel#getGender()
     */
	private String gender;
	
	/**
     * The susbscriber's owner ID.
     * 
     * @see SubscribersModel#SubscribersModel(int, String, String, String, int, String)
     * @see SubscribersModel#getOwner()
     */
	private int ownerId;
	
	/**
	 * Current page
	 * 
	 * @see ListsModel#ListsModel(int, String, String, int)
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
     * SubscribersModel default constructor.
     * <p>
     * Set default values to the object with empty values except for the ID which is set to "0"
     * </p>
     */
	public SubscribersModel() {
		this.id = 0;
		this.firstName = new String();
		this.lastName  = new String();
		this.email     = new String();
		this.age       = 0;
		this.gender    = new String();
		this.ownerId   = 0;
	}
	
	/**
     * SubscribersModel constructor with every attributes.
     * <p>
     * Creates the object with every values needed
     * </p>
     * 
     * @see SubscribersModel#id
     * @see SubscribersModel#firstName
     * @see SubscribersModel#lastName
     * @see SubscribersModel#email
     * @see SubscribersModel#age
     * @see SubscribersModel#gender
     * @see SubscribersModel#ownerId
     */
	public SubscribersModel(int id, String firstName, String lastName, String email, int age, String gender, int ownerId) {
		this.id        = id;
		this.firstName = firstName;
		this.lastName  = lastName;
		this.email     = email;
		this.age       = age;
		this.gender    = gender;
		this.ownerId   = ownerId;
	}

	/**
	 * Returns subscriber's ID.
	 * 
	 * @return subscriber's ID. 
	 */
	public int getId() {
		return id;
	}

	/**
     * @param id Subscriber's ID to set
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns subscriber's first name. If null, returns "N/A".
	 * 
	 * @return subscriber's first name. 
	 */
	public String getFirstName() {
		return (firstName == null) ? "N/A" : firstName;
	}

	/**
     * @param id Subscriber's first name to set
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns subscriber's last name. If null, returns "N/A".
	 * 
	 * @return subscriber's last name. 
	 */
	public String getLastName() {
		return (lastName == null) ? "N/A" : lastName;
	}

	/**
     * @param id Subscriber's last name to set
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns subscriber's email.
	 * 
	 * @return subscriber's email. 
	 */
	public String getEmail() {
		return email;
	}

	/**
     * @param id Subscriber's email to set
     */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns subscriber's age.
	 * 
	 * @return subscriber's age. 
	 */
	public int getAge() {
		return age;
	}

	/**
     * @param id Subscriber's age to set
     */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Returns subscriber's gender. If null, returns "N/A".
	 * 
	 * @return subscriber's gender. 
	 */
	public String getGender() {
		return (gender == null) ? "N/A" : gender;
	}

	/**
     * @param id Subscriber's gender to set
     */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Returns subscriber's owner ID.
	 * 
	 * @return subscriber's owner ID. 
	 */
	public int getOwnerId() {
		return ownerId;
	}

	/**
     * @param id Subscriber's owner ID to set
     */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		if (page == 0) {
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
		SubscribersModel.limit = limit;
	}
	
	/**
	 * Returns the number of subscribers belonging to the current user
	 *
	 * @param ownerId Subscriber's owner's ID
	 * @param page Page of subscribers to get
	 * @return totalSubscribers Number of subscribers
	 */
	public int numberOfSubscribers(int ownerId) {
		Model model      = new Model();
		String table     = "subscribers S";
		String[] where = {"S.owner = \"" + ownerId + "\""};
		ResultSet count  = model.select(table, new String[] {"COUNT(*) as totalLists"}, where, new String[0], new String[0], new double[2]);
		int totalSubscribers   = 0;
		try {
			while (count.next()) {
				totalSubscribers    = count.getInt("totalLists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalSubscribers;
	}
	
	/**
	 * Returns array of SubscribersModel filled with SubscribersModel's objects
     * 
     * @param ownerId Subscriber's owner's ID 
     * @return SubscribersModel array.
     */
	public SubscribersModel[] selectSubscribers(int ownerId, double page) {
		try {
			String table     = "subscribers S";
			String[] where   = {"S.owner = \"" + ownerId + "\""};
			Model model      = new Model();
			ResultSet result;
			if (page > 0) {
				int rowCount = this.numberOfSubscribers(ownerId);
				
				double numberOfPages = Math.ceil(rowCount/SubscribersModel.limit);			 
				if (page > numberOfPages) {
					page = numberOfPages;
				}
				double firstEntry = (page - 1) * SubscribersModel.limit;
				result = model.select(table, new String[0], where, new String[0], new String[0], new double[] {firstEntry, SubscribersModel.limit});
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
			
			// Filling SubscribersModel's array with SubscribersModel objects
			SubscribersModel[] subscribers = new SubscribersModel[resultLength];
			try {
				int i = 0;
				while (result.next()) {
					int subscriberId  = result.getInt("id");
					int age           = result.getInt("age");
					int owner         = result.getInt("owner");
					String first_name = result.getString("first_name");
					String last_name  = result.getString("last_name");
					String email      = result.getString("email");
					String gender     = result.getString("gender");
					
					subscribers[i] = new SubscribersModel(subscriberId, first_name, last_name, email, age, gender, owner);
					i++;
				}
				// Closing connection
				model.closeConnection();
				return subscribers;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
     * Inserts a new subscriber into database.
     * Uses values from current object
     */
	public int insertSubscriber() {
		// Adding every keys and their values into two lists if they have been sent.
		ArrayList<String> keyList   = new ArrayList<String>();
		ArrayList<String> valueList = new ArrayList<String>();
		if (this.getEmail() != null && !"".equals(this.getEmail())) {
			keyList.add("email");
			valueList.add(this.getEmail());
		}
		if (this.getFirstName() != null && !"".equals(this.getFirstName())) {
			keyList.add("first_name");
			valueList.add(this.getFirstName());
		}
		if (this.getLastName() != null && !"".equals(this.getLastName())) {
			keyList.add("last_name");
			valueList.add(this.getLastName());
		}
		if (this.getAge() != 0) {
			keyList.add("age");
			valueList.add(Integer.toString(this.getAge()));
		}
		if (this.getGender() != null && !"".equals(this.getGender())) {
			keyList.add("gender");
			valueList.add(this.getGender());
		}
		if (this.getOwnerId() != 0) {
			keyList.add("owner");
			valueList.add(Integer.toString(this.getOwnerId()));
		}
		
		// Inserting values into database if some values have been sent
		if (!keyList.isEmpty() && keyList.contains("email") && !valueList.isEmpty()) {
			String table    = "subscribers";
			String[] keys   = new String[keyList.size()];
			String[] values = new String[valueList.size()];
			// Converting the ArrayList into String Array
			keyList.toArray(keys);
			valueList.toArray(values);
			
			Model model = new Model();
			return model.insert(table, keys, values);
		}
		return 0;
	}
	
	/**
     * Update a subscriber from database.
     * Uses values from current object
     */
	public void updateSubscriber() {
		// Adding every keys and their values into two lists if they have been sent.
		ArrayList<String> keyList   = new ArrayList<String>();
		ArrayList<String> valueList = new ArrayList<String>();
		
		if (this.getEmail() != null && !"".equals(this.getEmail())) {
			keyList.add("email");
			valueList.add(this.getEmail());
		}
		if (this.getFirstName() != null && !"".equals(this.getFirstName())) {
			keyList.add("first_name");
			valueList.add(this.getFirstName());
		}
		if (this.getLastName() != null && !"".equals(this.getLastName())) {
			keyList.add("last_name");
			valueList.add(this.getLastName());
		}
		if (this.getAge() != 0) {
			keyList.add("age");
			valueList.add(Integer.toString(this.getAge()));
		}
		if (this.getGender() != null && !"".equals(this.getGender())) {
			keyList.add("gender");
			valueList.add(this.getGender());
		}
		if (this.getOwnerId() != 0) {
			keyList.add("owner");
			valueList.add(Integer.toString(this.getOwnerId()));
		}
		
		// Checking that the subscriber is really possessed by the user
		SubscribersModel[] subscribers = this.selectSubscribers(this.getOwnerId(), 0);
		boolean allowedRequest = false;
		
		for (int i = 0; i < subscribers.length; i++) {
			if (subscribers[i].getId() == this.getOwnerId()){
				allowedRequest = true;
			}
		}
		
		// Inserting values into database if some values have been sent
		if (allowedRequest && !keyList.isEmpty() && keyList.contains("email") && !valueList.isEmpty()) {
			String table    = "subscribers";
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
	
	/**
	 * Delete subscriber from database, referenced by his ID
	 * @param subscriberId Subscriber's ID to delete
	 */
	public int deleteSubscriber(int[] aIds, int owner) {
		// Checking that the subscriber is really possessed by the user
		SubscribersModel[] subscribers = this.selectSubscribers(owner, 0);
		int[] allowedRequest = new int[aIds.length];
		
		for (int i = 0; i < aIds.length; i++) {
			int allowedId = 0;
			for (int j = 0; j < subscribers.length; j++) {
				if (subscribers[j].getId() == aIds[i]){
					allowedId = 1;
				}
			}
			allowedRequest[i] = allowedId;
		}
		
		// Delete the subscribers if it is allowed
		if (Arrays.binarySearch(allowedRequest, 0) < 0) {
			Model model = new Model();
			model.delete("subscribers", aIds);
			return 1;
		} else {
			return 0;
		}
	}
}
