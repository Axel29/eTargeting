package eTargeting;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import au.com.bytecode.opencsv.CSVReader;

public class UserModel extends Model {
	private int userId;
	private String email;
	private String password;
	private String lastName;
	private String firstName;
	
	public UserModel() {
		this.userId    = 0;
		this.email     = new String();
		this.password  = new String();
		this.lastName  = new String();
		this.firstName = new String();
	}
	
	public UserModel(int userId, String email, String password, String lastName, String firstName) {
		this.userId    = userId;
		this.email     = email;
		this.password  = password;
		this.lastName  = lastName;
		this.firstName = firstName;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
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
						this.userId    = Integer.parseInt(userValues[0]);
						this.email     = userValues[1];
						this.lastName  = userValues[2];
						this.firstName = userValues[3];
						csvReader.close();
						cookieExists = true;
					}
				}
			}
			// Reading sessions to check if the user is logged in
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
	
	public static UserModel login(String email, String password){
		UserModel user = new UserModel();
		try {
			String table     = "users U";
			String[] where   = {"U.email = \"" + email + "\"", "U.password = \"" + password + "\""};
			
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], 1);
			
			try {
				while (result.next()) {
					user.setUserId(result.getInt("id"));
					user.setEmail(email);
					user.setPassword(password);
					user.setLastName(result.getString("last_name"));
					user.setFirstName(result.getString("first_name"));
				}
				return user;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Erreur lors de la connexion");
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean checkUserExists(String email) {
		try {
			String table     = "users U";
			String[] where   = {"U.email = \"" + email + "\""};
			
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], 1);
			
			try {
				if (result.next()) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Erreur lors de la vŽrification");
			e.printStackTrace();
		}
		return false;
	}
	
	public static UserModel getUser(int userId) {
		UserModel user   = new UserModel();
		try {
			String table     = "users U";
			String[] where   = {"U.id = \"" + userId + "\""};
			
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], 1);
			
			try {
				while (result.next()) {
					user.setUserId(result.getInt("id"));
					user.setEmail(result.getString("email"));
					user.setPassword(result.getString("password"));
					user.setLastName(result.getString("last_name"));
					user.setFirstName(result.getString("first_name"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Erreur lors de la vŽrification");
			e.printStackTrace();
		}
		return user;
	}
	
	public int insertUser(){
		String table    = "users";
		String keys[]   = {"email", "password", "first_name", "last_name"};
		String values[] = {this.getEmail(), this.getPassword(), this.getFirstName(), this.getLastName()};
		Model model = new Model();
		return model.insert(table, keys, values);
	}
}
