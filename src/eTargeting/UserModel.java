package eTargeting;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel extends Model {

	public UserModel() {
	}
	
	public static int login(String email, String password){
		int userId = 0;
		try {
			String table     = "users U";
			String[] where   = {"U.email = \"" + email + "\"", "U.password = \"" + password + "\""};
			
			Model model      = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], 1);
			
			try {
				while (result.next()) {
					userId = result.getInt(1);
				}
				return userId;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Erreur lors de la connexion");
			e.printStackTrace();
		}
		return userId;
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
	
	public int insertUser(User user){
		String table    = "users";
		String keys[]   = {"email", "password", "first_name", "last_name"};
		String values[] = {user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName()};
		Model model = new Model();
		return model.insert(table, keys, values);
	}
}
