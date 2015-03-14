package webService.request;

import javax.xml.bind.annotation.XmlRootElement;

import webService.CipherHelper;

@XmlRootElement
public class CRequestAuthentication {

	private String user_login;
	private String user_password;
	private String imei;
	private String imsi;
	private String isAdmin;
	
	public CRequestAuthentication() {}
	
	public CRequestAuthentication(String user_login, String user_password,
			String imei,String imsi, String isAdmin) {
		super();
		this.user_login = user_login;
		this.user_password = user_password;
		this.imei = imei;
		this.imsi = imsi;
		this.isAdmin = isAdmin;
	}

	public CRequestAuthentication decrypt()
	{
		CRequestAuthentication cRequestAuthentication;
		try {
			cRequestAuthentication = new CRequestAuthentication(CipherHelper.decrypt(user_login), CipherHelper.decrypt(user_password), CipherHelper.decrypt(imei), CipherHelper.decrypt(imsi), CipherHelper.decrypt(isAdmin));
			return cRequestAuthentication; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	
	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
	
	
}
