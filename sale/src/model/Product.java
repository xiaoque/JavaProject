package model;

import javax.persistence.*;

@Entity
@Table(name="\"Product\"")
public class Product {
	public Product(){
		super();
	}

	public Product(String nnn,float pp){
		super();
		this.name = nnn;
		this.price = pp;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="Seq", allocationSize=1)
	@Column(name = "productid")
	private int productid;


	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	@Column(name = "productname",length = 50)
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "price")
	private float price;
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Column(name = "description")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
