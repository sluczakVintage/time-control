package net.nfc.webservice;

import java.sql.Timestamp;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CCompanyDAOImpl;
import net.nfc.db.dao.CLocationDAOImpl;
import net.nfc.db.dao.CUserDAOImpl;
import net.nfc.db.dao.CUserPrivilegesDAOImpl;
import net.nfc.db.entity.CCompany;
import net.nfc.db.entity.CConstrains.locationStatus;
import net.nfc.db.entity.CConstrains.responseStatus;
import net.nfc.db.entity.CConstrains.userPrivileges;
import net.nfc.db.entity.CLocation;
import net.nfc.db.entity.CUsers;
/**
 * 
 * @author Maciek
 *
 */

@Path ("/adm")
public class CAdministration {
	
	/**
	 * g³ówna metoda rozpoznaj¹ca i przetwarzaj¹ca rejestracje nowego TAGU
	 * @param request
	 * @return status
	 * @throws AdException
	 */
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public CResponse registerNewTag(CRequestAdministration request) throws AdException{

		long Ldate = request.getDate();
		String user_name = request.getUser_login();
		String pass = request.getUser_password();
		String tagID = request.getTag_id();
		String nip = request.getCompany_nip();
		String companyName = request.getCompany_name();
		String objectSerialNumber = request.getObject_serial_number();
		String imsi = request.getImsi();
		String imei = request.getImei();
				
		Timestamp date = new Timestamp(Ldate);		
		
		CUserDAOImpl userDAO = new CUserDAOImpl();
		try {
				
			CUsers user = userDAO.get(user_name);
			
			if(CUtility.checkUser(user, pass, imei, imsi )) {
				//if(user != null && user.getUserPassword().equals(pass)) {
				
				CUserPrivilegesDAOImpl userPriv = new CUserPrivilegesDAOImpl();
				//sprawdzanie roli usera - pozwalamy je¿eli ma uprawnienia super_user albo classic_admin
				if(userPriv.get(user.getId()).getRole().equals(userPrivileges.SUPER_USER.getText()) || userPriv.get(user.getId()).getRole().equals(userPrivileges.ADMIN.getText())){
				
					CLocationDAOImpl locationDAO = new CLocationDAOImpl();
					
					//Jesli nie ma takiego tagu  
					if(locationDAO.checkTag(tagID ) == false ) {
						//Create new localization
						CCompanyDAOImpl companyDAO = new CCompanyDAOImpl();
						boolean isNewCompanyCreated = false;
						
						if(companyDAO.checkNIP(nip) == false ) {
							//create new company
						
							CCompany newCompany = new CCompany();
							newCompany.setName(companyName);
							newCompany.setNip(nip);
							
							companyDAO.create(newCompany);
							isNewCompanyCreated = true;
						}
						CCompany cmp = companyDAO.get(nip);
						CLocation newLocation = new CLocation();
						
						newLocation.setCompany(cmp);
						newLocation.setStatus(locationStatus.CREATED.getText());
						newLocation.setCreationDate(date);
						newLocation.setTagID(tagID);
						newLocation.setCreator(user);
						newLocation.setSerialNumber(objectSerialNumber);
							
						locationDAO.create(newLocation);
							
						if(isNewCompanyCreated) {
							return new CResponse(responseStatus.LOCATION_CREATED_COMPANY_CREATED.getText(), null,null); 
						}
						else return new CResponse(responseStatus.LOCATION_CREATED.getText(),null,null);
					}
					else return new CResponse(responseStatus.TAG_ALREADY_EXISTS.getText(),null,null);
				}
				else return new CResponse(responseStatus.WRONG_PASS.getText(), null,null); // zwracam gdy nie ma wystarczaj¹cych uprawnieñ
			}
			else {
				return new CResponse(responseStatus.WRONG_PASS.getText(),null,null);
			}
		}catch(AdException e) {
			System.out.println(e.getMessage());
			return new CResponse(responseStatus.SERVER_ERROR.getText(),null,null);
		}
	}
}
