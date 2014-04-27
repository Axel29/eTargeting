package eTargeting;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel extends Model {

	public UserModel() {
	}
	
	public static UserClass login(String email, String password){
		UserClass user = new UserClass();
		int userId = 0;
		String first_name;
		String last_name;
		try {
			String table     = "users U";
			String[] where   = {"U.email = \"" + email + "\"", "U.password = \"" + password + "\""};
			
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], 1);
			
			try {
				while (result.next()) {
					userId         = result.getInt("id");
					first_name     = result.getString("first_name");
					last_name      = result.getString("last_name");
					user.setUserId(userId);
					user.setEmail(email);
					user.setPassword(password);
					user.setLastName(last_name);
					user.setFirstName(first_name);
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
			System.out.println("Erreur lors de la vérification");
			e.printStackTrace();
		}
		return false;
	}
	
	public static UserClass getUser(int userId) {
		UserClass user   = new UserClass();
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
			System.out.println("Erreur lors de la vérification");
			e.printStackTrace();
		}
		return user;
	}
	
	public int insertUser(UserClass user){
		String table    = "users";
		String keys[]   = {"email", "password", "first_name", "last_name"};
		String values[] = {user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName()};
		Model model = new Model();
		return model.insert(table, keys, values);
	}
}
