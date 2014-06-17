package eTargeting.model;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * <b>The UserModel class represents a user and allows to login, logout and verifies user's informations.</b>
 * <p>
 * A user is represented by the following information:
 * <ul>
 * <li>A unique ID.</li>
 * <li>An email.</li>
 * <li>A password (encrypted in MD5).</li>
 * <li>A first name.</li>
 * <li>A last name.</li>
 * </ul>
 * </p>
 * 
 * @see Model
 * 
 * @author Axel
 * @version 1.0
 */
public class UserModel {
	/**
     * The user ID. This ID can't be updated
     * 
     * @see UserModel#UserModel(int, String, String, String, String)
     * @see UserModel#getId()
     */
	private int userId;
	
	/**
     * The user email address. This email address must be unique
     * 
     * @see UserModel#UserModel(int, String, String, String, String)
     * @see UserModel#getId()
     */
	private String email;
	
	/**
     * The user's password. This password is encrypted in MD5
     * 
     * @see UserModel#UserModel(int, String, String, String, String)
     * @see UserModel#getPassword()
     */
	private String password;
	
	/**
     * The user's last name
     * 
     * @see UserModel#UserModel(int, String, String, String, String)
     * @see UserModel#getLastName()
     */
	private String lastName;
	
	/**
     * The user's first name.
     * 
     * @see UserModel#UserModel(int, String, String, String, String)
     * @see UserModel#getFirstName()
     */
	private String firstName;
	
	/**
     * UserModel constructor.
     * <p>
     * Set default values to the object with empty values except for the ID which is set to "0"
     * </p>
     */
	public UserModel() {
		this.userId    = 0;
		this.email     = new String();
		this.password  = new String();
		this.lastName  = new String();
		this.firstName = new String();
	}
	
	/**
     * UserModel constructor with every attributes.
     * <p>
     * Creates the object with every values needed
     * </p>
     * 
     * @see UserModel#id
     * @see UserModel#email
     * @see UserModel#password
     * @see UserModel#lastName
     * @see UserModel#firstName
     */
	public UserModel(int userId, String email, String password, String lastName, String firstName) {
		this.userId    = userId;
		this.email     = email;
		this.password  = password;
		this.lastName  = lastName;
		this.firstName = firstName;
	}
	
	/**
	 * @return user ID. 
	 */
	public int getUserId() {
		return userId;
	}

	/**
     * @param userId User ID to set
     */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return user email. 
	 */
	public String getEmail() {
		return email;
	}
	
	/**
     * @param email User email to set
     */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return user password. 
	 */
	public String getPassword() {
		return password;
	}
	
	/**
     * @param password User password to set
     */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return user last name. 
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
     * @param lastName User last name to set
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns user first name.
	 * 
	 * @return user first name. 
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
     * @param firstName User first name to set
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Returns a boolean according to his presence into database.
     * 
     * @param userId
     * @return UserModel filled with every user's informations
     */
	public static UserModel getUserById(int userId) {
		UserModel user   = new UserModel();
		try {
			String table     = "users U";
			String[] where   = {"U.id = \"" + userId + "\""};
			
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], new double[] {1,0});
			
