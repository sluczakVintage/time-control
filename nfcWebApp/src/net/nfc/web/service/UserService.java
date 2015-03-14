package net.nfc.web.service;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CUserPrivileges;
import net.nfc.db.entity.CUsers;

public interface UserService {

	public List<CUsers> listUsers() throws AdException;
	public CUsers getUser(long id) throws AdException;
	public void update(CUsers user) throws AdException;
	public void addUser(CUsers user) throws AdException;
	public CUsers getUserByName(String userName) throws AdException;
	public CUserPrivileges getUserPrivileges(long userID) throws AdException;
	public void addPrivileges(CUsers user, String role) throws AdException;
	public void updatePrivileges(CUserPrivileges priv) throws AdException;
	
	
}
