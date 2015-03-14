package net.nfc.db.dao;

import java.util.List;

import net.nfc.db.entity.CEmployee;
import net.nfc.web.forms.CSearchForm;
/**
 * @author Maciek
 * 24-06-2011
 *
 */
public interface CEmployeeDAO  {

	public List<CEmployee> list() throws AdException;

	public CEmployee get(long id) throws AdException ;
	
	public CEmployee create(CEmployee emp) throws AdException ;
	
	public void update(CEmployee emp) throws AdException ;
	
	public CEmployee getEmployeeForUser(long userID) throws AdException;
	
	public List<CEmployee> searchEmployee(CSearchForm searchForm) throws AdException;
	
	public List<CEmployee> listEmployeesWithUsers();
	
	
	
}
