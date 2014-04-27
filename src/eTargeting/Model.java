package eTargeting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Model {

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;
	
	public Model() {
		getConnection();
	}
	
	private void getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url  = "jdbc:mysql://localhost/eTargeting";
			String user = "root";
			String pass = "root";
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void insert(String table, String[] aKeys, String[] aValues){
		try
		{
			String keys = "";
			for (int i=0; i<aKeys.length; i++){
				// If the value matching to this key is not empty, we insert it, otherwise it's useless
				if (aValues[i] != null && !aValues[i].isEmpty()) {
					if (i+1 != aKeys.length) {
						keys += aKeys[i] + ", ";
					} else {
						keys += aKeys[i];
					}
				}
			}
			
			String values = "";
			for (int i=0; i<aValues.length; i++){
				if (aValues[i] != null && !aValues[i].isEmpty()) {
					if (i+1 != aValues.length) {
						values += "\"" + aValues[i] + "\", ";
					} else {
						values += "\"" + aValues[i] + "\"";
					}
				}				
			}
			
			String request = "INSERT INTO " + table + " (" + keys + ") VALUES (" + values + ");";
			System.out.println("request: " + request);
			statement.executeUpdate(request);
			System.out.println("Insert OK");
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
