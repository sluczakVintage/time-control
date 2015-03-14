package net.nfc.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CConstrains.responseStatus;
import net.nfc.db.entity.CUsers;
import net.nfc.db.dao.CUserDAOImpl;
/**
 * 
 * @author Maciek
 *
 */

@Path ("/auth")
public class CAuthentication {
	
	
	private CUserDAOImpl userDAO = new CUserDAOImpl();
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public CResponse Authenticate( CRequestAuthentication request 	)  {
		
		System.out.println("Username " + request.getUser_login());
		System.out.println("Password " + request.getUser_password());
		System.out.println("Imei " + request.getImei());
		System.out.println("imsi " +request.getImsi());
		
		String user_name = request.getUser_login();
		String pass = request.getUser_password();
		String imei = request.getImei();
		String imsi = request.getImsi();
				
		try {
			CUsers user = userDAO.get(user_name);
			
			if(CUtility.checkUser(user, pass, imei, imsi )) {
			//if(user != null && user.getUserPassword().equals(pass)) {
				return  new CResponse(responseStatus.ACCESS_GRANTED.getText(), null, null);
			}
			return new CResponse(responseStatus.ACCESS_DENIED.getText(), null, null);
		}catch (AdException e) {
		 System.err.println("AdException " + e.getMessage());
		 return  new CResponse(responseStatus.ACCESS_DENIED.getText(), null, null);
	 }
	}
}
