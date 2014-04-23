package javabean;

public class CurrentUser {
	private int id;
	private String name;
	public CurrentUser(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public CurrentUser() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
