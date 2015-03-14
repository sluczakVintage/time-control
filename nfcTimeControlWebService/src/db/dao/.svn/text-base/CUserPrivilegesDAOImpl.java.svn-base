package db.dao;
/**
 * @author Maciek
 * 24-06-2011
 *
 */
import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;


import db.entity.CUserPrivileges;
import db.entity.CUsers;

public class CUserPrivilegesDAOImpl extends DAO implements CUserPrivilegesDAO{
	
	public CUserPrivileges get(long userID) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from CUserPrivileges where user_id=:userID");
			q.setLong("userID", userID);
			CUserPrivileges userPrivileges = (CUserPrivileges)q.uniqueResult();
			commit();
			return userPrivileges;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get user privileges for userID : " +userID,e);
		}
	}
	
	public CUserPrivileges create(CUsers user, String role) throws AdException{
		try{
			begin();
			CUserPrivileges userPrivileges = new CUserPrivileges(user, role);
			getSession().save(userPrivileges);
			commit();
			return userPrivileges;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not create userPrivileges for user :" + user.getUserName(), e);
		}
	}
	
	public void update(CUserPrivileges userPriv) throws AdException{
		try{
			begin();
			getSession().update(userPriv);
			commit();
			
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not update userPrivileges for user " + userPriv.getUser().getUserName() ,e);
		}
	}

}
