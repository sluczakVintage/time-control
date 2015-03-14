package webService;

import db.dao.AdException;
import db.dao.CDevicesDAOImpl;
import db.dao.CEmployeeDAOImpl;
import db.entity.CConstrains.devicesStatus;
import db.entity.CDevices;
import db.entity.CEmployee;
import db.entity.CUsers;
import db.entity.CConstrains;
/**
 * 
 * @author Maciek
 *
 */
public class CUtility {

	private static CEmployeeDAOImpl empDAO = new CEmployeeDAOImpl();
	private  static CDevicesDAOImpl devDAO  = new CDevicesDAOImpl();
	
	
	// pobieram usera dla danego user_login a następnie go weryfikuje
	
	
	public static boolean checkUser(CUsers user, String pass, String imei, String imsi) {
		try {
		if(user!=null && user.getUserPassword().equals(pass) && user.getIsActive() == '1') {
			
			CEmployee emp = empDAO.getEmployeeForUser(user.getId());
			CDevices dev =devDAO.getDeviceForEmployee(emp.getId());
			
			if(dev == null || emp == null) {
				System.out.println("tutaj");
				return false;
			}
		
			if(dev.getDeviceImei().equals(imei) &&  dev.getImsi().equals(imsi) && dev.getDeviceStatus().equals(devicesStatus.ACTIVE.getText()))  {
				return true;
			}
			else 
				{
				System.out.println("device if");return false;	
				}
		}else  
			{
			System.out.println("pierwszy if"); return false;
			}
		
		}catch (AdException  e) {
			
			System.out.println(e.getStackTrace());
			System.err.println(e.getMessage() );
			return false;
		}
	}
	
	
}
