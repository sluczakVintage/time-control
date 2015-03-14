package net.nfc.web.forms;

import java.sql.Timestamp;

public class CSearchForm {

	private String className;
	private String userName;
	private long location_id;
	private String status;
	private Timestamp fromDate;
	private Timestamp toDate;
	private String name;
	private String city;
	private String nip;
	
	public CSearchForm() {}
	
	public CSearchForm(String className, String userName, long location_id,String name,String city,String nip,
			String status, Timestamp fromDate, Timestamp toDate) {
		super();
		this.className = className;
		this.userName = userName;
		this.location_id = location_id;
		this.status = status;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.name = name;
		this.city = city;
		this.nip=nip;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getFromDate() {
		return fromDate;
	}

	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}

	public Timestamp getToDate() {
		return toDate;
	}

	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}

	public long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}
	
	
	public String printAll() {
		return new String(city + " " + fromDate.toString() + " " + toDate.toString() + " " + location_id + " " + name + " " + nip + " " + status + " " + userName);
	}
	
	
}
