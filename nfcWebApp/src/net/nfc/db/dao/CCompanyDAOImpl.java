/**
 * 
 */
package net.nfc.db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.nfc.db.entity.CCompany;
import net.nfc.web.forms.CSearchForm;


/**
 * @author Maciek
 * 24-06-2011
 *
 */
@Repository("CCompanyDAO")
public class CCompanyDAOImpl implements CCompanyDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<CCompany> list() throws AdException {
		try {
			return (List<CCompany>) sessionFactory.getCurrentSession().createCriteria(CCompany.class).addOrder(Order.asc("name")).list() ;
		}catch(HibernateException e) {
			throw new AdException("Could not list Companies ",e);
		}
	}

	public CCompany get(long id) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CCompany where id=:id");
			q.setLong("id",id);
			CCompany cmp = (CCompany)q.uniqueResult();
			return cmp;
		}catch(HibernateException e) {
			throw new AdException("Could not get Company id: " +id , e);
		}
	}
	
	public CCompany get(String nip) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CCompany where nip=:nip");
			q.setString("nip",nip);
			CCompany cmp = (CCompany)q.uniqueResult();
			return cmp;
		}catch(HibernateException e) {
			throw new AdException("Could not get Company nip: " +nip , e);
		}
	}
	
	
	public boolean checkNIP(String nip)  throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("select count(*) from CCompany where nip= :nip");
			q.setString("nip", nip);
			int count = ((Number)q.uniqueResult()).intValue();
			
			if(count == 0 ){
				return false;
			}
			else return true;
			
		}catch(HibernateException e) {
			throw new AdException("Could not count company for nip : " + nip, e);
		}
	}
	
	public CCompany create(CCompany cmp) throws AdException {
		try {
			sessionFactory.getCurrentSession().save(cmp);
			return cmp;
		}catch(HibernateException e) {
			throw new AdException("Could not add new Company : " + cmp.getName(), e);
		}
	}
	
	public void update(CCompany cmp) throws AdException {
		try {
			sessionFactory.getCurrentSession().update(cmp);
		}catch(HibernateException e) {
			throw new AdException("Could not update Company " +cmp.getName(),e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CCompany> searchCompany(CSearchForm form)
			throws AdException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(CCompany.class);
			if(! form.getName().equals("")) crit.add(Restrictions.ilike("name", "%"+form.getName()+"%"));
			if(! form.getCity().equals("")) crit.add(Restrictions.ilike("city","%"+ form.getCity()+"%"));
			if(! form.getNip().equals("")) crit.add(Restrictions.eq("nip", form.getNip()));
			
			return (List<CCompany>) crit.list();
			
		}catch (HibernateException e) {
			throw new AdException("Could not find company from search form",e);
		}
	}
	
	
}
