/**
 * 
 */
package net.nfc.web.service;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CEmployee;
import net.nfc.web.forms.CSearchForm;

/**
 * @author Maciek
 *
 */
public interface EmployeeService {

	public void addEmployee(CEmployee Employee ) throws AdException;
	public List<CEmployee> listEmployees() throws AdException;
	public CEmployee getEmployee(long id) throws AdException;
	public void updateEmployee(CEmployee Employee) throws AdException;
	public List<CEmployee> searchEmployee(CSearchForm form) throws AdException;
	public List<CEmployee> listEmployeesWithUsers();
	
}
