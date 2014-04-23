package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="\"Order\"")
public class Order {

	public Order() {
		super();
	}

	public Order( String username) {
		super();
		this.username = username;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="Seq", allocationSize=1)
	@Column(name = "orderid")
	private int orderid;
	private String username;
	
	@ManyToOne(cascade=CascadeType.REMOVE)  
	@JoinColumn(name = "productid", referencedColumnName = "productid", nullable=false)
	private Product product;
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	@Column(name ="username", length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
