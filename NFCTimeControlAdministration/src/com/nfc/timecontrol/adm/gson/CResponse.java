package com.nfc.timecontrol.adm.gson;

import com.google.gson.annotations.SerializedName;
import com.nfc.timecontrol.adm.CipherHelper;


public class CResponse {
	
	@SerializedName("status")
		private String status = "null";
	
	@SerializedName("message")
		private String message = "null";
	
	@SerializedName("token")
		private String token = "null";
	
	public CResponse() {

	}
	
	public CResponse(String status, String message, String token)
	{
		this.status = status;
		this.message = message;
		this.token = token;
	}

	public CResponse decrypt()
	{
		CResponse cResponse;
		try {
			cResponse = new CResponse(CipherHelper.decrypt(status), CipherHelper.decrypt(message), CipherHelper.decrypt(token));
			return cResponse; 
		} catch (Exception e) {

			e.printStackTrace();
		}
		return this;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
		
}
