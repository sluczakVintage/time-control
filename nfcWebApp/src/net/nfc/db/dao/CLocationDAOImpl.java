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

import net.nfc.db.entity.CConstrains.locationStatus;
import net.nfc.db.entity.CLocation;
import net.nfc.web.forms.CSearchForm;

/**
 * @author Maciek
 * 24-06-2011
 *
 */
@Repository("CLocationDAO")
public class CLocationDAOImpl implements CLocationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<CLocation> list() throws AdException {
		try {
			return (List<CLocation>) sessionFactory.getCurrentSession().createCriteria(CLocation.class).addOrder(Order.asc("name")).list() ;
		}catch(HibernateException e) {
			throw new AdException("Could not list Locations ",e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CLocation> listLocationsForCompany(long companyID) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CLocation where company_id=:id");
			q.setLong("id", companyID);
			
			return (List<CLocation>) q.list();
			
			//return (List<CLocation>) sessionFactory.getCurrentSession().createCriteria(CLocation.class).list() ;
		}catch(HibernateException e) {
			throw new AdException("Could not list Locations ",e);
		}
	}
	
	
	public CLocation get(long id) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CLocation where id=:id");
			q.setLong("id",id);
			CLocation loc = (CLocation)q.uniqueResult();
			return loc;
		}catch(HibernateException e) {
			throw new AdException("Could not get Location id: " +id , e);
		}
	}
	
	public boolean checkTag(String tag) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("select count(loc) from CLocation loc where tagID=:tag") ; 
			q.setString("tag",tag);
			int count = ((Number) q.uniqueResult()).intValue();
			if(count == 0 ){
				return false;
			}
			return true;
		}catch (HibernateException e) {
			throw new AdException("Could not count location for tag: " + tag , e); 
		}
	}
	
	public CLocation create(CLocation loc) throws AdException {
		try {
			sessionFactory.getCurrentSession().save(loc);
			return loc;
		}catch(HibernateException e) {
			throw new AdException("Could not add new Location : " + loc.getName(), e);
		}
	}
	
	public void update(CLocation loc) throws AdException {
		try {
			sessionFactory.getCurrentSession().update(loc);
		}catch(HibernateException e) {
			throw new AdException("Could not update Location " +loc.getName(),e);
		}
	}
	
	public CLocation getLocationForTag(String tagId) throws AdException {
		try {

			Query q = sessionFactory.getCurrentSession().createQuery("from CLocation where tagID=:tagID");
			q.setString("tagID",tagId);
			CLocation loc = (CLocation)q.uniqueResult();
	
			return loc;
		}catch(HibernateException e) {

			throw new AdException("Could not get Location fot tagId: " +tagId , e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CLocation> searchLocation(CSearchForm form)
			throws AdException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(CLocation.class);
			if(! form.getStatus().equals("null")) crit.add(Restrictions.eq("status", form.getStatus()));
			if(! form.getName().equals("") ) crit.add(Restrictions.ilike("name", "%" + form.getName() + "%"));
			if(! form.getCity().equals("") ) crit.add(Restrictions.ilike("city", "%" + form.getCity() + "%"));
				
			
			return (List<CLocation>) crit.list();
			
		}catch (HibernateException e) {
			throw new AdException("Could not find event from search form",e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CLocation> listActiveLocations() {
		Criteria crit =sessionFactory.getCurrentSession().createCriteria(CLocation.class);
		crit.add(Restrictions.eq("status",locationStatus.ACTIVE.getText()));
		return (List<CLocation>)crit.list();
	}
}
