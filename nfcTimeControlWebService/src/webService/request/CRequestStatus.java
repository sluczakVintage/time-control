package webService.request;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import webService.CipherHelper;


@XmlRootElement
public class CRequestStatus {
	
	
	private String user_login;
	private String user_password;
	private String imei;
	private String tag_id;
	private String imsi;
	
	public CRequestStatus() {}
	
	public CRequestStatus(String user_login, String user_password, String imei,
			String tag_id, String imsi) {
		super();
		this.user_login = user_login;
		this.user_password = user_password;
		this.imei = imei;
		this.tag_id = tag_id;
		this.imsi = imsi;
	}
	
	public CRequestStatus decrypt()
	{
		CRequestStatus cRequestStatus;
		try {
			cRequestStatus = new CRequestStatus(CipherHelper.decrypt(user_login), CipherHelper.decrypt(user_password), CipherHelper.decrypt(imei), CipherHelper.decrypt(tag_id), CipherHelper.decrypt(imsi));
			return cRequestStatus; 
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

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}


	

}
