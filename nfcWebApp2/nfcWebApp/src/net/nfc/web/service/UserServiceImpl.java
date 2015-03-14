/**
 * 
 */
package net.nfc.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CUserDAO;
import net.nfc.db.dao.CUserPrivilegesDAO;
import net.nfc.db.entity.CUserPrivileges;
import net.nfc.db.entity.CUsers;

/**
 * @author Maciek
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private CUserDAO userDAO;
	
	@Autowired
	private CUserPrivilegesDAO userPrivilegesDAO;
	
	public List<CUsers> listUsers() throws AdException {
		return userDAO.list();
	}

	public CUsers getUser(long id) throws AdException {
		return userDAO.get(id);
	}

	public void addUser(CUsers user ) {
		userDAO.create(user.getUserName(), user.getUserPassword());

	}
	
	public CUsers getUserByName(String userName) throws AdException {
		return userDAO.get(userName);
	}
	
	public CUserPrivileges getUserPrivileges(long id) throws AdException {
		return userPrivilegesDAO.get(id);
	}

	@Override
	public void addPrivileges(CUsers user, String role) throws AdException {
		userPrivilegesDAO.create(user , role);
		
	}

	@Override
	public void updatePrivileges(CUserPrivileges priv) throws AdException {
		userPrivilegesDAO.update(priv);
		
	}

	@Override
	public void update(CUsers user) throws AdException {
		userDAO.update(user);
		
	}
	
}
