package net.nfc.web.forms;

public class CAddDeviceToEmployeeForm {

	private long device_id;

	public long getDevice_id() {
		return device_id;
	}

	public void setDevice_id(long device_id) {
		this.device_id = device_id;
	}

	public CAddDeviceToEmployeeForm(long device_id) {
		super();
		this.device_id = device_id;
	}
	
	public CAddDeviceToEmployeeForm(){}
	
}
