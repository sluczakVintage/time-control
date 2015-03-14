/**
 * 
 */
package db.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CDevices;

/**
 * @author Maciek24-06-2011
 *
 */
public interface CDevicesDAO {

	public List<CDevices> list() throws AdException ;

	public CDevices get(long id) throws AdException ;
	
	public CDevices create(CDevices device) throws AdException ;
	
	public void update(CDevices device) throws AdException ;
}
