package db.dao;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;


public class DAO {
	
	private static final Logger log = Logger.getAnonymousLogger();
	private static final ThreadLocal session = new ThreadLocal();
	
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	protected DAO() {}
	
	public static Session getSession() {
		Session session =(Session) DAO.session.get();
		if(session == null) {
			session = sessionFactory.openSession();
			DAO.session.set(session);
		}
		return session;
	}
	
	protected void begin() {
		getSession().beginTransaction();
	}
	
	protected void commit() {
		getSession().getTransaction().commit();
	}
	
	protected void rollback() {
		try{
			getSession().getTransaction().rollback();
		}catch (HibernateException e) {
			log.log(Level.WARNING,"Cannot rollback",e);
		}
		try{
			getSession().close();
		}catch (HibernateException e) {
			log.log(Level.WARNING,"Cannot close",e);
		}
		DAO.session.set(null);
	}
	
	public static void close() {
		getSession().close();
		DAO.session.set(null);
	}
	
	public Timestamp getTimestamp(){
		Timestamp time = new Timestamp(new java.util.Date().getTime());
		return time;
	}
}