package net.nfc.db.entity;

/**
 * 
 * @author Maciek
 *
 */
public class CDevices {
	private long id;
	private String deviceType;
	private String deviceImei;
	private CEmployee employee;
	private String deviceStatus;
	private String phoneNumber;
	private String imsi;
	private String description;

	public CDevices() {}
	
	public CDevices(String deviceType, String deviceImei, CEmployee employee,
			String deviceStatus, String phoneNumber, String imsi, String description) {
		super();
		this.deviceType = deviceType;
		this.deviceImei = deviceImei;
		this.employee = employee;
		this.deviceStatus = deviceStatus;
		this.phoneNumber = phoneNumber;
		this.imsi = imsi;
		this.description = description;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceImei() {
		return deviceImei;
	}
	public void setDeviceImei(String deviceImei) {
		this.deviceImei = deviceImei;
	}
	public CEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(CEmployee employee) {
		this.employee = employee;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
