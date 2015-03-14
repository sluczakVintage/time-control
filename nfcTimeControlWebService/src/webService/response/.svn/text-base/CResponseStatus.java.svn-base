package webService.response;

import javax.xml.bind.annotation.XmlRootElement;

import webService.CipherHelper;

@XmlRootElement
public class CResponseStatus {

	private String status;
	private String comment;
	private String token;
	private long startDate;
	private String tag_id;
	
	public CResponseStatus() {} 
	
	public CResponseStatus(String status, String comment, String token, long date, String tagID) {
		try {
			this.status = CipherHelper.encrypt(status);
			this.comment = CipherHelper.encrypt(comment);
			this.tag_id = CipherHelper.encrypt(tagID);
			this.token = CipherHelper.encrypt(token);
			this.startDate = date;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	
	
	
	
}
