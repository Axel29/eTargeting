package eTargeting.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>The Model class is the core Model used for every model's implementation.</b>
 * <p>
 * If defines generic methods used to perform requests into database as select, insert, update, delete
 * </p>
 * 
 * @author Axel
 * @version 1.0
 */
public class Model {
	/**
     * A connection (session) with a specific database.
     */
	private static Connection connection = null;
	
	/**
     * The object used for executing a static SQL statement and returning the results it produces.
     */
	private static Statement statement = null;
	
	/**
     * A table of data representing a database result set, which is usually generated by executing a statement that queries the database.
     */
	private static ResultSet resultSet = null;
	
	/**
     * An object that represents a precompiled SQL statement.
     */
	@SuppressWarnings("unused")
	private static PreparedStatement preparedStatement = null;
	
	/**
     * Model constructor.
     * <p>
     * Set up a connection to database
     * </p>
     */
	public Model() {
		getConnection();
	}
	
	/**
	 * Gets connection and set it to the Connection's object
	 */
	private void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url  = "jdbc:mysql://localhost/eTargeting";
			String user = "root";
			String pass = "root";
			connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement();
		} catch(SQLException se) {
	      se.printStackTrace();
		} catch(Exception e){
	      e.printStackTrace();
		}
	}
	
	/**
	 * Returns a ResultSet object containing query's result 
     * 
     * @param table Table name for select
     * @param aFields Array of string containing every field to select
     * @param aWhere Array of string containg every where conditions
     * @param aJoin Array of string containg every join conditions
     * @param aOrder Array of string containg every order conditions
     * @param limit Number of maximum results to get
     * @return ResultSet containing query's result.
     */
	protected ResultSet select(String table, String[] aFields, String[] aWhere, String[] aJoin, String[] aOrder, int limit) {
		try {
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
			
			//System.out.println("SELECT query: " + query);
			resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns a ResultSet object containing query's result 
     * 
     * @param table Table name for select
     * @param aKeys Array of string containing every field's name to insert
     * @param aValues Array of string containing every field's values to insert
     * @return Last inserted ID.
     */
	protected int insert(String table, String[] aKeys, String[] aValues) {
		try {
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
			//System.out.println("INSERT query: " + request);
			statement.executeUpdate(request);
			
			PreparedStatement lastInsertId = connection.prepareStatement("SELECT LAST_INSERT_ID()");
			ResultSet rs                   = lastInsertId.executeQuery();
			int insertedId = 0;
			if (rs.next())  
			{  
				insertedId = rs.getInt("last_insert_id()");
			}  
			connection.close();
			return insertedId;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}