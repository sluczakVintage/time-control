package com.nfc.timecontrol.adm.gson;

import com.google.gson.annotations.SerializedName;
import com.nfc.timecontrol.adm.CipherHelper;

public class CRequestAdministration {

	private String user_login;
	private String user_password;
	@SerializedName("imei")
	private String imei;
	@SerializedName("imsi")
	private String imsi;
	private String tag_id;
	private String company_nip;
	private String location_name;
	private String company_name;
	private long date;
	private String object_serial_number;
	
	public CRequestAdministration() {}
	
	public CRequestAdministration(String user_login, String user_password, String imei, String imsi, String tag_id, String company_nip, String company_name, String location_name, long date, String object_serial_number )
	{

		try {
			this.user_login =  CipherHelper.encrypt(user_login);
			this.user_password = CipherHelper.encrypt(user_password);
			this.imei = CipherHelper.encrypt(imei);
			this.imsi = CipherHelper.encrypt(imsi);
			this.tag_id = CipherHelper.encrypt(tag_id);
			this.company_nip = CipherHelper.encrypt(company_nip);
			this.company_name = CipherHelper.encrypt(company_name);
			this.location_name = CipherHelper.encrypt(location_name);
			this.date = date;
				this.object_serial_number = CipherHelper.encrypt(object_serial_number);
			} catch (Exception e) {

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

	public String getCompany_nip() {
		return company_nip;
	}

	public void setCompany_nip(String company_nip) {
		this.company_nip = company_nip;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public String getObject_serial_number() {
		return object_serial_number;
	}

	public void setObject_serial_number(String object_serial_number) {
		this.object_serial_number = object_serial_number;
	}
	
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
}
