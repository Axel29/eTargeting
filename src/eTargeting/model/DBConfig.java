package eTargeting.model;

public class DBConfig {
	/**
	 * A string containing database url
	 */
	private String url = "jdbc:mysql://localhost/eTargeting";
	
	/**
	 * A string containing database user
	 */
	private String user = "root";

	/**
	 * A string containing database password
	 */
	private String pass = "root";
	
	/**
	 * Returns database url
	 * 
	 * @return list's owner's ID. 
	 */
	protected String getUrl() {
		return url;
	}

	protected String getUser() {
		return user;
	}

	protected String getPass() {
		return pass;
	}
}
