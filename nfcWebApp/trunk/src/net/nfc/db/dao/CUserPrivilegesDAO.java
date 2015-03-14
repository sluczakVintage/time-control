package net.nfc.db.dao;
/**
 * @author Maciek
 * 24-06-2011
 *
 */

import net.nfc.db.entity.CUserPrivileges;
import net.nfc.db.entity.CUsers;

public interface CUserPrivilegesDAO {

	public CUserPrivileges get(long userID) throws AdException;
	
	public CUserPrivileges create(CUsers user, String role) throws AdException;
	
	public void update(CUserPrivileges userPrivileges) throws AdException;
	 
}
