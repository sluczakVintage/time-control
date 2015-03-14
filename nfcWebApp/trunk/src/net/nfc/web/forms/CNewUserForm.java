package net.nfc.web.forms;

public class CNewUserForm {

	private String userName;
	private String userPassword;
	private String userPassword2;
	private long employeeID;
	
	public CNewUserForm() {}
	
	public CNewUserForm(String userName, String userPassword,
			String userPassword2, long employeeID) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPassword2 = userPassword2;
		this.employeeID = employeeID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPassword2() {
		return userPassword2;
	}

	public void setUserPassword2(String userPassword2) {
		this.userPassword2 = userPassword2;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}
	
	
	
	
	
}
