package eTargeting;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListsModel extends Model {
	private int id;
	private String name;
	private String subscriberIds;
	private int owner;
	
	public ListsModel() {
		this.id          = 0;
		this.name        = new String();
		this.subscriberIds = new String();
		this.owner       = 0;
	}
	
	public ListsModel(int id, String name, String subscribers, int owner){
		this.id            = id;
		this.name          = name;
		this.subscriberIds = subscribers;
		this.owner         = owner;
	}
	
	public ListsModel[] selectLists(int ownerId) {
		try {
			String table = "lists L";
			String[] where = {"L.owner = \"" + ownerId + "\""};
			/*
			 * Exemple avec champs à sélectionner
			String[] fields = {"L.id", "name", "subscriber_ids", "owner"};
			String[] where = {"L.id = \"1\"", "subscriber_ids IS NULL"};
			String[] join = {"users AS u ON L.owner = u.id"};
			String[] order = {"L.id DESC", "owner DESC"};
			int limit = 100;
			model.select(table, fields, where, join, order, limit);
			*/
			Model model = new Model();
			ResultSet result = model.select(table, new String[0], where, new String[0], new String[0], 0);
			
			// Getting the resultSet's size:
			// We place the cursor to the last element
			result.last();
			// Then we get the current index of row
			int resultLength = result.getRow();
			// And finally, we place the cursor back to the first position
			result.beforeFirst();
			
			ListsModel[] lists = new ListsModel[resultLength];
			try {
				int i = 0;
				while (result.next()) {
					lists[i] = new ListsModel(result.getInt("id"), result.getString("name"), result.getString("subscriber_ids"), result.getInt("owner"));
					i++;
				}
				//System.out.println(lists[0]);
				return lists;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertList() {
		String table    = "lists";
		String keys[]   = {"name", "subscriber_ids", "owner"};
		String values[] = {this.getName(), this.getSubscriberIds(), Integer.toString(this.getOwner())};
		Model model = new Model();
		model.insert(table, keys, values);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getSubscriberIds() {
		return subscriberIds;
	}

	public void setSubscriberIds(String subscriberIds) {
		this.subscriberIds = subscriberIds;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
}
