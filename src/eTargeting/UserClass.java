package eTargeting;

import java.io.StringReader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import au.com.bytecode.opencsv.CSVReader;

public class UserClass {
	private int userId;
	private String email;
	private String password;
	private String lastName;
	private String firstName;
	
	public UserClass() {
		this.userId    = 0;
		this.email     = new String();
		this.password  = new String();
		this.lastName  = new String();
		this.firstName = new String();
	}
	
	public UserClass(int userId, String email, String password, String lastName, String firstName) {
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
	
	public static int getLoggedUserId(HttpServletRequest request) {
		int userId           = 0;
		boolean cookieExists = false;
		try {
			Cookie[] cookies = request.getCookies();
			// Reading cookies to check if the user is logged in
			if (cookies != null) {
				System.out.println("COOKIES");
				for(int i=0; i<cookies.length; i++)
				{
					if ("user".equals(cookies[i].getName())) {
						// Getting user id from cookies' CSV
						StringReader stringReder = new StringReader(cookies[i].getValue());
						CSVReader csvReader      = new CSVReader(stringReder);
						String[] userValues      = csvReader.readNext();
						userId = Integer.parseInt(userValues[0]);
						csvReader.close();
						cookieExists = true;
					}
				}
				System.out.println("COOKIES EXISTS: " + cookieExists);
			}
			// Reading sessions to check if the user is logged in
			if(!cookieExists){
				System.out.println("SESSION");
				HttpSession session = request.getSession();
				if ((Integer)session.getAttribute("userId") != null) {
					userId = (Integer)session.getAttribute("userId");
				}
			}
			System.out.println("USERID: " + userId);
			return userId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;
	}
}
