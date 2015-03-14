package db.dao;
/**
 * @author Maciek
 * 24-06-2011
 *
 */
import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CUsers;

public class CUserDAOImpl extends DAO implements CUserDAO{

	public CUsers get(String username) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from CUsers where userName=:username");
			q.setString("username", username);
			CUsers user = (CUsers)q.uniqueResult();
			commit();
			return user;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get user " +username,e);
		}
	}
	
	public CUsers create(String username, String password) throws AdException{
		try{
			begin();
			CUsers user = new CUsers(username, password,getTimestamp(),'1');
			getSession().save(user);
			commit();
			return user;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not create user" + username, e);
		}
	}
	
	public void update(CUsers user) throws AdException{
		try{
			begin();
			getSession().update(user);
			commit();
			
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not update user " +user.getUserName(),e);
		}
	}
	 
	
	public boolean checkUser(String username, String password) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("selct count(*) from CUser where userName = :username and password = :password");
			q.setString("username",username);
			q.setString("password",password);
			Integer number = (Integer)q.uniqueResult();
			
			if(number == 1 ) {
				return true; 
			}
			
			return false;
			
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not check user " + username,e);
		}
	}
}
