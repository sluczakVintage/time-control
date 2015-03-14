package net.nfc.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CEventDAOImpl;
import net.nfc.db.dao.CUserDAOImpl;
import net.nfc.db.entity.CConstrains.responseStatus;
import net.nfc.db.entity.CEvent;
import net.nfc.db.entity.CUsers;

/**
 * 
 * @author Maciek
 *
 */

@Path("/status")
public class CEventStatus {
	
	CUserDAOImpl userDAO = new CUserDAOImpl();
	
	@POST		
	@Produces("application/json")
	@Consumes("application/json")
	public CResponseStatus Check(CRequestStatus request ) {
	
	System.out.println("Username " + request.getUser_login());
	System.out.println("Password " + request.getUser_password());
	System.out.println("Imei " + request.getImei());
	System.out.println("imsi " +request.getImsi());
	    	
	System.out.println("Connect");
	
	String user_name = request.getUser_login();
	String pass = request.getUser_password();
	String imei = request.getImei();
	String imsi = request.getImsi();
	
		
	try {
		CUsers user = userDAO.get(user_name);
		if(CUtility.checkUser(user, pass, imei, imsi )) {
		
			CEventDAOImpl eventDAO = new CEventDAOImpl();
			
			CEvent event = eventDAO.getEventStatus(user.getId());
			
			if(event != null ) {
			
			return  new CResponseStatus(event.getStatus(), event.getComment(),event.getToken(),event.getEventStartDate().getTime(), event.getTagId() );
			} 
			else return new CResponseStatus(null,null,null,0,null);
		}
		else return new CResponseStatus(responseStatus.WRONG_PASS.getText(),null,null,0,null);
		}catch (AdException e) {
		 System.out.println(e.getMessage());
		 System.out.println(responseStatus.USER_NOT_FOUND.getText());
		 return  new CResponseStatus(responseStatus.USER_NOT_FOUND.getText(),null,null,0,null);
		}
	}
}
