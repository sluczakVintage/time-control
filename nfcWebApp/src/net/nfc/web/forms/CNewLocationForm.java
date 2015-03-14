package net.nfc.web.forms;
import java.sql.Timestamp;
/**
 * 
 * @author Maciek
 *
 */

public class CNewLocationForm {
	private long id;
	private String street;
	private String city;
	private String postCode;
	private String details;
	private String tagID;
	private String name;
	private String status;
	private String serialNumber;
	private long company_id;

	public CNewLocationForm() {}

	public CNewLocationForm(long id, String street, String city,
			String postCode, String details, String tagID, String name,
			String status, String serialNumber, long company_id) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.postCode = postCode;
		this.details = details;
		this.tagID = tagID;
		this.name = name;
		this.status = status;
		this.serialNumber = serialNumber;
		this.company_id = company_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTagID() {
		return tagID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
	
	
}
