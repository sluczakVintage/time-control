package net.nfc.db.entity;

/**
 * 
 * @author Maciek
 *
 */
public class CEmployee {
	private long id;
	private String name;
	private String lastName;
	private String street;
	private String city;
	private String postCode;
	private String position;
	private CUsers user;
	private String email;
	
	public CEmployee() {}
	
	public CEmployee(String name, String lastName, String street, String city,
			String postCode, String position, CUsers user, String email) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.postCode = postCode;
		this.position = position;
		this.user = user;
		this.email = email;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public CUsers getUser() {
		return user;
	}
	public void setUser(CUsers user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
