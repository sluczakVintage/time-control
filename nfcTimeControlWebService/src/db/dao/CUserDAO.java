package db.dao;
/**
 * @author Maciek
 * 24-06-2011
 *
 */
import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CUsers;

public interface CUserDAO {

	public CUsers get(String username) throws AdException;
	
	public CUsers create(String username, String password) throws AdException;
	
	public void update(CUsers user) throws AdException;
	 
	
	public boolean checkUser(String username, String password) throws AdException ;
}
