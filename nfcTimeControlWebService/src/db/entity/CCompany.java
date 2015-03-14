package db.entity;

public class CCompany {
	private long id;
	private String name;
	private String street;
	private String city;
	private String postCode;
	private String country;
	private String phoneNumber;
	private String contactPersonName;
	private String contactPersonLastName;
	private String contactPersonDescription;
	private String nip;
	
	public CCompany() {}
	
	public CCompany(String name, String street, String city, String postCode, String country,
			String phoneNumber, String contactPersonName,
			String contactPersonLastName, String contactPersonDescription, String nip) {
		super();
		this.name = name;
		this.street = street;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.contactPersonName = contactPersonName;
		this.contactPersonLastName = contactPersonLastName;
		this.contactPersonDescription = contactPersonDescription;
		this.nip = nip;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonLastName() {
		return contactPersonLastName;
	}

	public void setContactPersonLastName(String contactPersonLastName) {
		this.contactPersonLastName = contactPersonLastName;
	}

	public String getContactPersonDescription() {
		return contactPersonDescription;
	}

	public void setContactPersonDescription(String contactPersonDescription) {
		this.contactPersonDescription = contactPersonDescription;
	}
	
	public String getCountry(){
		return country;
	}
		
	public void setCountry(String country) {
		this.country=country;
	}
	
	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}


}
