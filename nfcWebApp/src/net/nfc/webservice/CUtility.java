package net.nfc.webservice;

import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CDevicesDAOImpl;
import net.nfc.db.dao.CEmployeeDAOImpl;
import net.nfc.db.entity.CDevices;
import net.nfc.db.entity.CEmployee;
import net.nfc.db.entity.CUsers;
/**
 * 
 * @author Maciek
 *
 */
public class CUtility {

	// pobieram usera dla danego user_login a następnie go weryfikuje
	public static boolean checkUser(CUsers user, String pass, String imei, String imsi) {
		try {
		if(user!=null && user.getUserPassword().equals(pass)) {
	
			CEmployeeDAOImpl empDAO = new CEmployeeDAOImpl();
			CDevicesDAOImpl devDAO = new CDevicesDAOImpl();
			
			CEmployee emp = empDAO.getEmployeeForUser(user.getId());
			CDevices dev =devDAO.getDeviceForEmployee(emp.getId());
			
			System.out.println("imei " + dev.getDeviceImei() + " imsi " + dev.getImsi());
			 
			
			if(dev.getDeviceImei().equals(imei) &&  dev.getImsi().equals(imsi))  {
				return true;
			}
			else return false;	
		}else return false;
		
		}catch (AdException  e) {
			
			System.out.println(e.getStackTrace());
			System.err.println(e.getMessage() );
			return false;
		}
	}
	
	
}
