package net.nfc.db.dao;
/**
 * @author Maciek
 * 24-06-2011
 *
 */

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.nfc.db.entity.CUserPrivileges;
import net.nfc.db.entity.CUsers;

@Repository("CUserPrivilegesDAO")
public class CUserPrivilegesDAOImpl implements CUserPrivilegesDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public CUserPrivileges get(long userID) throws AdException{
		try{
			Query q = sessionFactory.getCurrentSession().createQuery("from CUserPrivileges where user_id=:userID");
			q.setLong("userID", userID);
			CUserPrivileges userPrivileges = (CUserPrivileges)q.uniqueResult();
			
			return userPrivileges;
		}catch(HibernateException e) {
			throw new AdException("Could not get user privileges for userID : " +userID,e);
		}
	}
	
	public CUserPrivileges create(CUsers user, String role) throws AdException{
		try{
			
			CUserPrivileges userPrivileges = new CUserPrivileges(user, role);
			sessionFactory.getCurrentSession().save(userPrivileges);
			
			return userPrivileges;
		}catch(HibernateException e) {
			
			throw new AdException("Could not create userPrivileges for user :" + user.getUserName(), e);
		}
	}
	
	public void update(CUserPrivileges userPriv) throws AdException{
		try{
			sessionFactory.getCurrentSession().update(userPriv);
			
			
		}catch(HibernateException e) {
			
			throw new AdException("Could not update userPrivileges for user " + userPriv.getUser().getUserName() ,e);
		}
	}

}
