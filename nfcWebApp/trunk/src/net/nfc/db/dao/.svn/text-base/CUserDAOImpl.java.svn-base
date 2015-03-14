package net.nfc.db.dao;
/**
 * @author Maciek
 * 24-06-2011
 *
 */
import java.sql.BatchUpdateException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import net.nfc.db.entity.CUsers;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("CUserDAO")
public class CUserDAOImpl implements CUserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CUsers get(long id) throws AdException{
		try{
			
			Query q = sessionFactory.getCurrentSession().createQuery("from CUsers where id=:id");
			q.setLong("id", id);
			CUsers user = (CUsers)q.uniqueResult();
			
			return user;
		}catch(HibernateException e) {
			
			throw new AdException("Could not get user id : " +id,e);
		}
	}
	
	public CUsers get(String username) throws AdException{
		try{
			
			Query q = sessionFactory.getCurrentSession().createQuery("from CUsers where userName=:username");
			q.setString("username", username);
			CUsers user = (CUsers)q.uniqueResult();
			if(user==null) throw new AdException("No such user " +username);
			return user;
		}catch(HibernateException e) {
			throw new AdException("Could not get user " +username,e);
		}
	}
	
	public CUsers create(String username, String password) {
		CUsers user = new CUsers(username, password,new Timestamp(new java.util.Date().getTime()),'1');
			sessionFactory.getCurrentSession().save(user);
			return user;
	}
	
	public void update(CUsers user) throws AdException{
		try{
			
			sessionFactory.getCurrentSession().update(user);
			
			
		}catch(HibernateException e) {
			
			throw new AdException("Could not update user " +user.getUserName(),e);
		}
	}
	 
	
	public boolean checkUser(String username, String password) throws AdException {
		try {
			
			Query q = sessionFactory.getCurrentSession().createQuery("selct count(*) from CUser where userName = :username and password = :password");
			q.setString("username",username);
			q.setString("password",password);
			Integer number = (Integer)q.uniqueResult();
			
			if(number == 1 ) {
				return true; 
			}
			
			return false;
			
		}catch(HibernateException e){
			
			throw new AdException("Could not check user " + username,e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CUsers> list() throws AdException {
		try {
			return (List<CUsers>) sessionFactory.getCurrentSession().createCriteria(CUsers.class).list() ;
		}catch(HibernateException e) {
			throw new AdException("Could not list Users ",e);
		}
	}

	
}
