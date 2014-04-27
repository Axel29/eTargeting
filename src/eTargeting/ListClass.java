package eTargeting;

public class ListClass {
	private int id;
	private String name;
	private String subscriberIds;
	private int owner;
	
	public ListClass() {
		this.id          = 0;
		this.name        = new String();
		this.subscriberIds = new String();
		this.owner       = 0;
	}
	
	public ListClass(int id, String name, String subscribers, int owner){
		this.id            = id;
		this.name          = name;
		this.subscriberIds = subscribers;
		this.owner         = owner;
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
