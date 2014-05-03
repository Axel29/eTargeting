package eTargeting.model;

import java.sql.ResultSet;
import java.sql.SQLException;

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
public class SubscribersModel extends Model {

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
	 * Returns array of ListsModel filled with ListsModel's objects
     * 
     * @param ownerId List's owner's ID 
     * @return ListsModel array.
     */
	public SubscribersModel[] selectSubscribers(int ownerId) {
		try {
			String table     = "subscribers S";
			String[] where   = {"S.owner = \"" + ownerId + "\""};
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], 0);
			
			// Getting the resultSet's size:
			// We place the cursor to the last element
			result.last();
			// Then we get the current index of row
			int resultLength = result.getRow();
			// And finally, we place the cursor back to the first position
			result.beforeFirst();
			
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
     * Inserts a new list into database.
     * Uses values from current object
     */
	public void insertSubscriber() {
		String table    = "subscribers";
		String keys[]   = {"first_name", "last_name", "email", "age", "gender", "owner"};
		String values[] = {this.getFirstName(), this.getLastName(), this.getEmail(), Integer.toString(this.getAge()), this.getGender(), Integer.toString(this.getOwnerId())};
		Model model = new Model();
		model.insert(table, keys, values);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
}
