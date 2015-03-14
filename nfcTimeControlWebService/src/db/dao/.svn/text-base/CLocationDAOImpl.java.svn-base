/**
 * 
 */
package db.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CLocation;

/**
 * @author Maciek
 * 24-06-2011
 *
 */
public class CLocationDAOImpl extends DAO implements CLocationDAO {
	
	public List<CLocation> list() throws AdException {
		try {
			begin();
			Query q =getSession().createQuery("from CLocation");
			List<CLocation> list = q.list();
			commit();
			return list;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not list Locations ",e);
		}
	}

	public CLocation get(long id) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CLocation where id=:id");
			q.setLong("id",id);
			CLocation loc = (CLocation)q.uniqueResult();
			commit();
			return loc;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Location id: " +id , e);
		}
	}
	
	public boolean checkTag(String tag) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("select count(loc) from CLocation loc where tagID=:tag") ; 
			q.setString("tag",tag);
			int count = ((Number) q.uniqueResult()).intValue();
			
			if(count != 0 ){
				return true;
			}
			return false;
			
		}catch (HibernateException e) {
			rollback();
			throw new AdException("Could not count location for tag: " + tag , e); 
		}
	}
	
	public CLocation create(CLocation loc) throws AdException {
		try {
			begin();
			getSession().save(loc);
			commit();
			return loc;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not add new Location : " + loc.getName(), e);
		}
	}
	
	public void update(CLocation loc) throws AdException {
		try {
			begin();
			getSession().update(loc);
			commit();
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not update Location " +loc.getName(),e);
		}
	}

	public CLocation getLocationForTag(String tagId) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CLocation where tagID=:tagID");
			q.setString("tagID",tagId);
			CLocation loc = (CLocation)q.uniqueResult();
			commit();
			return loc;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Location fot tagId: " +tagId , e);
		}
	}
}
