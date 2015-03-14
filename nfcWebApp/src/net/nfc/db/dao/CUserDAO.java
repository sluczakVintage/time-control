package net.nfc.db.dao;
/**
 * @author Maciek
 * 24-06-2011
 *
 */

import java.util.List;

import net.nfc.db.entity.CUsers;

public interface CUserDAO {

	public CUsers get(long id) throws AdException;
	
	public CUsers get(String username) throws AdException;
	
	public CUsers create(String username, String password);
	
	public void update(CUsers user) throws AdException;
	 
	public List<CUsers> list() throws AdException;
	
	public boolean checkUser(String username, String password) throws AdException ;
}
