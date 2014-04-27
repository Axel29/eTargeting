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
	@SuppressWarnings("unused")
	private static PreparedStatement preparedStatement = null;
	
	public Model() {
		getConnection();
	}
	
	private void getConnection() {
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
	
	protected ResultSet select(String table, String[] aFields, String[] aWhere, String[] aJoin, String[] aOrder, int limit) {
		try
		{
			String query = "SELECT ";
			// Fields to select
			if (aFields == null || aFields.length == 0) {
				query += "*";
			}
			for (int i=0; i<aFields.length; i++){
				if (aFields[i] != null && !aFields[i].isEmpty()) {
					if (i+1 != aFields.length) {
						query += aFields[i] + ", ";
					} else {
						query += aFields[i];
					}
				}
			}
			
			// From table
			query += " FROM " + table + " ";
			
			// Join tables
			for (int i=0; i<aJoin.length; i++){
				if (aJoin[i] != null && !aJoin[i].isEmpty()) {
					query += " INNER JOIN " + aJoin[i] + " ";
				}
			}
			
			// Where clause
			if (aWhere!= null && aWhere.length > 0) {
				query += " WHERE ";
			}
			for (int i=0; i<aWhere.length; i++){
				if (aWhere[i] != null && !aWhere[i].isEmpty()) {
					if (i+1 != aWhere.length) {
						query += aWhere[i] + " AND ";
					} else {
						query += aWhere[i];
					}
				}
			}
			
			// Order clause
			if (aOrder != null && aOrder.length > 0) {
				query += " ORDER BY ";
			}
			for (int i=0; i<aOrder.length; i++){
				if (aOrder[i] != null && !aOrder[i].isEmpty()) {
					if (i == 0) {
						query += aOrder[i];
					} else {
						query += ", " + aOrder[i];
					}
				}
			}
			
			// Limit clause
			query += limit != 0 ? " LIMIT " + limit : "";
			
			query += ";";
			
			System.out.println("SELECT query: " + query);
			resultSet = statement.executeQuery(query);
			return resultSet;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	protected int insert(String table, String[] aKeys, String[] aValues) {
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
			System.out.println("INSERT query: " + request);
			statement.executeUpdate(request);
			
			PreparedStatement lastInsertId = connection.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs = lastInsertId.executeQuery();
			int insertedId = 0;
			if (rs.next())  
			{  
				insertedId = rs.getInt("last_insert_id()");
			}  
			connection.close();
			return insertedId;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
