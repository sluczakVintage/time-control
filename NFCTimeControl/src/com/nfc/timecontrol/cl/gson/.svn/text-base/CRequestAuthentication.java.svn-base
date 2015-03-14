package com.nfc.timecontrol.cl.gson;

import com.google.gson.annotations.SerializedName;
import com.nfc.timecontrol.cl.CipherHelper;

public class CRequestAuthentication {

	@SerializedName("user_login")
	private String user_login;
	
	@SerializedName("user_password")
	private String user_password;
	
	@SerializedName("imei")
	private String imei;

	@SerializedName("imsi")
	private String imsi;
	
	@SerializedName("isAdmin")
	private String isAdmin;
	
	public String isAdmin() {
		return isAdmin;
	}

	public void setAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public CRequestAuthentication() {

	}
	
	public CRequestAuthentication(String user_login, String user_password, String imei, String imsi, String isAdmin) {
		try {
			this.user_login = CipherHelper.encrypt(user_login);
			this.user_password = CipherHelper.encrypt(user_password);
			this.imei = CipherHelper.encrypt(imei);
			this.imsi = CipherHelper.encrypt(imsi);
			this.isAdmin = CipherHelper.encrypt(isAdmin);
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
	
	
}
