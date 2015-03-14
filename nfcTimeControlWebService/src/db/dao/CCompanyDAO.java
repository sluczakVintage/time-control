/**
 * 
 */
package db.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CCompany;

/**
 * @author Maciek 
 * 24-06-2011
 *
 */
public interface CCompanyDAO{
	
	public List<CCompany> list() throws AdException ;
	public CCompany get(long id) throws AdException ;
	public CCompany get(String nip) throws AdException ;
	
	
	public boolean checkNIP(String nip)  throws AdException;
	
	public CCompany create(CCompany cmp) throws AdException ;
	
	public void update(CCompany cmp) throws AdException ;
}
