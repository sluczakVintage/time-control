/**
 * 
 */
package db.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CEmployee;
/**
 * @author Maciek
 * 24-06-2011
 *
 */
public interface CEmployeeDAO {

	public List<CEmployee> list() throws AdException ;

	public CEmployee get(long id) throws AdException ;
	
	public CEmployee create(CEmployee emp) throws AdException ;
	
	public void update(CEmployee emp) throws AdException ;
	
}
