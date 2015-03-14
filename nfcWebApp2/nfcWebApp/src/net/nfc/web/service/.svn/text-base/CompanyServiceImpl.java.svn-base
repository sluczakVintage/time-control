/**
 * 
 */
package net.nfc.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CCompanyDAO;
import net.nfc.db.entity.CCompany;
import net.nfc.web.forms.CSearchForm;

/**
 * @author Maciek
 *
 */
@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CCompanyDAO companyDAO;
	
	public void addCompany(CCompany company) throws AdException {
		companyDAO.create(company);
	}

	@Override
	public List<CCompany> listCompanies() throws AdException {
		return companyDAO.list();
	}

	@Override
	public CCompany getCompany(long id) throws AdException {
		return companyDAO.get(id);
	}

	@Override
	public void updateCompany(CCompany company) throws AdException {
		companyDAO.update(company);
		
	}

	@Override
	public List<CCompany> searchCompany(CSearchForm form) throws AdException {
		return companyDAO.searchCompany(form);
	}

}
