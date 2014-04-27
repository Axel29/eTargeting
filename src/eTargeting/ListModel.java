package eTargeting;

public class ListModel extends Model {	
	public ListModel() {
	}
	
	public void insertList(ListClass list){
		String table    = "lists";
		String keys[]   = {"name", "subscriber_ids", "owner"};
		String values[] = {list.getName(), list.getSubscriberIds(), Integer.toString(list.getOwner())};
		Model model = new Model();
		model.insert(table, keys, values);
	}
}
