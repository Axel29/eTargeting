package eTargeting;

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
}
