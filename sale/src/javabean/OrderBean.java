package javabean;

public class OrderBean {
	private float price;
	private String name;
	private String description;
	private String username;
	private int id;

	public OrderBean() {
		super();
	}

	public OrderBean(float price, String name, String description,
			String username, int id) {
		super();
		this.price = price;
		this.name = name;
		this.description = description;
		this.username = username;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
