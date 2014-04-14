package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnect {
	private static String url  = "jdbc:mysql://localhost:8889/calculatrice_java";
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
	public static void addCalcul(double number1, double number2,char operator,double resultat) throws Exception
	{
		try {		
			createDbConnect();
		
			String query = "Insert INTO calcul (id, nombre1, nombre2, operateur,resultat) values (NULL ,'"+number1+"','"+number2+"','"+operator+"','"+resultat+"'  )";
			statement.executeUpdate(query);

			stopDbConnect();
		}
		catch (Exception e) {
			throw e;
		}
		
	}
	
	public static boolean verifLogin(String login, String mdp){
		boolean bOk = false;
		try {
			createDbConnect();
			String countPersonne = "Select count(*) from acces where login='"+login+"' and motdepasse='"+mdp+"'";
			System.out.println(countPersonne);
			ResultSet resultPersonne = statement.executeQuery(countPersonne);
			
			while(resultPersonne.next())
			{
				if("1".equals(resultPersonne.getString(1))){
					bOk = true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur login n'existe pas !!!");
			e.printStackTrace();
		}
		
		return bOk;
		
	}

}