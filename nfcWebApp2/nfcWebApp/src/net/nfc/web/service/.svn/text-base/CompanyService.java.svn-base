/**
 * 
 */
package net.nfc.web.service;

import java.util.List;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CCompany;
import net.nfc.web.forms.CSearchForm;

/**
 * @author Maciek
 *
 */
public interface CompanyService {

	
	public void addCompany(CCompany company) throws AdException;
	public List<CCompany> listCompanies() throws AdException;
	public CCompany getCompany(long id) throws AdException;
	public void updateCompany(CCompany company) throws AdException;
	public List<CCompany> searchCompany(CSearchForm form) throws AdException;
}
