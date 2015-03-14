package com.nfc.timecontrol.cl.gson;

import com.nfc.timecontrol.cl.CipherHelper;

public class CRequestRegistration {
	
	private String user_login;
	private String user_password;
	private String imei;
	private String imsi;
	
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	private String tag_id;
	private String token;
	private long date;
	
	public CRequestRegistration() {}
	
	public CRequestRegistration(String user_login, String user_password,
			String imei, String imsi, String tag_id, String token, long date) {
		
		try
		{
			this.user_login = CipherHelper.encrypt(user_login);
			this.user_password = CipherHelper.encrypt(user_password);
			this.imei = CipherHelper.encrypt(imei);
			this.imsi = CipherHelper.encrypt(imsi);
			this.tag_id = CipherHelper.encrypt(tag_id);
			this.token = CipherHelper.encrypt(token);
			this.date = date;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
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
	
}