			while (result.next()) {
				user.setUserId(result.getInt("id"));
				user.setEmail(result.getString("email"));
				user.setLastName(result.getString("last_name"));
				user.setFirstName(result.getString("first_name"));
			}
			model.closeConnection();
		} catch (Exception e) {
			System.out.println("Erreur lors de la v�rification");
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * Returns a UserModel object filled with every informations stocked in session or cookies
     * 
     * @param request Servlet request
     * @return User informations stock in session or cookies.
     */
	public UserModel getLoggedUser(HttpServletRequest request) {
		boolean cookieExists = false;
		try {
			Cookie[] cookies = request.getCookies();
			// Reading cookies to check if the user is logged in
			if (cookies != null) {
				for(int i=0; i<cookies.length; i++)
				{
					if ("user".equals(cookies[i].getName())) {
						// Getting user id from cookies' CSV
						StringReader stringReder = new StringReader(cookies[i].getValue());
						CSVReader csvReader      = new CSVReader(stringReder);
						String[] userValues      = csvReader.readNext();
						// Setting user's values to the current object
						this.userId    = Integer.parseInt(userValues[0]);
						this.email     = userValues[1];
						this.lastName  = userValues[2];
						this.firstName = userValues[3];
						// Close the csv reader
						csvReader.close();
						cookieExists = true;
					}
				}
			}
			// Reading sessions to check if the user is logged in if not present in cookies
			if(!cookieExists){
				HttpSession session = request.getSession();
				if ((Integer)session.getAttribute("userId") != null) {
					this.userId = (Integer)session.getAttribute("userId");
					this.email     = session.getAttribute("email").toString();
					this.lastName  = session.getAttribute("last_name").toString();
					this.firstName = session.getAttribute("first_name").toString();
				}
			}
			return this;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Returns a boolean according to his presence into database.
     * 
     * @param email user email address
     * @return True if the user exists and false if the user doesn't exist.
     */
	public boolean userExists(String email) {
		try {
			String table     = "users U";
			String[] where   = {"U.email = \"" + email + "\""};
			
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], new double[] {1,0});
			
			if (result.next()) {
				model.closeConnection();
				return true;
			}
		} catch (Exception e) {
			System.out.println("Erreur lors de la v�rification");
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Returns a UserModel object filled with every informations from he's email and password
     * 
     * @param email user email address
     * @param password MD5 encrypted password
     * @return User informations from database.
     */
	public static UserModel login(String email, String password){
		UserModel user = new UserModel();
		try {
			String table     = "users U";
			String[] where   = {"U.email = \"" + email + "\"", "U.password = \"" + password + "\""};
			
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], new double[] {1,0});
			
			while (result.next()) {
				user.setUserId(result.getInt("id"));
				user.setEmail(email);
				user.setLastName(result.getString("last_name"));
				user.setFirstName(result.getString("first_name"));
			}
			model.closeConnection();
			return user;
		} catch (Exception e) {
			System.out.println("Erreur lors de la connexion.");
			e.printStackTrace();
		}
		return user;
	}
	
	/**
     * Logout the user.
     * Destroys everything in session and the cookie if is set
     */
	public static void logout(HttpServletRequest request, HttpServletResponse response) {
		// Destoy session
		HttpSession session = request.getSession();
		session.invalidate();
		// Destroy user's cookie
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for(int i=0; i<cookies.length; i++)
			{
				if ("user".equals(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		}
	}
	
	/**
	 * Save the user's informations separately into session
	 * @param request
	 * @param user
	 */
	public void saveUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", this.getUserId());
		session.setAttribute("email", this.getEmail());
		session.setAttribute("password", this.getPassword());
		session.setAttribute("last_name", this.getLastName());
		session.setAttribute("first_name", this.getFirstName());
	}
	
	/**
	 * Save the user's informations separately into session
	 * @param request
	 * @param user
	 */
	public void saveUserCookies(HttpServletResponse response) {
		try {
			// Creating a CSV from user's values in order to put it in one cookie
			StringWriter stringWriter = new StringWriter();
			CSVWriter csvWriter       = new CSVWriter(stringWriter);
			String[] userValues       = {Integer.toString(this.getUserId()), this.getEmail(), this.getLastName(), this.getFirstName()};
			System.out.println("email: " + this.getEmail() + "... - firstName: " + this.getFirstName() + "... - lastName: " + this.getLastName() + "...");
			// Writing into the string writer
			csvWriter.writeNext(userValues);
			csvWriter.close();
			System.out.println("stringWriter.toString(): " + stringWriter.toString() + "...");
			Cookie cookie = new Cookie("user", stringWriter.toString());
			// Setting expire date for cookies to current day + 30 days
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Inserts a new user into database.
     * Uses values from current object
     */
	public int insertUser(){
		// Adding every keys and their values into two lists if they have been sent.
		ArrayList<String> keyList   = new ArrayList<String>();
		ArrayList<String> valueList = new ArrayList<String>();		
		if (this.getEmail() != null && !"".equals(this.getEmail())) {
			keyList.add("email");
			valueList.add(this.getEmail());
		}		
		if (this.getPassword() != null && !"".equals(this.getPassword())) {
			keyList.add("password");
			valueList.add(this.getPassword());
		}
		if (this.getFirstName() != null && !"".equals(this.getFirstName())) {
			keyList.add("first_name");
			valueList.add(this.getFirstName());
		}
		if (this.getLastName() != null && !"".equals(this.getLastName())) {
			keyList.add("last_name");
			valueList.add(this.getLastName());
		}
		
		// Inserting values into database if some values have been sent
		if (!keyList.isEmpty() && keyList.contains("email") && !valueList.isEmpty()) {
			String table    = "users";
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
}
