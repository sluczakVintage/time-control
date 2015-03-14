package com.nfc.timecontrol.cl.gson;

import com.google.gson.annotations.SerializedName;
import com.nfc.timecontrol.cl.CipherHelper;

public class CRequestStatus {

	@SerializedName("user_login")
	private String user_login;
	
	@SerializedName("user_password")
	private String user_password;
	
	@SerializedName("imei")
	private String imei;
	
	@SerializedName("imsi")
	private String imsi;

	public CRequestStatus() {

	}
	
	public CRequestStatus(String user_login, String user_password, String imei, String imsi) {
		try
		{
			this.user_login = CipherHelper.encrypt(user_login);
			this.user_password = CipherHelper.encrypt(user_password);
			this.imei = CipherHelper.encrypt(imei);
			this.imsi = CipherHelper.encrypt(imsi);
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

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImsi() {
		return imsi;
	}
	
	
}
