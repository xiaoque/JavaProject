package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "\"Person\"")
@DiscriminatorColumn(name = "DISCRIMINATOR_PERSON", discriminatorType = DiscriminatorType.STRING, length = 30)
public abstract class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="Seq", allocationSize=1)
	private int id;

	public int getId() {
		return id;
	}

	@Column(name = "username", length = 50)
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

}
