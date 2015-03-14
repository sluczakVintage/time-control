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
public class CCompanyDAOImpl extends DAO implements CCompanyDAO {
	public List<CCompany> list() throws AdException {
		try {
			begin();
			Query q =getSession().createQuery("from CCompany");
			List<CCompany> list = q.list();
			commit();
			return list;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not list Companies ",e);
		}
	}

	public CCompany get(long id) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CCompany where id=:id");
			q.setLong("id",id);
			CCompany cmp = (CCompany)q.uniqueResult();
			commit();
			return cmp;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Company id: " +id , e);
		}
	}
	
	public CCompany get(String nip) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CCompany where nip=:nip");
			q.setString("nip",nip);
			CCompany cmp = (CCompany)q.uniqueResult();
			commit();
			return cmp;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Company nip: " +nip , e);
		}
	}
	
	
	public boolean checkNIP(String nip)  throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("select count(*) from CCompany where nip= :nip");
			q.setString("nip", nip);
			int count = ((Number)q.uniqueResult()).intValue();
			
			if(count != 0 ){
				return true;
			}
			else return false;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not count company for nip : " + nip, e);
		}
	}
	
	public CCompany create(CCompany cmp) throws AdException {
		try {
			begin();
			getSession().save(cmp);
			commit();
			return cmp;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not add new Company : " + cmp.getName(), e);
		}
	}
	
	public void update(CCompany cmp) throws AdException {
		try {
			begin();
			getSession().update(cmp);
			commit();
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not update Company " +cmp.getName(),e);
		}
	}
}
