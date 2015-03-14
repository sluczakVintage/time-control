/**
 * 
 */
package net.nfc.db.dao;

import java.util.List;


import net.nfc.db.entity.CCompany;
import net.nfc.web.forms.CSearchForm;

/**
 * @author Maciek
 * 24-06-2011
 *
 */
public interface CCompanyDAO {
	
	public List<CCompany> list() throws AdException ;
	
	public CCompany get(long id) throws AdException ;
	
	public CCompany get(String nip) throws AdException ;
		
	public boolean checkNIP(String nip)  throws AdException ;
	
	public CCompany create(CCompany cmp) throws AdException ;
	
	public void update(CCompany cmp) throws AdException ;
	
	public List<CCompany> searchCompany(CSearchForm searchForm) throws AdException;
}
