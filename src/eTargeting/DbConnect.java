package eTargeting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;




public class DbConnect {
	private static String url  = "jdbc:mysql://localhost/eTargeting";
	private static String user = "root";
	private static String pass = "root";
	private static Connection conn;
	private static Statement statement;
	
	public static void createDbConnect() throws Exception {
		try{

			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, pass);

			statement = conn.createStatement();

		}
		catch(Exception e){
			throw e;
		}
		
	}
	
	public static void stopDbConnect() throws Exception {
		try{
			conn.close();
		}
		catch(Exception e){
			throw e;
		}
	}
	
	public static void addUser(String email, String password, String first_name, String last_name) throws Exception
	{
		try {		
			createDbConnect();
		
			String query = "INSERT INTO `eTargeting`.`users` (`id`, `email`, `password`, `first_name`, `last_name`) VALUES (NULL, '"+email+"', '"+password+"', '"+first_name+"', '"+last_name+"')";
			statement.executeUpdate(query);

			stopDbConnect();
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	public static int verifLogin(String login, String mdp){
		int userId = 0;
		try {
			createDbConnect();
			String request = "SELECT * FROM users WHERE email = '" + login + "' AND password = '" + mdp + "'";
			ResultSet result = statement.executeQuery(request);
			
			while(result.next())
			{
				if("1".equals(result.getString(1))){
					userId = result.getInt(1);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur login n'existe pas !!!");
			e.printStackTrace();
		}
		
		return userId;
		
	}

}
