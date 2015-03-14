package webService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import webService.request.CRequestAuthentication;
import webService.response.CResponse;

import db.dao.AdException;
import db.dao.CUserDAOImpl;
import db.dao.CUserPrivilegesDAOImpl;
import db.entity.CConstrains.responseStatus;
import db.entity.CConstrains.userPrivileges;
import db.entity.CUsers;
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
		
		request = request.decrypt();
		System.out.println("Username " + request.getUser_login());
		System.out.println("Password " + request.getUser_password());
		System.out.println("Imei " + request.getImei());
		System.out.println("imsi " +request.getImsi());
		
		String user_name = request.getUser_login();
		String pass = request.getUser_password();
		String imei = request.getImei();
		String imsi = request.getImsi();
		String isAdmin = request.getIsAdmin();		
		
		try {
			CUsers user = userDAO.get(user_name);
			if(CUtility.checkUser(user, pass, imei, imsi )) {
				
				if(isAdmin.equals("1")) {
					CUserPrivilegesDAOImpl userPriv = new CUserPrivilegesDAOImpl();
					//sprawdzanie roli usera - pozwalamy je�eli ma uprawnienia super_user albo classic_admin
					if(userPriv.get(user.getId()).getRole().equals(userPrivileges.SUPER_USER.getText()) || userPriv.get(user.getId()).getRole().equals(userPrivileges.ADMIN.getText())){
						System.out.println("ACCESS_GRANTED");
						return  new CResponse(responseStatus.ACCESS_GRANTED.getText(), null, null);
					}else {
						System.out.println("ACCESS_DENIED");
						return new CResponse(responseStatus.ACCESS_DENIED.getText(),null,null);
					}
				}
				else System.out.println("ACCESS_GRANTED"); return  new CResponse(responseStatus.ACCESS_GRANTED.getText(), null, null);
			}
			return new CResponse(responseStatus.ACCESS_DENIED.getText(), null, null);
		}catch (AdException e) {
		 System.err.println("AdException " + e.getMessage());
		 return  new CResponse(responseStatus.ACCESS_DENIED.getText(), null, null);
	 }
	}
}
