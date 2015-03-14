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
public interface CLocationDAO {
	
	public List<CLocation> list() throws AdException ;

	public CLocation get(long id) throws AdException;
	
	public boolean checkTag(String tag) throws AdException;
	
	public CLocation getLocationForTag(String tagId) throws AdException;
	
	public CLocation create(CLocation loc) throws AdException ;
	
	public void update(CLocation loc) throws AdException ;
}
