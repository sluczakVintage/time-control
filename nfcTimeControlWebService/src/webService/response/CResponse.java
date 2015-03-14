package webService.response;

import javax.xml.bind.annotation.XmlRootElement;

import webService.CipherHelper;

@XmlRootElement
public class CResponse {

	public String status;
	public String message;
	public  String token;
	
	public CResponse() {} 
	
	public CResponse(String status, String msg, String token) {
		try
		{
			this.status = CipherHelper.encrypt(status);
			this.message = CipherHelper.encrypt(msg);
			this.token = CipherHelper.encrypt(token);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
