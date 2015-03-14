package com.nfc.timecontrol.cl.gson;

import com.google.gson.annotations.SerializedName;
import com.nfc.timecontrol.cl.CipherHelper;

public class CResponseStatus {

	@SerializedName("status")
	private String status;
	
	@SerializedName("comment")
	private String comment;
	
	@SerializedName("tag_id")
	private String tag_id;

	@SerializedName("token")
	private String token;

	@SerializedName("startDate")
	private long startDate;

	public CResponseStatus() {

	}
	
	public CResponseStatus(String status, String comment, String tag_id, String token, long startDate) {
		this.status = status;
		this.comment = comment;
		this.tag_id = tag_id;
		this.token = token;
		this.startDate = startDate;
	}

	public CResponseStatus decrypt()
	{
		CResponseStatus cResponseStatus;
		try {
			cResponseStatus = new CResponseStatus(CipherHelper.decrypt(status), CipherHelper.decrypt(comment), CipherHelper.decrypt(tag_id), CipherHelper.decrypt(token), startDate);
			return cResponseStatus; 
		} catch (Exception e) {

			e.printStackTrace();
		}
		return this;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getTag_id() {
		return tag_id;
	}

}
