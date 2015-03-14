package webService.request;

import javax.xml.bind.annotation.XmlRootElement;

import webService.CipherHelper;
/**
 * 
 * @author Maciek
 *
 */
@XmlRootElement
public class CRequestRegistration {

	private String user_login;
	private String user_password;
	private String imei;
	private String imsi;
	private String tag_id;
	private String token;
	private long date;
	
	public CRequestRegistration() {}
	
	public CRequestRegistration(String user_login, String user_password,
			String imei, String tag_id, String token, long date, String imsi) {
		super();
		this.user_login = user_login;
		this.user_password = user_password;
		this.imei = imei;
		this.tag_id = tag_id;
		this.token = token;
		this.date = date;
		this.imsi = imsi;
	}

	public CRequestRegistration decrypt()
	{
		CRequestRegistration cRequestRegistration;
		try {
			cRequestRegistration = new CRequestRegistration(CipherHelper.decrypt(user_login), CipherHelper.decrypt(user_password), CipherHelper.decrypt(imei), CipherHelper.decrypt(tag_id), CipherHelper.decrypt(token), date, CipherHelper.decrypt(imsi));
			return cRequestRegistration; 
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	
	
	
	
	
	
}
