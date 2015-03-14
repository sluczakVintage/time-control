/**
 * 
 */
package net.nfc.web.service;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CEmployeeDAO;
import net.nfc.db.entity.CEmployee;
import net.nfc.web.forms.CSearchForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Maciek
 *
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private CEmployeeDAO EmployeeDAO;
	
	public EmployeeServiceImpl() {}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEmployee(CEmployee employee) throws AdException {
		EmployeeDAO.create(employee);
	}

	@Transactional
	public List<CEmployee> listEmployees() throws AdException {
		return EmployeeDAO.list();
	}
	
	@Transactional
	public CEmployee getEmployee(long id) throws AdException {
		return EmployeeDAO.get(id);
	}
	
	@Transactional
	public void updateEmployee(CEmployee employee) throws AdException {
		EmployeeDAO.update(employee);
	}

	@Override
	public List<CEmployee> searchEmployee(CSearchForm form) throws AdException {
		return EmployeeDAO.searchEmployee(form);
	}

	@Override
	public List<CEmployee> listEmployeesWithUsers() {
		return EmployeeDAO.listEmployeesWithUsers();
	}
	

}
